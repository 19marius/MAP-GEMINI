package a7.model.statements;

import a7.model.ADT.interfaces.IDictionary;
import a7.model.ProgramState;
import a7.model.types.IType;

public class NopStatement implements IStatement
{
    @Override
    public IDictionary<String, IType> TypeCheck(IDictionary<String, IType> types) { return types; }
    
    @Override
    public ProgramState Execute(ProgramState state) { return null; }

    @Override
    public String ToString() { return "nop"; }

    @Override
    public String toString() { return ToString(); }
}