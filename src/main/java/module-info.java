module com.example.proyecto_est {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyecto_est to javafx.fxml;
    exports com.example.proyecto_est;
}