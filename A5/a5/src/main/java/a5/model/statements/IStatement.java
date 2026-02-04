package a5.model.statements;

import a5.model.exceptions.InvalidTypeException;
import a5.model.ADT.interfaces.IDictionary;
import a5.model.ProgramState;
import a5.model.types.IType;

public interface IStatement 
{
    public IDictionary<String, IType> TypeCheck(IDictionary<String, IType> types) throws InvalidTypeException;

    public ProgramState Execute(ProgramState state);

    public String ToString();
}