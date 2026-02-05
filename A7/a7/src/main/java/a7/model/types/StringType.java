package a7.model.types;

import a7.model.values.StringValue;
import a7.model.values.IValue;

public class StringType implements IType
{
    @Override
    public IValue<?> Default() { return new StringValue(""); }

    @Override
    public boolean Equals(IType other) { return other instanceof StringType; }

    @Override
    public String ToString() { return "string"; }

    // base method overrides

    @Override
    public boolean equals(Object other) { return other instanceof StringType; }

    @Override
    public int hashCode() { return super.hashCode(); }
}