package com.example.proyecto_est;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class CalendarioController {
    @FXML
    private GridPane gridDias;

    @FXML
    private HBox hboxSemana;

    @FXML
    private VBox vboxCalendario;

    @FXML
    private Button btnVolver;

    private Estudiante estudiante; // El único estudiante

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
        cargarCalendario();
    }

    public void cargarCalendario() {
        LocalDate currentDate = LocalDate.now();
        int firstDayOfMonth = currentDate.withDayOfMonth(1).getDayOfWeek().getValue(); // Día de la semana del primer día del mes
        firstDayOfMonth = (firstDayOfMonth == 7) ? 0 : firstDayOfMonth; // Ajustar para que domingo sea la última columna

        // Limpiar el GridPane antes de agregar elementos
        gridDias.getChildren().clear();

        int row = 0; // Fila inicial
        int column = firstDayOfMonth; // Columna inicial según el primer día del mes

        for (int day = 1; day <= currentDate.lengthOfMonth(); day++) {
            // Crear una etiqueta para cada día
            Label dayLabel = new Label(String.valueOf(day));
            dayLabel.setStyle("-fx-padding: 10; -fx-alignment: center;");

            // Verificar si hay tareas para ese día
            List<Tarea> tareasEnFecha = estudiante.getTareasPorFecha(LocalDate.of(currentDate.getYear(), currentDate.getMonth(), day));
            if (!tareasEnFecha.isEmpty()) {
                StringBuilder tareasTexto = new StringBuilder(dayLabel.getText());
                for (Tarea tarea : tareasEnFecha) {
                    tareasTexto.append("\n").append(tarea.getNombre());
                }
                dayLabel.setText(tareasTexto.toString());
                dayLabel.setStyle("-fx-background-color: lightblue; -fx-padding: 10; -fx-alignment: center;");
            }

            // Agregar el label al GridPane
            gridDias.add(dayLabel, column, row);

            // Avanzar a la siguiente columna
            column++;

            // Cambiar a la siguiente fila si llegamos al final de la semana
            if (column > 6) {
                column = 0;
                row++;
            }
        }
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
