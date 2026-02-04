package a5.model.statements;

import a5.model.ProgramState;
import a5.model.ADT.interfaces.IDictionary;
import a5.model.exceptions.InvalidReferenceException;
import a5.model.exceptions.InvalidTypeException;
import a5.model.expressions.IExpression;
import a5.model.types.IType;
import a5.model.values.IValue;

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
}