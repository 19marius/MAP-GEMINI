package a7.model.expressions;

import a7.model.ADT.interfaces.IDictionary;
import a7.model.ADT.interfaces.IHeap;
import a7.model.types.IType;
import a7.model.values.IValue;

public interface IExpression 
{
    IType TypeCheck(IDictionary<String, IType> types);

    public IValue<?> Evaluate(IDictionary<String, IValue<?>> symbols, IHeap<Integer, IValue<?>> heap);

    public String ToString();

    public boolean CanNest();
}