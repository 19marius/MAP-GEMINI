package a5.model.statements;

import a5.model.ProgramState;
import a5.model.ADT.interfaces.IDictionary;
import a5.model.exceptions.InvalidTypeException;
import a5.model.types.IType;

public record DeclarationStatement
(
    String varname,
    IType type
) 
implements IStatement
{
    @Override
    public IDictionary<String, IType> TypeCheck(IDictionary<String, IType> types) throws InvalidTypeException
    {
        types.Set(varname, type);
        return types;
    }

    @Override
    public ProgramState Execute(ProgramState state) 
    {
        state.symbols().Set(varname, type.Default());
        return null;
    }

    @Override
    public String ToString() { return type.ToString() + " " + varname; }
}