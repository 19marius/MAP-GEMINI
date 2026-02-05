package a7.model.types;

import a7.model.values.IntValue;
import a7.model.values.IValue;

public class IntType implements IType
{
    @Override
    public IValue<?> Default() { return new IntValue(0); }

    @Override
    public boolean Equals(IType other) { return other instanceof IntType; }

    @Override
    public String ToString() { return "int"; }

    // base method overrides

    @Override
    public boolean equals(Object other) { return other instanceof IntType; }

    @Override
    public int hashCode() { return super.hashCode(); }
}