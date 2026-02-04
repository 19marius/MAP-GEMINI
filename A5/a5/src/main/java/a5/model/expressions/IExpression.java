package a5.model.expressions;

import a5.model.ADT.interfaces.IDictionary;
import a5.model.ADT.interfaces.IHeap;
import a5.model.types.IType;
import a5.model.values.IValue;

public interface IExpression 
{
    IType TypeCheck(IDictionary<String, IType> types);

    public IValue<?> Evaluate(IDictionary<String, IValue<?>> symbols, IHeap<Integer, IValue<?>> heap);

    public String ToString();

    public boolean CanNest();
}