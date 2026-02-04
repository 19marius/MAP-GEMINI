package a5.model.statements;

import java.io.IOException;

import a5.model.ProgramState;
import a5.model.ADT.interfaces.IDictionary;
import a5.model.exceptions.FileAccessException;
import a5.model.exceptions.InvalidTypeException;
import a5.model.expressions.IExpression;
import a5.model.types.IType;
import a5.model.types.StringType;
import a5.model.values.IValue;
import a5.model.values.StringValue;

public record CloseReadFileStatement(IExpression expression) implements IStatement
{
    @Override
    public IDictionary<String, IType> TypeCheck(IDictionary<String, IType> types) throws InvalidTypeException
    {
        if (!expression.TypeCheck(types).Equals(new StringType())) throw new InvalidTypeException("Expression must be a string.", expression.TypeCheck(types));
        return types;
    }

    @Override
    public ProgramState Execute(ProgramState state) throws InvalidTypeException,
                                                           FileAccessException
    {
        IValue<?> v = expression.Evaluate(state.symbols(), state.heap());
        if (!v.GetType().Equals(new StringType())) throw new InvalidTypeException("Expression " + expression.ToString() + " must be a string.", v.GetType());

        StringValue key = (StringValue) v;
        if (!state.files().Exists(key)) throw new FileAccessException("File \"" + key.GetValue() + "\" does not exist.", key.GetValue());
        
        try { state.files().Get(key).close(); }
        catch (IOException ex) { throw new FileAccessException("Unknown error occurred when closing the file \"" + key.GetValue() + "\"(" + ex.getMessage() + ")", key.GetValue()); }

        state.files().Remove(key);
        return null;
    }

    @Override
    public String ToString() { return "close_read_file(" + expression.ToString() + ")"; }
}