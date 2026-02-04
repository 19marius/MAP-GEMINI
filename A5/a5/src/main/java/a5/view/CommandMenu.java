package a5.view;

import java.util.Scanner;

import a5.model.ADT.implementations.Dictionary;
import a5.model.exceptions.CommandException;
import a5.model.ADT.interfaces.IDictionary;
import a5.controller.commands.Command;
import a5.helpers.StringHelpers;

public final class CommandMenu
{
    final String BARRIER = StringHelpers.Repeat('-', 100);
    final String CLEAR = StringHelpers.Repeat('\n', 100);

    private final IDictionary<String, Command> commands;
    private final Scanner sc;

    private boolean init = false;

    public CommandMenu() 
    {
        this.commands = new Dictionary<>();
        sc = new Scanner(System.in);
    }

    public void Initialize()
    {
        if (init) return;
        init = true;

        do { DisplayMenu(); } while (true);
    }

    public void AddCommand(Command cmd) { commands.Set(cmd.Key(), cmd); }

    private void DisplayMenu()
    {
        System.out.println(CLEAR);
        commands.ForEach(x -> System.out.println(x.first + " - " + x.second.Description()));
        System.out.println(BARRIER);
        
        System.out.print("Choice: ");
        String option = sc.nextLine();
        
        if (commands.Exists(option))
        {
            try 
            {
                System.out.println(CLEAR);
                commands.Get(option).Execute(); 
            }
            catch (CommandException ex)
            {
                System.out.println(BARRIER);

                String message = ex.InnerException().getMessage();
                System.out.println("Something went wrong (" + ex.InnerException().getClass().getSimpleName() + (StringHelpers.IsNullOrEmpty(message) ? "" : ": " + message)  + ")");
            }
        }
        else System.out.println("\nNo such command exists.");

        System.out.println(BARRIER);
        Break();
    }

    private void Break()
    {
        System.out.println("Press Enter key to continue . . .");
        sc.nextLine(); 
    }
}