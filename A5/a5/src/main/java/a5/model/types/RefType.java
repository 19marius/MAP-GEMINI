package a5.model.types;

import a5.model.values.IValue;
import a5.model.values.RefValue;

public class RefType implements IType
{
    private final IType inner;

    public RefType(IType inner) { this.inner = inner; }

    public IType GetInnerType() { return inner; }

    @Override
    public IValue<?> Default() { return new RefValue(0, inner); }

    @Override
    public boolean Equals(IType other) { return equals(other); }

    @Override
    public String ToString() { return "ref " + inner.ToString(); }

    // base method overrides

    @Override
    public boolean equals(Object other) { return other instanceof RefType && (((RefType)other).inner == null || inner.equals(((RefType)other).inner)); }

    @Override
    public int hashCode() { return super.hashCode(); }
}