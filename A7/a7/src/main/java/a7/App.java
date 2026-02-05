package a7;


import java.io.IOException;
import java.util.Scanner;

import a7.controller.Controller;
import a7.controller.commands.RunProgramCommand;
import a7.model.ADT.interfaces.IList;
import a7.view.CommandMenu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application 
{
    private static IList<Controller> programs;

    @Override
    public void start(Stage stage) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProgramSelection.fxml"));
        Parent root = loader.load();

        stage.setTitle("Language Interpreter");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static IList<Controller> Programs() { return programs; }

    public static void main(String[] args)
    {
        System.out.println(CommandMenu.CLEAR);
        
        try (Scanner sc = new Scanner(System.in))
        {
            System.out.print("Run in console? (y/n): ");
            String ans = sc.nextLine();

            while (!ans.equals("y") && !ans.equals("n"))
            {
                System.out.println(CommandMenu.BARRIER);
                System.out.println("Invalid option.");
                System.out.println(CommandMenu.BARRIER);

                System.out.print("Run in console? (y/n): ");
                ans = sc.nextLine();
            }

            programs = Interpreter.Run(ans.equals("y")).Where(x -> x instanceof RunProgramCommand).Select(x -> ((RunProgramCommand)x).Controller()).ToList();
            System.out.println(CommandMenu.CLEAR);
        }

        launch();
    }
}