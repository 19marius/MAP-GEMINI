package a7.controller.commands;

public class ExitCommand extends Command
{
    public ExitCommand(String key, String description) { super(key, description); }

    @Override
    public void Execute() { System.exit(0); }
}