package a5.model.values;

import a5.model.types.StringType;
import a5.model.types.IType;

public class StringValue implements IValue<String>
{
    String value;

    public StringValue(String value) { this.value = value; }

    @Override
    public IType GetType() { return new StringType(); }

    @Override
    public String GetValue() { return value; }

    @Override
    public String ToString() { return "\"" + value + "\""; }

    @Override
    public Boolean Equals(IValue<?> other) { return equals(other); }

    // base method overrides

    @Override
    public boolean equals(Object other) { return other instanceof StringValue && GetValue().equals(((StringValue)other).GetValue()); }

    @Override
    public int hashCode() { return super.hashCode(); }
}