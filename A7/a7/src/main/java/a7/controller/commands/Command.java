package a7.controller.commands;

public abstract class Command 
{
    private final String key,
                         description;

    public Command(String key, String description)
    {
        this.key = key;
        this.description = description;
    }

    public abstract void Execute();

    public String Key() { return key; }

    public String Description() { return description; }
}