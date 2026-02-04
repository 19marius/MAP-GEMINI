package a5.model;

import java.io.BufferedReader;
import java.util.Stack;

import a5.helpers.StringHelpers;
import a5.model.ADT.implementations.Dictionary;
import a5.model.ADT.interfaces.IDictionary;
import a5.model.ADT.interfaces.IHeap;
import a5.model.ADT.interfaces.IList;
import a5.model.ADT.interfaces.IStack;
import a5.model.exceptions.InvalidTypeException;
import a5.model.statements.IStatement;
import a5.model.values.IValue;
import a5.model.values.StringValue;


public class ProgramState
{
    private static final Stack<Integer> available_ids = new Stack<>();

    private final ProgramLoggingOptions          log_options;
    private final int id;

    private IDictionary<StringValue, BufferedReader> files;
    private IStack<IStatement>                       execution;
    private IDictionary<String, IValue<?>>           symbols;
    private IList<IValue<?>>                         output;
    private IHeap<Integer, IValue<?>>                heap;

    private IStatement                     original;
    private int                            step = 0;

    public ProgramState(IStatement                     original,
                        IStack<IStatement>             execution,
                        IDictionary<String, IValue<?>> symbols,
                        IHeap<Integer, IValue<?>>         heap,
                        IList<IValue<?>>               output,
                        IDictionary<StringValue, BufferedReader> files)
    {
        try { original.TypeCheck(new Dictionary<>()); }
        catch (InvalidTypeException ex) { System.out.println(original.ToString()); }

        id = NextID();
        log_options = new ProgramLoggingOptions();

        this.execution = execution;
        this.original = original;
        this.symbols = symbols;
        this.output = output;
        this.files = files;
        this.heap = heap;

        execution.Push(original);
    }

    public ProgramState ExecuteSingleStep() throws Exception
    { 
        ProgramState result;
        try { result = execution().Pop().Execute(this); }
        catch (Exception ex)
        {
            Reset();
            throw ex;
        }

        IncreaseStep();
        return result;
    }

    public boolean IsCompleted() { return execution().IsEmpty(); }

    public String ToString() { return toString(); }
   
    public int IncreaseStep() { return ++step; }

    public void Reset()
    {
        step = 0;

        execution().Clear();
        symbols().Clear();
        output().Clear();
        heap().Clear();

        execution.Push(original);
    }

    static synchronized int NextID()
    {
        if (available_ids.empty()) return available_ids.push(2) - 1;

        int key = available_ids.pop();
        if (available_ids.empty()) available_ids.push(key + 1);

        return key;
    }

    public IStack<IStatement> execution()           { return execution;  }
    public void execution(IStack<IStatement> value) { execution = value; }

    public IDictionary<String, IValue<?>> symbols()           { return symbols;  }
    public void symbols(IDictionary<String, IValue<?>> value) { symbols = value; }

    public IList<IValue<?>> output()           { return output;  }
    public void output(IList<IValue<?>> value) { output = value; }

    public IStatement original()           { return original;  }
    public void original(IStatement value) { original = value; }
    
    public IHeap<Integer, IValue<?>> heap()           { return heap;  }
    public void heap(IHeap<Integer, IValue<?>> value) { heap = value; }

    public IDictionary<StringValue, BufferedReader> files()           { return files;  }
    public void files(IDictionary<StringValue, BufferedReader> value) { files = value; }

    public ProgramLoggingOptions LoggingOptions() { return log_options; }

    public int ID() { return id; }

    @Override
    public String toString()
    {
        StringBuilder out = new StringBuilder();

        if (log_options.DisplayIterationIndex())
        {
            out.append(StringHelpers.Repeat('-', 50));
            out.append(String.format("\n(#%d) PROGRAM STATE #%d\n", ID(), step));
            out.append(StringHelpers.Repeat('-', 50));
            out.append("\n");   
        }

        if (log_options.DisplayOriginalProgram())
        {
            out.append("ORIGINAL PROGRAM:\n")
               .append(original().ToString())
               .append(";\n\n");
        }

        if (log_options.DisplayExecutionStack())
        {
            out.append("EXECUTION STACK: ")
               .append(execution().Size() > 0 ? "" : "[empty]")
               .append("\n");
            
               if (execution().Size() > 0) execution().ForEach(s -> out.append(String.format("%s%s\n", log_options.DisplayDecorationalArrows() ? "> " : "", s.ToString().replace("\n  ", "\n").replace('\n', ' ').replaceAll(" +", " ").replaceAll("(?<=\\() | (?=\\))", ""))));
        }

        if (log_options.DisplaySymbolTable())
        {
            out.append("\n")
               .append("SYMBOL TABLE: ")
               .append(symbols().KeyCount() > 0 ? "" : "[empty]")
               .append("\n");
        
            if (symbols().KeyCount() > 0) symbols().ForEach(s -> out.append(String.format("%s%s (%s): %s\n", log_options.DisplayDecorationalArrows() ? "> " : "", s.first, s.second.GetType().ToString(), s.second.ToString())));
        }

        if (log_options.DisplayHeap())
        {
            out.append("\n")
               .append("HEAP: ")
               .append(heap().KeyCount() > 0 ? "" : "[empty]")
               .append("\n");

            if (heap().KeyCount() > 0) heap().ForEach(s -> out.append(String.format("%s%s (%s): %s\n", log_options.DisplayDecorationalArrows() ? "> " : "", StringHelpers.ToHex(s.first), s.second.GetType().ToString(), s.second.ToString())));
        }

        if (log_options.DisplayOutput())
        {
            out.append("\n")
               .append("OUTPUT: ")
               .append(output().Length() > 0 ? "" : "[empty]")
               .append("\n");
 
            if (output().Length() > 0) output().ForEach(s -> out.append(String.format("%s%s\n", log_options.DisplayDecorationalArrows() ? "> " : "", s.ToString())));
        }

        if (log_options.DisplayFileTable())
        {
            out.append("\n")
               .append("FILE TABLE: ")
               .append(files().KeyCount() > 0 ? "" : "[empty]")
               .append("\n");

            if (files().KeyCount() > 0) files().ForEach(s -> out.append(String.format("%s%s\n", log_options.DisplayDecorationalArrows() ? "> " : "", s.first.GetValue())));    
        }
        
        return out.toString();
    }
}