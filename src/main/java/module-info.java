module com.example.boardingpass {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.boardingpass to javafx.fxml;
    exports com.example.boardingpass;
}