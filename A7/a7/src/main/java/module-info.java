module a7 
{
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens a7 to javafx.fxml;
    opens a7.view.controls to javafx.fxml;
    
    exports a7;
    exports a7.view.controls;
    exports a7.controller.commands;
}