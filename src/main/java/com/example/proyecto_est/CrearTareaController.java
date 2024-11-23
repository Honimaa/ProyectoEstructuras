package com.example.proyecto_est;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;

public class CrearTareaController {

    @FXML
    private TextField txtNombreTarea;

    @FXML
    private TextField txtMateria;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private DatePicker dpFechaEntrega;

    @FXML
    private Button btnAgregarTarea;

    @FXML
    private Button btnVolver;

    @FXML
    private void agregarTarea() {
        String nombre = txtNombreTarea.getText();
        String materia = txtMateria.getText();
        String descripcion = txtDescripcion.getText();
        var fechaEntrega = dpFechaEntrega.getValue();

        if (nombre.isEmpty() || materia.isEmpty() || fechaEntrega == null) {
            System.out.println("Por favor completa todos los campos obligatorios.");
        } else {
            System.out.println("Tarea agregada exitosamente: " + nombre + " (" + materia + ")");
        }
    }

    @FXML
    private void volver() {
        try {
            HelloApplication.cambiarVista("Productividad.fxml", "EduTimePro - Productividad");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

