package a7.model.statements;

import a7.model.ProgramState;
import a7.model.ADT.interfaces.IDictionary;
import a7.model.exceptions.InvalidReferenceException;
import a7.model.exceptions.InvalidTypeException;
import a7.model.expressions.IExpression;
import a7.model.types.IType;
import a7.model.types.RefType;
import a7.model.values.IValue;
import a7.model.values.RefValue;

public record AllocationStatement
(
    String varname,
    IExpression expression
) 
implements IStatement
{
    @Override
    public IDictionary<String, IType> TypeCheck(IDictionary<String, IType> types) throws InvalidTypeException
    {
        if (!types.Get(varname).Equals(new RefType(expression.TypeCheck(types)))) throw new InvalidTypeException("The types of the variable and the expression do not match (" + types.Get(varname).ToString() + " ≠ " + expression.TypeCheck(types).ToString() + ").", expression.TypeCheck(types));
        return types;
    }

    @Override
    public ProgramState Execute(ProgramState state) 
    {
        if (!state.symbols().Exists(varname)) throw new InvalidReferenceException("Variable \"" + varname + "\" does not exist.", varname);
        if (!state.symbols().Get(varname).GetType().Equals(new RefType(null))) throw new InvalidTypeException("Referenced variable \"" + varname + "\" is not a reference type.", state.symbols().Get(varname).GetType());

        IValue<?> value = expression.Evaluate(state.symbols(), state.heap());
        if (!((RefType) state.symbols().Get(varname).GetType()).GetInnerType().Equals(value.GetType())) throw new InvalidTypeException("The types of the reference variable and the expression do not match (" + state.symbols().Get(varname).GetType().ToString() + " ≠ " + ((RefType) state.symbols().Get(varname).GetType()).GetInnerType().ToString() + ").", state.symbols().Get(varname).GetType());

        state.symbols().Set(varname, new RefValue(state.heap().Insert(value), value.GetType()));
        return null;
    }

    @Override
    public String ToString() { return "new(" + varname + ", " + expression.ToString() + ")"; }
}