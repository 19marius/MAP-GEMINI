package a7.view.controls;

import a7.controller.Controller;
import a7.helpers.Pair;
import a7.model.ProgramState;
import a7.model.values.IValue;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import a7.helpers.StringHelpers;
import a7.model.ADT.interfaces.IList;

@SuppressWarnings("unused")
public class MainWindowControl 
{
    private Controller controller;

    @FXML private TextField noOfProgStates;
    @FXML private TableView<Pair<Integer, IValue<?>>> heapTable;
    @FXML private TableColumn<Pair<Integer, IValue<?>>, String> heapAddressColumn;
    @FXML private TableColumn<Pair<Integer, IValue<?>>, String> heapValueColumn;
    
    @FXML private ListView<String> outputList;
    @FXML private ListView<String> fileList;
    
    @FXML private ListView<Integer> progIdentifiersList;
    
    @FXML private TableView<Pair<String, IValue<?>>> symTable;
    @FXML private TableColumn<Pair<String, IValue<?>>, String> symVarNameColumn;
    @FXML private TableColumn<Pair<String, IValue<?>>, String> symValueColumn;
    
    @FXML private ListView<String> execStackList;
    @FXML private Button runOneStepButton;

    public void SetController(Controller controller) 
    {
        this.controller = controller;
        populate();
    }

    @FXML
    public void initialize() 
    {
        // Setup Heap Table columns
        
        heapAddressColumn.setCellValueFactory(p -> new SimpleStringProperty(StringHelpers.ToHex(p.getValue().first)));
        heapValueColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().second.ToString()));

        // Setup Symbol Table columns

        symVarNameColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().first));
        symValueColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().second.ToString()));

        // Listener for Program ID selection to update symbol table and execution stack

        progIdentifiersList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> { if (newVal != null) populateProgStateDetails(newVal); });
    }

    @FXML
    public void handleRunOneStep(ActionEvent event) 
    {
        if (controller == null) 
        {
            new Alert(Alert.AlertType.ERROR, "Controller not initialized").show();
            return;
        }

        try 
        {
            IList<ProgramState> programs = controller.Repository().GetPrograms().Where(x -> !x.IsCompleted()).ToList();
            if (programs.Length() == 0) 
            {
                new Alert(Alert.AlertType.INFORMATION, "Execution finished!").show();
                return;
            }

            controller.ParallelSingleStep(programs);
            populate();

        } 
        catch (Exception e) { new Alert(Alert.AlertType.ERROR, e.getMessage()).show(); }
    }

    private void populate() 
    {
        // Populate text field

        List<ProgramState> programs = controller.Repository().GetPrograms().Vanilla();
        noOfProgStates.setText(String.valueOf(programs.size()));

        if (programs.isEmpty()) return;

        ProgramState firstProgram = programs.get(0);

        // Populate Heap (Global)

        List<Pair<Integer, IValue<?>>> heapEntries = new ArrayList<>();
        firstProgram.heap().ForEach(x -> heapEntries.add(x));
        heapTable.setItems(FXCollections.observableArrayList(heapEntries));

        // Populate Output (Global)

        List<String> outEntries = new ArrayList<>();
        firstProgram.output().ForEach(v -> outEntries.add(v.ToString()));
        outputList.setItems(FXCollections.observableArrayList(outEntries));

        // Populate Files (Global)

        List<String> filesEntries = new ArrayList<>();
        firstProgram.files().ForEach(p -> filesEntries.add(p.first.ToString()));
        fileList.setItems(FXCollections.observableArrayList(filesEntries));

        // Populate IDs

        List<Integer> ids = programs.stream().map(ProgramState::ID).collect(Collectors.toList());
        progIdentifiersList.setItems(FXCollections.observableArrayList(ids));

        // Refresh currently selected ID details if it still exists

        Integer selectedId = progIdentifiersList.getSelectionModel().getSelectedItem();
        if (selectedId != null && ids.contains(selectedId)) populateProgStateDetails(selectedId);
        else if (!ids.isEmpty()) progIdentifiersList.getSelectionModel().selectFirst();
    }

    private void populateProgStateDetails(Integer id) 
    {
        ProgramState selectedState = null;
        for (ProgramState p : controller.Repository().GetPrograms().Vanilla()) 
        {
            if (p.ID() != id) continue;

            selectedState = p;
            break;
        }

        if (selectedState == null) return; 
        
        // Populate SymTable
    
        List<Pair<String, IValue<?>>> symEntries = new ArrayList<>();
        selectedState.symbols().ForEach(x -> symEntries.add(x));
        symTable.setItems(FXCollections.observableArrayList(symEntries));

        // Populate Execution Stack
    
        List<String> stackEntries = new ArrayList<>();
        selectedState.execution().ForEach(s -> stackEntries.add(s.ToString()));
        execStackList.setItems(FXCollections.observableArrayList(stackEntries));
    }
}