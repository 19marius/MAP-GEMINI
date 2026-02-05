package a7.model.statements;

import a7.model.exceptions.InvalidTypeException;
import a7.model.ADT.interfaces.IDictionary;
import a7.model.ProgramState;
import a7.model.types.IType;

public interface IStatement 
{
    public IDictionary<String, IType> TypeCheck(IDictionary<String, IType> types) throws InvalidTypeException;

    public ProgramState Execute(ProgramState state);

    public String ToString();
}