package a7.model.types;

import a7.model.values.IValue;

public interface IType 
{
    public IValue<?> Default();

    public boolean Equals(IType other);

    public String ToString();
}