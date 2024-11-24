module com.example.proyecto_est {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.proyecto_est to javafx.fxml;
    exports com.example.proyecto_est;
}