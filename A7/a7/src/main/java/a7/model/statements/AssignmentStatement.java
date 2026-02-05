package a7.model.statements;

import a7.model.ProgramState;
import a7.model.ADT.interfaces.IDictionary;
import a7.model.exceptions.InvalidReferenceException;
import a7.model.exceptions.InvalidTypeException;
import a7.model.expressions.IExpression;
import a7.model.types.IType;
import a7.model.values.IValue;

public record AssignmentStatement
(
    String varname,
    IExpression expression
) 
implements IStatement
{
    @Override
    public IDictionary<String, IType> TypeCheck(IDictionary<String, IType> types) throws InvalidTypeException
    {
        if (types.Get(varname).Equals(expression.TypeCheck(types))) return types;
        else throw new InvalidTypeException("The types of the variable and the expression do not match (" + types.Get(varname).ToString() + " ≠ " + expression.TypeCheck(types).ToString() + ").", expression.TypeCheck(types));
    }

    @Override
    public ProgramState Execute(ProgramState state) throws InvalidReferenceException,
                                                           InvalidTypeException
    {
        if (!state.symbols().Exists(varname)) throw new InvalidReferenceException("Variable \"" + varname + "\" must be declared before it is assigned to.", varname);
        
        IValue<?> value = expression.Evaluate(state.symbols(), state.heap());
        if (!state.symbols().Get(varname).GetType().Equals(value.GetType())) throw new InvalidTypeException("The types of the variable and the expression do not match (" + value.GetType().ToString() + " ≠ " + state.symbols().Get(varname).GetType().ToString() + ").", value.GetType());
        
        state.symbols().Set(varname, value);
        return null;
    }

    @Override
    public String ToString() { return varname + " = " + expression.ToString(); }

    @Override
    public String toString() { return ToString(); }
}