package a7.model.statements;

import a7.model.ProgramState;
import a7.model.ADT.interfaces.IDictionary;
import a7.model.types.IType;

public record CompoundStatement
(
    IStatement first,
    IStatement second
) 
implements IStatement
{
    @Override
    public IDictionary<String, IType> TypeCheck(IDictionary<String, IType> types) { return second.TypeCheck(first.TypeCheck(types)); }
    
    @Override
    public ProgramState Execute(ProgramState state) 
    {
        state.execution().Push(second);
        state.execution().Push(first);

        return null;
    }

    @Override
    public String ToString() { return first.ToString() + ";\n" + second.ToString(); }
}