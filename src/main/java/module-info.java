module com.example.front {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.front to javafx.fxml;
    exports com.example.front;
    exports com.example.back;
    opens com.example.back to javafx.fxml;
}