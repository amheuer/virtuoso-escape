module org.virtuoso.escape {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens org.virtuoso.escape.model to javafx.fxml;
    opens org.virtuoso.escape to javafx.fxml;
    exports org.virtuoso.escape;
}