package com.example.proyecto_est;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PrincipalController {

    @FXML
    private Label nombre;

    @FXML
    private Accordion accordion; // Referencia al Accordion en el FXML.

    @FXML
    private ImageView imageLibros;

    @FXML
    private ImageView imageProductividad;

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

    @FXML
    private void goToProductividad() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Productividad.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ProductividadController productividadController = loader.getController();
            productividadController.setEstudiante(estudiante);
            Stage stage = (Stage) imageProductividad.getScene().getWindow();
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void populateAccordion() {
        accordion.getPanes().clear(); // Limpiar cualquier contenido previo.

        for (Tarea tarea : estudiante.getTareas()) {
            // Solo procesar las tareas que no están terminadas
            if (!tarea.isTerminado()) {
                // Crear contenido dinámico para cada TitledPane.
                AnchorPane content = new AnchorPane();
                Label description = new Label("Descripción: " + tarea.getDescripcion());
                Label materia = new Label("Materia: " + tarea.getNombreMateria());
                Label entrega = new Label("Fecha de entrega: " + tarea.getEntrega());
                Label dificultad = new Label("Prioridad: " + tarea.getDificultad());
                Label horasDedicadas = new Label("Horas dedicadas: " + tarea.getHorasDedicadas());
                Label terminado = new Label("¿Terminado?: " + (tarea.isTerminado() ? "Sí" : "No"));

                // Crear el TitledPane con el nombre de la tarea.
                TitledPane titledPane = new TitledPane(tarea.getNombre(), content);

                // Botón para cambiar el estado de "finalizado".
                Button toggleButton = new Button(tarea.isTerminado() ? "Marcar como no terminado" : "Marcar como terminado");
                toggleButton.setLayoutX(10);
                toggleButton.setLayoutY(150);

                // Botón para aumentar las horas dedicadas.
                Button increaseHoursButton = new Button("Añadir 1 hora de trabajo");
                increaseHoursButton.setLayoutX(200);
                increaseHoursButton.setLayoutY(150);

                // Configurar la acción del botón de estado "finalizado".
                toggleButton.setOnAction(event -> {
                    // Cambiar el estado de "terminado" de la tarea.
                    tarea.setTerminado(!tarea.isTerminado());

                    if (tarea.isTerminado()) {
                        // Registrar la fecha y hora de finalización
                        tarea.setFechaTerminacion(LocalDateTime.now());
                        System.out.println(tarea.getFechaTerminacion());
                        accordion.getPanes().remove(titledPane); // Eliminar del accordion cuando esté terminado
                    }

                    // Actualizar el estado del botón y la etiqueta
                    terminado.setText("¿Terminado?: " + (tarea.isTerminado() ? "Sí" : "No"));
                    toggleButton.setText(tarea.isTerminado() ? "Marcar como no terminado" : "Marcar como terminado");
                });

                // Configurar la acción del botón de aumentar horas.
                increaseHoursButton.setOnAction(event -> {
                    // Incrementar las horas dedicadas en 1.
                    tarea.setHorasDedicadas(tarea.getHorasDedicadas() + 1);
                    horasDedicadas.setText("Horas dedicadas: " + tarea.getHorasDedicadas());
                });

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

                // Añadir todos los elementos al AnchorPane.
                content.getChildren().addAll(description, materia, entrega, dificultad, horasDedicadas, terminado, toggleButton, increaseHoursButton);

                // Añadir el TitledPane al Accordion solo si no está terminado.
                accordion.getPanes().add(titledPane);
            }
        }
    }


}
