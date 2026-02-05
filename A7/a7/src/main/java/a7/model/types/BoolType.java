package a7.model.types;

import a7.model.values.BoolValue;
import a7.model.values.IValue;

public class BoolType implements IType
{
    @Override
    public IValue<?> Default() { return new BoolValue(false); }
    
    @Override
    public boolean Equals(IType other) { return other instanceof BoolType; }

    @Override
    public String ToString() { return "bool"; }

    // base method overrides

    @Override
    public boolean equals(Object other) { return other instanceof IntType; }

    @Override
    public int hashCode() { return super.hashCode(); }
}