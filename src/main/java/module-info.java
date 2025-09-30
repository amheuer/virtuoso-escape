module org.virtuoso.escape.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens org.virtuoso.escape.model to javafx.fxml;
    opens org.virtuoso.escape.gui to javafx.fxml;
    exports org.virtuoso.escape.gui;
}