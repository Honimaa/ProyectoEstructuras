package com.example.proyecto_est;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class RegistroController {
    private List<Estudiante> estudiantes;

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    @FXML
    TextField nombreUsuario;

    @FXML
    PasswordField contrasena;

    @FXML
    PasswordField confirmarContrasena;

    @FXML
    Button registrar;

    @FXML
    private void registrarEstudiante() {
        Estudiante estudiante = new Estudiante();
        if(contrasena.getText().equals(confirmarContrasena.getText())) {
            estudiante.setUsuario(nombreUsuario.getText());
            estudiante.setClave(contrasena.getText());
            estudiante.setTareas(new ArrayList<>());
            this.estudiantes.add(estudiante);
            try{
                for(Estudiante est : estudiantes) {
                    System.out.println(est.getTareas().size());
                }
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                LoginController logincontroller = loader.getController();
                logincontroller.setEstudiantes(estudiantes);
                Stage stage = (Stage) registrar.getScene().getWindow();
                stage.setScene(scene);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
