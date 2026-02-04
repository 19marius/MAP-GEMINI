package a5.repo;

import a5.model.ADT.interfaces.IList;
import a5.model.ProgramState;

public interface IRepository 
{   
    public IList<ProgramState> GetPrograms();
    
    public void SetPrograms(IList<ProgramState> replacement);

    public void AddProgram(ProgramState program);

    public void LogExecution(ProgramState program);
}