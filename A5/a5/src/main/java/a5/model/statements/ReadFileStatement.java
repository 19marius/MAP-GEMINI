package a5.model.statements;

import java.io.BufferedReader;
import java.io.IOException;

import a5.helpers.StringHelpers;
import a5.model.ProgramState;
import a5.model.ADT.interfaces.IDictionary;
import a5.model.exceptions.FileAccessException;
import a5.model.exceptions.InvalidReferenceException;
import a5.model.exceptions.InvalidTypeException;
import a5.model.expressions.IExpression;
import a5.model.types.IType;
import a5.model.types.IntType;
import a5.model.types.StringType;
import a5.model.values.IValue;
import a5.model.values.IntValue;
import a5.model.values.StringValue;

public record ReadFileStatement
(
    IExpression expression,
    String varname
)
implements IStatement 
{
    @Override
    public IDictionary<String, IType> TypeCheck(IDictionary<String, IType> types) throws InvalidTypeException
    {
        if (!expression.TypeCheck(types).Equals(new StringType())) throw new InvalidTypeException("Expression must be a string.", expression.TypeCheck(types));
        if (!types.Get(varname).Equals(new IntType())) throw new InvalidTypeException("Variable is not an integer.", types.Get(varname));
        
        return types;
    }

    @Override
    public ProgramState Execute(ProgramState state) throws InvalidReferenceException,
                                                           InvalidTypeException,
                                                           FileAccessException
    {
        if (!state.symbols().Exists(varname)) throw new InvalidReferenceException("Variable \"" + varname + "\" does not exist.", varname);
        if (!state.symbols().Get(varname).GetType().Equals(new IntType())) throw new InvalidTypeException("Referenced variable \"" + varname + "\" must be an integer.", state.symbols().Get(varname).GetType());

        IValue<?> v = expression.Evaluate(state.symbols(), state.heap());
        if (!v.GetType().Equals(new StringType())) throw new InvalidTypeException("Expression " + expression.ToString() + " must be a string.", v.GetType());

        StringValue key = (StringValue) v;
        BufferedReader reader = state.files().Get(key);
        
        try
        {
            String line = reader.readLine();
            state.symbols().Set(varname, new IntValue(StringHelpers.IsNullOrEmpty(line) ? 0 : Integer.parseInt(line)));
        }
        catch (IOException ex) { throw new FileAccessException("Unknown error occured when reading from the file \"" + key.GetValue() + "\" (" + ex.getMessage() + ")", key.GetValue()); }
    
        return null;
    }

    @Override
    public String ToString() { return "read_file(" + expression.ToString() + ", " + varname + ")"; }
}