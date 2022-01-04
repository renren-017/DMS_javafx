module test.dmsfinal {
    requires javafx.controls;
    requires javafx.fxml;


    opens test.dmsfinal to javafx.fxml;
    exports test.dmsfinal;
}