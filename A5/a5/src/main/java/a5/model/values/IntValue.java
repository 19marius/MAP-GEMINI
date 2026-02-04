package a5.model.values;

import a5.model.types.IntType;
import a5.model.types.IType;

public class IntValue implements IValue<Integer>
{
    Integer value;

    public IntValue(int value) { this.value = value; }

    @Override
    public IType GetType() { return new IntType(); }

    @Override
    public Integer GetValue() { return value; }

    @Override
    public String ToString() { return value.toString(); }

    @Override
    public Boolean Equals(IValue<?> other) { return equals(other); }

    // base method overrides

    @Override
    public boolean equals(Object other) { return other instanceof IntValue && GetValue().equals(((IntValue)other).GetValue()); }

    @Override
    public int hashCode() { return super.hashCode(); }
}