package com.example.proyecto_est;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

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

    private Estudiante estudiante;


    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @FXML
    private void agregarTarea() {
        String nombre = txtNombreTarea.getText();
        String materia = txtMateria.getText();
        String descripcion = txtDescripcion.getText();
        var fechaEntrega = dpFechaEntrega.getValue();

        if (nombre.isEmpty() || materia.isEmpty() || fechaEntrega == null) {
            System.out.println("Por favor completa todos los campos obligatorios.");
        } else {
            Tarea tarea = new Tarea(nombre,materia,descripcion,0, fechaEntrega,0,false);
            estudiante.getTareas().add(tarea);
            System.out.println("Tarea agregada exitosamente: " + nombre + " (" + materia + ")");
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                PrincipalController principalController = loader.getController();
                principalController.setEstudiante(estudiante);
                Stage stage = (Stage) btnAgregarTarea.getScene().getWindow();
                stage.setScene(scene);
            }catch (Exception e) {
                e.printStackTrace();
            }
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

