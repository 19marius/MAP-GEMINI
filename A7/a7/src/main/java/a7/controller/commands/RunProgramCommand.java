package a7.controller.commands;

import a7.model.exceptions.CommandException;
import a7.controller.Controller;

public class RunProgramCommand extends Command
{
    private final Controller controller;

    public Controller Controller() { return controller; }

    public RunProgramCommand(String key, String description, Controller controller)
    {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void Execute() throws CommandException
    {
        try { controller.ExecuteAll(); }
        catch (Exception ex) { throw new CommandException("Error while running a program.", ex); }
    }
}