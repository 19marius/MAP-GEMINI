package a7.model.expressions;

import a7.model.exceptions.InvalidTypeException;
import a7.model.ADT.interfaces.IDictionary;
import a7.model.ADT.interfaces.IHeap;
import a7.model.values.BoolValue;
import a7.model.values.IValue;
import a7.model.types.BoolType;
import a7.model.types.IType;

public record LogicalExpression
(
    IExpression left, 
    LogicalOperator op, 
    IExpression right
) 
implements IExpression
{
    @Override
    public IType TypeCheck(IDictionary<String, IType> types) throws InvalidTypeException
    { 
        IType l = left.TypeCheck(types),r = right.TypeCheck(types);
        if (!l.Equals(new BoolType()) || !r.Equals(new BoolType())) throw new InvalidTypeException("Operands are not bools.", !l.Equals(new BoolType()) ? l : r);

        return new BoolType();
    }

    @Override
    public IValue<?> Evaluate(IDictionary<String, IValue<?>> symbols, 
                              IHeap<Integer, IValue<?>> heap) 
    
    throws InvalidTypeException, RuntimeException
    {
        IValue<?> l, r;
        if (!(l = left.Evaluate(symbols, heap)).GetType().Equals(new BoolType())) throw new InvalidTypeException("Left operand is not a bool.", l.GetType());
        if (!(r = right.Evaluate(symbols, heap)).GetType().Equals(new BoolType())) throw new InvalidTypeException("Right operand is not a bool.", r.GetType());

        BoolValue li = (BoolValue)l,
                  ri = (BoolValue)r;

        switch (op)
        {
            case AND -> { return new BoolValue(li.GetValue() && ri.GetValue()); }
            case OR ->  { return new BoolValue(li.GetValue() || ri.GetValue()); }
        }

        throw new RuntimeException("Evaluation was unsuccessful.");
    }

    @Override
    public String ToString() { return left.ToString() + " " + OperatorSymbol() + " " + right.ToString(); }

    @Override
    public boolean CanNest() { return true; }

    public String OperatorSymbol()
    {
        switch (op)
        {
            case AND -> { return "&&"; }
            case OR -> { return "||"; }
        }

        throw new RuntimeException("Symbol does not exist.");
    }
}