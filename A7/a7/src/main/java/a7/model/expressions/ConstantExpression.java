package a7.model.expressions;

import a7.model.ADT.interfaces.IDictionary;
import a7.model.ADT.interfaces.IHeap;
import a7.model.types.IType;
import a7.model.values.IValue;

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