package a5.model.statements;

import a5.model.ProgramState;
import a5.model.ADT.interfaces.IDictionary;
import a5.model.expressions.IExpression;
import a5.model.types.IType;

public record PrintStatement(IExpression expression) implements IStatement
{
    @Override
    public IDictionary<String, IType> TypeCheck(IDictionary<String, IType> types) 
    {
        expression.TypeCheck(types);
        return types;
    }

    @Override
    public ProgramState Execute(ProgramState state) 
    {
        state.output().Append(expression.Evaluate(state.symbols(), state.heap()));
        return null;
    }

    @Override
    public String ToString() { return "print(" + expression.ToString() + ")"; }
}