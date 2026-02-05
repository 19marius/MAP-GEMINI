package a7.model.statements;

import a7.model.ADT.interfaces.IDictionary;
import a7.model.ADT.interfaces.IStack;
import a7.model.exceptions.InvalidTypeException;
import a7.model.types.IType;
import a7.model.ProgramState;
import a7.model.values.IValue;

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

    @Override
    public String toString() { return ToString(); }
}