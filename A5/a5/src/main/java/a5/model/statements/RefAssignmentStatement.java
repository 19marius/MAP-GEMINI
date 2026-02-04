package a5.model.statements;

import a5.helpers.StringHelpers;
import a5.model.ProgramState;
import a5.model.ADT.interfaces.IDictionary;
import a5.model.exceptions.InvalidReferenceException;
import a5.model.exceptions.InvalidTypeException;
import a5.model.expressions.IExpression;
import a5.model.types.IType;
import a5.model.types.RefType;
import a5.model.values.IValue;
import a5.model.values.RefValue;

public record RefAssignmentStatement
(
    String varname,
    IExpression expression
)
implements IStatement
{
    @Override
    public IDictionary<String, IType> TypeCheck(IDictionary<String, IType> types) throws InvalidTypeException
    {
        if (types.Get(varname).Equals(new RefType(expression.TypeCheck(types)))) return types;
        else throw new InvalidTypeException("The types of the variable and the expression do not match (" + types.Get(varname).ToString() + " ≠ " + expression.TypeCheck(types).ToString() + ").", expression.TypeCheck(types));
    }
    
    @Override
    public ProgramState Execute(ProgramState state) throws InvalidReferenceException,
                                                           InvalidTypeException
    {
        IValue<?> v = state.symbols().Get(varname);

        if (!state.symbols().Exists(varname)) throw new InvalidReferenceException("Variable \"" + varname + "\" must be declared before it is assigned to.", varname);
        if (!v.GetType().Equals(new RefType(null))) throw new InvalidTypeException("Variable \"" + varname + "\"must be a reference type.", state.symbols().Get(varname).GetType());
        if (!state.heap().Exists(((RefValue)v).Address())) throw new InvalidReferenceException("Address " + StringHelpers.ToHex(((RefValue)v).Address()) + " is not allocated.", v.ToString());

        IValue<?> value = expression.Evaluate(state.symbols(), state.heap());
        if (!((RefType)v.GetType()).GetInnerType().Equals(value.GetType())) throw new InvalidTypeException("The types of the reference variable and the expression do not match (" + value.GetType().ToString() + " ≠ " + ((RefType)v.GetType()).GetInnerType().ToString() + ").", value.GetType());
        
        state.heap().Set(((RefValue)v).Address(), value);
        return null;
    }

    @Override
    public String ToString() { return "*" + varname + " = " + expression.ToString(); }
}