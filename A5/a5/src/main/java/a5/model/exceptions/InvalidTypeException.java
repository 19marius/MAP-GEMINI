package a5.model.exceptions;

import a5.model.types.IType;

public class InvalidTypeException extends RuntimeException
{
    private final IType type;

    public InvalidTypeException(String message, IType type) 
    { 
        super(message); 
        this.type = type; 
    }

    public IType GetType() { return type; }
}