package a5.model.statements;

import a5.model.ProgramState;
import a5.model.ADT.interfaces.IDictionary;
import a5.model.exceptions.InvalidTypeException;
import a5.model.expressions.IExpression;
import a5.model.types.BoolType;
import a5.model.types.IType;
import a5.model.values.BoolValue;
import a5.model.values.IValue;


public record ConditionalStatement
(
    IExpression condition,
    IStatement consequent,
    IStatement alternative
) 
implements IStatement
{
    @Override
    public IDictionary<String, IType> TypeCheck(IDictionary<String, IType> types) throws InvalidTypeException
    {
        if (!condition.TypeCheck(types).Equals(new BoolType())) throw new InvalidTypeException("Condition is not a bool type.", condition.TypeCheck(types));
        
        consequent.TypeCheck((IDictionary<String, IType>) types.Clone());
        alternative.TypeCheck((IDictionary<String, IType>) types.Clone());

        return types;
    }

    @Override
    public ProgramState Execute(ProgramState state) throws InvalidTypeException
    {
        IValue<?> c = condition.Evaluate(state.symbols(), state.heap());
        if (!c.GetType().Equals(new BoolType())) throw new InvalidTypeException("Condition expression " + condition.ToString() + " must be a bool.", c.GetType());

        BoolValue cb = (BoolValue)c;
        state.execution().Push(cb.GetValue() ? consequent : alternative);

        return null;
    }

    @Override
    public String ToString() { return "if (" + condition.ToString() + ")\n{\n  " + consequent.ToString().replace("\n", "\n  ") + ";\n}\nelse\n{\n  " + alternative.ToString().replace("\n", "\n  ") + ";\n}"; }
}