package com.example.proyecto_est;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;

public class PrincipalController {

    @FXML
    Label nombre;

    private Estudiante estudiante;

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
        if (nombre != null) {
            nombre.setText(estudiante.getUsuario());
        }
    }

    @FXML
    private void initialize() {

    }

    /*public class CustomSearchBar extends Application {



        @Override
        public void start(Stage primaryStage) {
            TextField searchBar = new TextField();
            searchBar.setPromptText("Buscar tareas o materias...");

            searchBar.setStyle(
                    "-fx-background-color: #1C1C45;" +  // Color de fondo
                            "-fx-text-fill: white;" +            // Color del texto
                            "-fx-prompt-text-fill: #888;" +      // Color del texto placeholder
                            "-fx-background-radius: 30;" +       // Borde redondeado
                            "-fx-border-radius: 30;" +           // Bordes redondeados
                            "-fx-padding: 10;"                   // Espaciado interno
            );

            // Icono de búsqueda
            ImageView searchIcon = new ImageView(new Image("file:search_icon.png")); // Añade la ruta de tu icono aquí
            searchIcon.setFitHeight(20);  // Ajustar el tamaño del icono
            searchIcon.setFitWidth(20);

            // Layout para icono y barra de búsqueda
            HBox searchBox = new HBox(10, searchIcon, searchBar);
            searchBox.setAlignment(Pos.CENTER_LEFT);  // Alinear elementos a la izquierda

            // Aplicar un fondo similar al de la imagen que compartiste
            searchBox.setStyle("-fx-background-color: #1C1C45;" + "-fx-background-radius: 30;" + "-fx-padding: 5;");

            // Crear una escena con la barra de búsqueda
            VBox vbox = new VBox(searchBox);
            vbox.setAlignment(Pos.TOP_CENTER);
            vbox.setStyle("-fx-background-color: #0F0F30;");  // Fondo oscuro de la interfaz

            Scene scene = new Scene(vbox, 400, 600);
            primaryStage.setTitle("Interfaz de Búsqueda Personalizada");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }*/
}
