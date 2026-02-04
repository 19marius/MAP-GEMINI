package a5.model.values;

import a5.helpers.StringHelpers;
import a5.model.types.IType;
import a5.model.types.RefType;

public class RefValue implements IValue<Integer> 
{
    int address;
    IType type;

    public RefValue(int address, IType type)
    {
        this.address = address;
        this.type = type;
    }

    public Integer Address() { return GetValue(); }

    @Override
    public IType GetType() { return new RefType(type); }

    @Override
    public Integer GetValue() { return address; }

    @Override
    public String ToString() { return "(" + StringHelpers.ToHex(address) + ":" + type.ToString() + ")"; }

    @Override
    public Boolean Equals(IValue<?> other) { return equals(other); }

    // base method overrides

    @Override
    public boolean equals(Object other) { return other instanceof RefValue && GetValue().equals(((RefValue)other).GetValue()); }

    @Override
    public int hashCode() { return super.hashCode(); }
}