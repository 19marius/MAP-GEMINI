package a7.model.exceptions;

public class LoggingException extends RuntimeException
{
    private final String path;

    public LoggingException(String path)
    {
        super("Could not log to the file at " + path);
        this.path = path;
    }

    public String GetInvalidPath() { return path; }
}