module com.example.boardingpass {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.junit.jupiter.api;

    opens com.example.boardingpass to javafx.fxml;
    exports com.example.boardingpass;
}