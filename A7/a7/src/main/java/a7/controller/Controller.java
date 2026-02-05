package a7.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import a7.repo.IRepository;

import a7.model.ADT.interfaces.IDictionary;
import a7.model.ADT.implementations.List;
import a7.model.ADT.interfaces.IHeap;
import a7.model.ADT.interfaces.IList;
import a7.model.values.RefValue;
import a7.model.types.RefType;
import a7.model.values.IValue;
import a7.model.ProgramState;

public final class Controller 
{
    private ExecutorService executor;
    private final IRepository repo;
    private boolean display = false,
                    log = false,
                    gc = true;

    public Controller(IRepository repo) { this.repo = repo; }

    public IRepository Repository() { return repo; }

    public boolean ToggleDisplayFlag() { return (display = !display); }

    public boolean ToggleLogFlag() { return (log = !log); }

    public boolean ToggleGC() { return (gc = !gc); }

    public void ExecuteAll() throws Exception
    {
        executor = Executors.newFixedThreadPool(2);
        
        IList<ProgramState> programs = RemoveCompleted(repo.GetPrograms());
        while (programs.Length() > 0)
        {
            if (gc) GC(programs.Select(p -> p.symbols()).ToList(), programs.First().heap());

            ParallelSingleStep(programs);
            programs = RemoveCompleted(repo.GetPrograms());
        }

        executor.shutdownNow();
        repo.SetPrograms(programs);
    }
    
    private void ParallelSingleStep(IList<ProgramState> programs) throws Exception
    {
        if (log) programs.ForEach(p -> repo.LogExecution(p));
        java.util.List<Callable<ProgramState>> call_list = programs.Select(p -> (Callable<ProgramState>)(() -> p.ExecuteSingleStep()))
                                                                   .ToList()
                                                                   .Vanilla();

        Exception[] exception = new Exception[1];
        java.util.List<ProgramState> new_programs = null;
        try 
        {
            new_programs = executor.invokeAll(call_list)
                                   .stream()
                                   .map(f -> 
                                       {
                                           try { return f.get(); } 
                                           catch (InterruptedException | ExecutionException ex) 
                                           { 
                                               exception[0] = ex;
                                               return null;
                                           }
                                       })
                                   .filter(p -> p != null)
                                   .collect(Collectors.toList());
        } 
        catch (InterruptedException e) { exception[0] = e; }
        if (exception[0] != null || new_programs == null) throw exception[0];

        for (ProgramState p : new_programs) programs.Append(p);
        if (log || display) programs.ForEach(p -> 
        {
            if (display) System.out.println(p.toString()); 
            if (log) repo.LogExecution(p); 
        });

        repo.SetPrograms(programs);
    }

    private IList<ProgramState> RemoveCompleted(IList<ProgramState> programs)
    {
        return programs.Where(p -> !p.IsCompleted())
                       .ToList();
    }

    private void GC(IList<IDictionary<String, IValue<?>>> symbols, IHeap<Integer, IValue<?>> heap)
    {
        IList<Integer> active_refs = FilterReferences(symbols, heap);

        heap.Where(s -> !active_refs.Exists(s.first))
            .ForEach(s -> heap.Remove(s.first));
    }

    private IList<Integer> FilterReferences(IList<IDictionary<String, IValue<?>>> symbols, IHeap<Integer, IValue<?>> heap)
    {
        IList<Integer> refs = new List<>();

        symbols.ForEach(x -> x.Where(s -> s.second.GetType().Equals(new RefType(null)))
                              .Select(s -> ((RefValue)s.second).Address())
                              .ForEach(s -> refs.Append(s)));

        heap.Where(s-> s.second.GetType().Equals(new RefType(null)))
            .Select(s -> ((RefValue)s.second).Address())
            .ForEach(s -> refs.Append(s));

        return refs;
    }
}