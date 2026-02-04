package a5.model.values;

import a5.model.types.IType;

public interface IValue<T> 
{
    public IType GetType();

    public T GetValue();

    public String ToString();

    public Boolean Equals(IValue<?> other);
}