package com.example.proyecto_est;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.util.List;

public class CalendarioController {
    @FXML
    private GridPane gridDias;

    @FXML
    private HBox hboxSemana;

    @FXML
    private VBox vboxCalendario;

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
        int dayOfMonth = 1;
        int firstDayOfMonth = currentDate.withDayOfMonth(1).getDayOfWeek().getValue(); // Día de la semana del primer día del mes
        firstDayOfMonth = (firstDayOfMonth == 7) ? 0 : firstDayOfMonth; // Ajustar para que domingo sea 6 en lugar de 7

        // Crear las filas para las semanas del mes
        int row = 1; // Comenzamos desde la segunda fila para los días (la primera fila tiene los días de la semana)
        int column = firstDayOfMonth; // Colocamos el primer día del mes en la columna correspondiente

        // Llenar el calendario con los días del mes
        for (int day = 1; day <= currentDate.lengthOfMonth(); day++) {
            // Crear una etiqueta para el día del mes
            Label dayLabel = new Label(String.valueOf(day));

            // Verificar si hay tareas para ese día
            List<Tarea> tareasEnFecha = estudiante.getTareasPorFecha(LocalDate.of(currentDate.getYear(), currentDate.getMonth(), day));
            if (!tareasEnFecha.isEmpty()) {
                // Si hay tareas, añadirlas en la etiqueta
                StringBuilder tareasTexto = new StringBuilder(dayLabel.getText());
                for (Tarea tarea : tareasEnFecha) {
                    tareasTexto.append("\n").append(tarea.getNombre());
                }
                dayLabel.setText(tareasTexto.toString());
            }

            // Colocar el label en la celda correspondiente del GridPane
            gridDias.add(dayLabel, column, row);

            // Avanzar a la siguiente columna
            column++;

            // Si llegamos al final de la fila (domingo), pasamos a la siguiente fila
            if (column > 6) {
                column = 0;
                row++;
            }
        }
    }
}
