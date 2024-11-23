package com.example.proyecto_est;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class ProductividadController {

    @FXML
    private Label lblUltimaTarea;

    @FXML
    private ComboBox<String> cmbPrioridad;

    @FXML
    private ComboBox<String> cmbPausasActivas;

    @FXML
    private Label lblNivelProductividad;

    @FXML
    private Button btnIrCrearTarea;

    @FXML
    private Estudiante estudiante;

    @FXML
    private Button btnVolver;

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;

        lblUltimaTarea.setText(obtenerTareaFinalizadaReciente());
    }

    public String obtenerTareaFinalizadaReciente() {
        Estudiante estudiante = this.estudiante;
        Tarea tareaMasReciente = null; // Inicializa la tarea más reciente como null
        LocalDateTime fechaMasReciente = null; // Inicializa la fecha más reciente como null

        for (Tarea tarea : estudiante.getTareas()) {
            // Verifica si la tarea tiene una fecha de terminación y si es más reciente que la actual
            if (tarea.getFechaTerminacion() != null &&
                    (fechaMasReciente == null || tarea.getFechaTerminacion().isAfter(fechaMasReciente))) {
                tareaMasReciente = tarea; // Actualiza la tarea más reciente
                fechaMasReciente = tarea.getFechaTerminacion(); // Actualiza la fecha más reciente
            }
        }
        return tareaMasReciente.getNombre();
    }



    @FXML
    private void volver() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            PrincipalController principalController = loader.getController();
            principalController.setEstudiante(estudiante);
            Stage stage = (Stage) btnVolver.getScene().getWindow();
            stage.setScene(scene);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

