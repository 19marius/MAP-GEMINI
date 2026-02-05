package a7.repo;

import a7.model.ADT.interfaces.IList;
import a7.model.ProgramState;

public interface IRepository 
{   
    public IList<ProgramState> GetPrograms();
    
    public void SetPrograms(IList<ProgramState> replacement);

    public void AddProgram(ProgramState program);

    public void LogExecution(ProgramState program);
}