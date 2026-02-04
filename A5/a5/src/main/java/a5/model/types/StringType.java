package a5.model.types;

import a5.model.values.StringValue;
import a5.model.values.IValue;

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