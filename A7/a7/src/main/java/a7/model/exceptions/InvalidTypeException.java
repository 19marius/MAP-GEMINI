package a7.model.exceptions;

import a7.model.types.IType;

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