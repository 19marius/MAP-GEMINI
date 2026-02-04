package a5.model.exceptions;

public class InvalidReferenceException extends RuntimeException
{
    private final String varname;

    public InvalidReferenceException(String message, String varname) 
    { 
        super(message); 
        this.varname = varname;
    }

    public String GetVariableName() { return varname; }
}