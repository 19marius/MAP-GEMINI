package a7.model.statements;

import a7.model.exceptions.InvalidTypeException;
import a7.model.ADT.interfaces.IDictionary;
import a7.model.expressions.IExpression;
import a7.model.values.BoolValue;
import a7.model.types.BoolType;
import a7.model.values.IValue;
import a7.model.ProgramState;
import a7.model.types.IType;

public record WhileStatement
(
    IExpression condition,
    IStatement consequent
) 
implements IStatement
{
    @Override
    public IDictionary<String, IType> TypeCheck(IDictionary<String, IType> types) throws InvalidTypeException
    {
        if (!condition.TypeCheck(types).Equals(new BoolType())) throw new InvalidTypeException("Condition is not a bool type.", condition.TypeCheck(types));
        
        consequent.TypeCheck((IDictionary<String, IType>) types.Clone());
        return types;
    }
    
    @Override
    public ProgramState Execute(ProgramState state) throws InvalidTypeException
    {
        IValue<?> c = condition.Evaluate(state.symbols(), state.heap());
        if (!c.GetType().Equals(new BoolType())) throw new InvalidTypeException("Condition expression " + condition.ToString() + " must be a bool.", c.GetType());

        if (!((BoolValue)c).GetValue()) return null;

        state.execution().Push(this);
        state.execution().Push(consequent);
        
        return null;
    }

    @Override
    public String ToString() { return "while (" + condition.ToString() + ")\n{\n  " + consequent.ToString().replace("\n", "\n  ") + ";\n}"; }
}