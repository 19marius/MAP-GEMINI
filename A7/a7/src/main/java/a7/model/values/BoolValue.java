package a7.model.values;

import a7.model.types.BoolType;
import a7.model.types.IType;

public class BoolValue implements IValue<Boolean>
{
    Boolean value;

    public BoolValue(boolean value) { this.value = value; }

    @Override
    public IType GetType() { return new BoolType(); }

    @Override
    public Boolean GetValue() { return value; }

    @Override
    public String ToString() { return value.toString(); }

    @Override
    public Boolean Equals(IValue<?> other) { return equals(other); }

    // base method overrides

    @Override
    public boolean equals(Object other) { return other instanceof BoolValue && GetValue().equals(((BoolValue)other).GetValue()); }

    @Override
    public int hashCode() { return super.hashCode(); }
}