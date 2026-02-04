package a5.model.types;

import a5.model.values.IValue;

public interface IType 
{
    public IValue<?> Default();

    public boolean Equals(IType other);

    public String ToString();
}