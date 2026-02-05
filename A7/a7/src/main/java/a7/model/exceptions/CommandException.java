package a7.model.exceptions;

public class CommandException extends RuntimeException 
{
    private final Exception inner;

    public CommandException(String message, Exception inner)
    {
        super(message);
        this.inner = inner;
    }

    public Exception InnerException() { return inner; }
}