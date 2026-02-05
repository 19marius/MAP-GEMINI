package a7.model.expressions;

import a7.model.exceptions.InvalidReferenceException;
import a7.model.types.IType;
import a7.model.ADT.interfaces.IDictionary;
import a7.model.ADT.interfaces.IHeap;
import a7.model.values.IValue;

public record VariableExpression(String varname) implements IExpression
{
    @Override
    public IType TypeCheck(IDictionary<String, IType> types) { return types.Get(varname); }

    @Override
    public IValue<?> Evaluate(IDictionary<String, IValue<?>> symbols, IHeap<Integer, IValue<?>> heap) 
    {
        IValue<?> value = symbols.Get(varname);
        if (value == null) throw new InvalidReferenceException("Variable \"" + varname + "\" does not exist.", varname);

        return value;
    }

    @Override
    public String ToString() { return varname; }

    @Override
    public boolean CanNest() { return false; }
}