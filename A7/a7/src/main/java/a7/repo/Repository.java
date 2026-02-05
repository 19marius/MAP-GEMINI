package a7.repo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import a7.model.exceptions.LoggingException;
import a7.model.ADT.implementations.List;
import a7.model.ADT.interfaces.IList;
import a7.model.ProgramState;

public class Repository implements IRepository
{
    private String log_path;
    private final IList<ProgramState> programs;

    public Repository(ProgramState program, String log_path)
    {
        this.programs = new List<>();
        this.log_path = log_path;

        programs.Append(program);
    }
    
    public String GetLogPath() { return log_path; }

    public void SetLogPath(String path) { log_path = path; }

    @Override
    public void AddProgram(ProgramState program) { programs.Append(program); }

    @Override
    public IList<ProgramState> GetPrograms() { return programs; }

    @Override
    public void SetPrograms(IList<ProgramState> replacement)
    {
        programs.Clear();
        replacement.GetIterator().ForEach((s, i) -> programs.Append(s));
    }

    @Override
    public void LogExecution(ProgramState program) throws LoggingException
    {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(log_path, true))))
        {
            boolean arrows = program.LoggingOptions().DisplayDecorationalArrows(),
                    original = program.LoggingOptions().DisplayOriginalProgram();

            program.LoggingOptions().DisplayDecorationalArrows(false);
            program.LoggingOptions().DisplayOriginalProgram(false);

            writer.print(program.toString());
            writer.append('\n');

            program.LoggingOptions().DisplayDecorationalArrows(arrows);
            program.LoggingOptions().DisplayOriginalProgram(original);
        }
        catch (IOException ex) { throw new LoggingException(log_path); }
    }
}