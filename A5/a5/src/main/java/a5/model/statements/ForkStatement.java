package a5.model.statements;

import a5.model.ADT.interfaces.IDictionary;
import a5.model.ADT.interfaces.IStack;
import a5.model.exceptions.InvalidTypeException;
import a5.model.types.IType;
import a5.model.ProgramState;
import a5.model.values.IValue;

public record ForkStatement(IStatement statement) implements IStatement
{
    @Override
    public IDictionary<String, IType> TypeCheck(IDictionary<String, IType> types) throws InvalidTypeException
    {
        statement.TypeCheck((IDictionary<String, IType>) types.Clone());
        return types;
    }
    
    @Override
    public ProgramState Execute(ProgramState state) 
    {
        IStack<IStatement> execution = (IStack<IStatement>) state.execution().Clone();
        execution.Clear();

        ProgramState program = new ProgramState(statement, 
                                                execution, 
                                                (IDictionary<String, IValue<?>>) state.symbols().Clone(),
                                                state.heap(),
                                                state.output(),
                                                state.files());

        return program;
    }

    @Override
    public String ToString()
    {
        String s = statement().ToString();
        boolean multi_line = s.contains("\n");

        return "fork" + (multi_line ? "\n" : "") + 
               "(" + (multi_line ? "\n   " : "") + 
               s.replace("\n", (multi_line ? "\n   " : "\n"))
                .replace((multi_line ? ' ' : '\n'), ' ') + 
                (multi_line ? "\n" : "") +
               ")";
    }
}