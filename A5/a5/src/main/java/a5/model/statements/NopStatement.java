package a5.model.statements;

import a5.model.ADT.interfaces.IDictionary;
import a5.model.ProgramState;
import a5.model.types.IType;

public class NopStatement implements IStatement
{
    @Override
    public IDictionary<String, IType> TypeCheck(IDictionary<String, IType> types) { return types; }
    
    @Override
    public ProgramState Execute(ProgramState state) { return null; }

    @Override
    public String ToString() { return "nop"; }
}