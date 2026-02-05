package a7.model.exceptions;

public class FileAccessException extends RuntimeException
{
    private final String file;

    public FileAccessException(String message, String file)
    {
        super(message);
        this.file = file;
    }

    public String GetFilename() { return file; }
}