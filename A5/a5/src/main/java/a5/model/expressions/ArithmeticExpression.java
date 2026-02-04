package a5.model.expressions;

import a5.model.exceptions.DivideByZeroException;
import a5.model.exceptions.InvalidTypeException;
import a5.model.ADT.interfaces.IDictionary;
import a5.model.ADT.interfaces.IHeap;
import a5.model.values.IntValue;
import a5.model.values.IValue;
import a5.model.types.IType;
import a5.model.types.IntType;

public record ArithmeticExpression
(
    IExpression left, 
    ArithmeticOperator op, 
    IExpression right
) 
implements IExpression
{
    @Override
    public IType TypeCheck(IDictionary<String, IType> types) throws InvalidTypeException
    { 
        IType l = left.TypeCheck(types),r = right.TypeCheck(types);
        if (!l.Equals(new IntType()) || !r.Equals(new IntType())) throw new InvalidTypeException("Operands are not integers.", !l.Equals(new IntType()) ? l : r);

        return new IntType();
    }

    @Override
    public IValue<?> Evaluate(IDictionary<String, IValue<?>> symbols, 
                              IHeap<Integer, IValue<?>>         heap) 
    
    throws InvalidTypeException, RuntimeException
    {
        IValue<?> l, r;
        if (!(l = left.Evaluate(symbols, heap)).GetType().Equals(new IntType())) throw new InvalidTypeException("Left operand is not an integer.", l.GetType());
        if (!(r = right.Evaluate(symbols, heap)).GetType().Equals(new IntType())) throw new InvalidTypeException("Right operand is not an integer.", r.GetType());

        IntValue li = (IntValue)l,
                 ri = (IntValue)r;

        switch (op)
        {
            case PLUS ->     { return new IntValue(li.GetValue() + ri.GetValue()); }
            case MINUS ->    { return new IntValue(li.GetValue() - ri.GetValue()); }
            case MULTIPLY -> { return new IntValue(li.GetValue() * ri.GetValue()); }
            case DIVIDE ->   
            {
                if (ri.GetValue() == 0) throw new DivideByZeroException();
                return new IntValue(li.GetValue() / ri.GetValue()); 
            }
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
            case PLUS -> { return "+"; }
            case MINUS -> { return "-"; }
            case MULTIPLY -> { return "*"; }
            case DIVIDE -> { return "/"; }
        }

        throw new RuntimeException("Symbol does not exist.");
    }
}