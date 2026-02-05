package a7.view.controls;

import java.io.IOException;
import java.util.ArrayList;

import a7.App;
import a7.controller.Controller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class ProgramSelectionControl
{
    @FXML private ListView<Controller> programs_list_view;
    @FXML private Button run_button;

    @FXML
    public void initialize() 
    {
        ArrayList<Controller> programs = new ArrayList<>();
        App.Programs().ForEach(x -> programs.add(x));
        programs_list_view.setItems(FXCollections.observableArrayList(programs));
    }

    @FXML
    public void OnRunButtonAction(ActionEvent event) 
    {
        Controller selected = programs_list_view.getSelectionModel().getSelectedItem();
        if (selected == null) 
        {
            new Alert(Alert.AlertType.WARNING, "Please select a program.").show();
            return;
        }

        try 
        {
            selected.InitializeExecutor();

            FXMLLoader loader = new FXMLLoader(App.class.getResource("MainWindow.fxml"));
            Parent root = loader.load();

            MainWindowControl main = loader.getController();
            main.SetController(selected);

            Stage stage = new Stage();
            stage.setTitle("Program Execution");
            stage.setScene(new Scene(root));
            
            stage.setOnCloseRequest(e -> selected.ShutdownExecutor());
            stage.show();

        }
        catch (IOException e) { new Alert(Alert.AlertType.ERROR, "Could not load program: " + e.getMessage()).show(); }
    }
}