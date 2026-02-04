package a5.model.expressions;

import a5.helpers.StringHelpers;
import a5.model.exceptions.InvalidReferenceException;
import a5.model.exceptions.InvalidTypeException;
import a5.model.ADT.interfaces.IDictionary;
import a5.model.ADT.interfaces.IHeap;
import a5.model.values.RefValue;
import a5.model.types.IType;
import a5.model.types.RefType;
import a5.model.values.IValue;

public record DereferenceExpression(IExpression expression) implements IExpression
{
    @Override
    public IType TypeCheck(IDictionary<String, IType> types) throws InvalidTypeException
    { 
        IType ref = expression.TypeCheck(types);
        if (!ref.Equals(new RefType(null))) throw new InvalidTypeException("Operand must be a reference type.", ref);

        return ((RefType) ref).GetInnerType();
    }
    
    @Override
    public IValue<?> Evaluate(IDictionary<String, IValue<?>> symbols, 
                              IHeap<Integer, IValue<?>> heap)

    throws InvalidTypeException, InvalidReferenceException
    {
        IValue<?> v = expression.Evaluate(symbols, heap);
        if (!v.GetType().Equals(new RefType(null))) throw new InvalidTypeException("Operand " + expression.ToString() + " is not a reference type.", v.GetType());
    
        RefValue ref = (RefValue)v;
        if (!heap.Exists(ref.Address())) throw new InvalidReferenceException("Address " + StringHelpers.ToHex(ref.Address()) + " is not allocated.", ref.ToString());

        return heap.Get(ref.Address());
    }

    @Override
    public String ToString() { return "*" + (expression.CanNest() ? "(" : "") + expression.ToString() + (expression.CanNest() ? ")" : ""); }

    @Override
    public boolean CanNest() { return false; }
}