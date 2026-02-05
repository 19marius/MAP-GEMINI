package a7;

import a7.controller.commands.*;

import a7.model.ADT.interfaces.IList;
import a7.model.ProgramTemplates;

import a7.view.CommandMenu;

public class Interpreter
{
    public static IList<Command> Run(boolean with_console)
    {
        String log_path = "C:\\Users\\marius\\Desktop\\log.txt";
    
        CommandMenu menu = new CommandMenu();

        menu.AddCommand(new ExitCommand      ("0", "Exit"));
        menu.AddCommand(new RunProgramCommand("1", "Simple assignment template", ProgramTemplates.TemplateOne(log_path)));
        menu.AddCommand(new RunProgramCommand("2", "Arithmetic expressions template", ProgramTemplates.TemplateTwo(log_path)));
        menu.AddCommand(new RunProgramCommand("3", "Conditional statement template", ProgramTemplates.TemplateThree(log_path)));
        menu.AddCommand(new RunProgramCommand("4", "File read template", ProgramTemplates.TemplateFour(log_path)));
        menu.AddCommand(new RunProgramCommand("5", "Relational expression template", ProgramTemplates.TemplateFive(log_path)));
        menu.AddCommand(new RunProgramCommand("6", "Heap allocation template", ProgramTemplates.TemplateSix(log_path)));
        menu.AddCommand(new RunProgramCommand("7", "Dereference template", ProgramTemplates.TemplateSeven(log_path)));
        menu.AddCommand(new RunProgramCommand("8", "Reference assignment template", ProgramTemplates.TemplateEight(log_path)));
        menu.AddCommand(new RunProgramCommand("9", "GC template", ProgramTemplates.TemplateNine(log_path)));
        menu.AddCommand(new RunProgramCommand("10", "While statement template", ProgramTemplates.TemplateTen(log_path)));
        menu.AddCommand(new RunProgramCommand("11", "Fork template", ProgramTemplates.TemplateEleven(log_path)));
    
        if (with_console) menu.Initialize();
        return menu.GetCommands();
    }
}