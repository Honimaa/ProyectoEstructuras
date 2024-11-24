package com.example.proyecto_est;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class ProductividadController {

    @FXML
    private Label lblUltimaTarea;

    @FXML
    private ChoiceBox<String> choiceBoxTareas; // Cambio a ChoiceBox en lugar de ComboBox

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

    @FXML
    private Label lblTareaSeleccionada;

    @FXML
    private Label lblHorasTrabajadas;

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;

        lblUltimaTarea.setText(obtenerTareaFinalizadaReciente());
        HorasDedicadas();
        actualizarBarraProgreso();
        cargarTareasEnChoiceBox(); // Llamar a la función para cargar las tareas
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
        return tareaMasReciente != null ? tareaMasReciente.getNombre() : "No hay tareas finalizadas";
    }

    // Función para cargar todas las tareas del estudiante en el ChoiceBox
    public void cargarTareasEnChoiceBox() {
        for (Tarea tarea : estudiante.getTareas()) {
            choiceBoxTareas.getItems().add(tarea.getNombre()); // Agregar tarea por nombre
        }

        // Listener para manejar la selección
        choiceBoxTareas.setOnAction(event -> {
            String selectedTareaNombre = choiceBoxTareas.getSelectionModel().getSelectedItem();
            if (selectedTareaNombre != null) {
                lblTareaSeleccionada.setText(selectedTareaNombre);

                // Opcional: lógica adicional para cambiar dificultad o mover tareas
                Tarea tareaSeleccionada = estudiante.getTareas().stream()
                        .filter(t -> t.getNombre().equals(selectedTareaNombre))
                        .findFirst()
                        .orElse(null);

                if (tareaSeleccionada != null) {
                    cambiarDificultad(tareaSeleccionada);
                    moverTareaADelante(tareaSeleccionada);
                }
            }
        });
    }

    // Función para cambiar la dificultad de la tarea seleccionada a 1 y la anterior a 0
    public void cambiarDificultad(Tarea tareaSeleccionada) {
        // Cambiar la dificultad de la tarea seleccionada a 1
        tareaSeleccionada.setDificultad(1);

        // Buscar si hay otra tarea con dificultad 1 y cambiarla a 0
        for (Tarea tarea : estudiante.getTareas()) {
            if (tarea != tareaSeleccionada && tarea.getDificultad() == 1) {
                tarea.setDificultad(0);
                break; // Solo se puede tener una tarea con dificultad 1
            }
        }
    }
    // Función para mover la tarea seleccionada a la primera posición
    public void moverTareaADelante(Tarea tareaSeleccionada) {
        // Eliminar la tarea seleccionada de la lista y agregarla al principio
        estudiante.getTareas().remove(tareaSeleccionada);
        estudiante.getTareas().add(0, tareaSeleccionada);

        // Actualizar el ChoiceBox para reflejar el cambio en el orden
        choiceBoxTareas.getItems().clear(); // Limpiar el ChoiceBox
        cargarTareasEnChoiceBox(); // Recargar las tareas con el nuevo orden
    }

    private void HorasDedicadas() {
        int TotalHoras = 0;
        for (Tarea tarea : this.estudiante.getTareas()){
            TotalHoras +=tarea.getHorasDedicadas();
        }

        lblHorasTrabajadas.setText(Integer.toString(TotalHoras));
    }

    @FXML
    private ProgressBar progressBar;

    private void actualizarBarraProgreso() {
        int totalTareas = estudiante.getTareas().size();
        if (totalTareas == 0) {
            progressBar.setProgress(0); // Si no hay tareas, progreso es 0
            return;
        }

        long tareasCompletadas = estudiante.getTareas().stream()
                .filter(Tarea::isTerminado)
                .count(); // Contar tareas completadas

        double progreso = (double) tareasCompletadas / totalTareas;
        progressBar.setProgress(progreso); // Actualizar la barra de progreso
    }

    @FXML
    private void volver() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            PrincipalController principalController = loader.getController();
            principalController.setEstudiante(estudiante);
            Stage stage = (Stage) btnVolver.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
