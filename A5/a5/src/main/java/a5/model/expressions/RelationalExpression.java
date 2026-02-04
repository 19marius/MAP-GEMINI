package a5.model.expressions;

import a5.model.ADT.interfaces.IDictionary;
import a5.model.ADT.interfaces.IHeap;
import a5.model.exceptions.InvalidTypeException;
import a5.model.types.BoolType;
import a5.model.types.IType;
import a5.model.types.IntType;
import a5.model.values.BoolValue;
import a5.model.values.IValue;
import a5.model.values.IntValue;

public record RelationalExpression
(
    IExpression left,
    RelationalOperator op,
    IExpression right
)
implements IExpression
{
    @Override
    public IType TypeCheck(IDictionary<String, IType> types) throws InvalidTypeException
    { 
        IType l = left.TypeCheck(types),r = right.TypeCheck(types);
        if (!l.Equals(new IntType()) || !r.Equals(new IntType())) throw new InvalidTypeException("Operands are not integers.", !l.Equals(new IntType()) ? l : r);

        return new BoolType();
    }

    @Override
    public IValue<?> Evaluate(IDictionary<String, IValue<?>> symbols, IHeap<Integer, IValue<?>> heap) 
    {
        IValue<?> l, r;
        if (!(l = left.Evaluate(symbols, heap)).GetType().Equals(new IntType())) throw new InvalidTypeException("Left operand is not an integer.", l.GetType());
        if (!(r = right.Evaluate(symbols, heap)).GetType().Equals(new IntType())) throw new InvalidTypeException("Right operand is not an integer.", r.GetType());

        IntValue li = (IntValue)l,
                 ri = (IntValue)r;

        switch (op)
        {
            case LESS ->       { return new BoolValue(li.GetValue() < ri.GetValue());  }
            case LESS_EQUAL -> { return new BoolValue(li.GetValue() <= ri.GetValue()); }

            case GREATER ->       { return new BoolValue(li.GetValue() > ri.GetValue());  }
            case GREATER_EQUAL -> { return new BoolValue(li.GetValue() >= ri.GetValue()); }

            case EQUAL ->     { return new BoolValue(li.GetValue().equals(ri.GetValue()));  }
            case NOT_EQUAL -> { return new BoolValue(!li.GetValue().equals(ri.GetValue())); }
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
            case LESS -> { return "<"; }
            case LESS_EQUAL -> { return "<="; }
            
            case GREATER -> { return ">"; }
            case GREATER_EQUAL -> { return ">="; }
            
            case EQUAL -> { return "=="; }
            case NOT_EQUAL -> { return "!="; }
        }

        throw new RuntimeException("Symbol does not exist.");
    }
}