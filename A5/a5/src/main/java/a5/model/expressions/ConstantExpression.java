package a5.model.expressions;

import a5.model.ADT.interfaces.IDictionary;
import a5.model.ADT.interfaces.IHeap;
import a5.model.types.IType;
import a5.model.values.IValue;

public record ConstantExpression(IValue<?> value) implements IExpression
{
    @Override
    public IType TypeCheck(IDictionary<String, IType> types) { return value.GetType(); }

    @Override
    public IValue<?> Evaluate(IDictionary<String, IValue<?>> symbols, IHeap<Integer, IValue<?>> heap) { return value; }

    @Override
    public String ToString() { return value.ToString(); }
    
    @Override
    public boolean CanNest() { return false; }
}