module a7 
{
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens a7 to javafx.fxml;
    exports a7;
}