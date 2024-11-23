package com.example.proyecto_est;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

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

    public void inicializar() {
        cmbPrioridad.getItems().addAll("Tarea 1", "Tarea 2", "Tarea 3");
        cmbPausasActivas.getItems().addAll("15 minutos", "30 minutos", "1 hora");
    }

    @FXML
    private void irCrearTarea() {
        try {
            HelloApplication.cambiarVista("CrearTarea.fxml", "EduTimePro - Crear Tarea");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

