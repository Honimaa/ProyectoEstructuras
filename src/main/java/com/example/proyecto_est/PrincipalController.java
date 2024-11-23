package com.example.proyecto_est;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PrincipalController {

    @FXML
    private Label nombre;

    @FXML
    private Accordion accordion; // Referencia al Accordion en el FXML.

    @FXML
    private ImageView imageLibros;

    private Estudiante estudiante;

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;

        if (nombre != null) {
            nombre.setText(estudiante.getUsuario());
        }

        if (accordion != null) {
            populateAccordion();
        }
    }

    @FXML
    private void initialize() {
        // Este método se llama automáticamente después de cargar el FXML.
    }

    @FXML
    private void goToCrearTarea() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CrearTarea.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            CrearTareaController crearTareaController = loader.getController();
            crearTareaController.setEstudiante(estudiante);
            Stage stage = (Stage) imageLibros.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void populateAccordion() {
        accordion.getPanes().clear(); // Limpiar cualquier contenido previo.

        for (Tarea tarea : estudiante.getTareas()) {
            // Crear contenido dinámico para cada TitledPane.
            AnchorPane content = new AnchorPane();
            Label description = new Label("Descripción: " + tarea.getDescripcion());
            Label materia = new Label("Materia: " + tarea.getNombreMateria());
            Label entrega = new Label("Fecha de entrega: " + tarea.getEntrega());
            Label dificultad = new Label("Dificultad: " + tarea.getDificultad());
            Label horasDedicadas = new Label("Horas dedicadas: " + tarea.getHorasDedicadas());
            Label terminado = new Label("¿Terminado?: " + (tarea.isTerminado() ? "Sí" : "No"));

            // Posicionar las etiquetas dentro del AnchorPane.
            description.setLayoutX(10);
            description.setLayoutY(10);
            materia.setLayoutX(10);
            materia.setLayoutY(30);
            entrega.setLayoutX(10);
            entrega.setLayoutY(50);
            dificultad.setLayoutX(10);
            dificultad.setLayoutY(70);
            horasDedicadas.setLayoutX(10);
            horasDedicadas.setLayoutY(90);
            terminado.setLayoutX(10);
            terminado.setLayoutY(110);

            content.getChildren().addAll(description, materia, entrega, dificultad, horasDedicadas, terminado);

            // Crear el TitledPane con el nombre de la tarea.
            TitledPane titledPane = new TitledPane(tarea.getNombre(), content);

            // Añadir el TitledPane al Accordion.
            accordion.getPanes().add(titledPane);
        }
    }
}
