package com.example.proyecto_est;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContraseña;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Label lblError;

    private static Map<String, String> usuarios = new HashMap<>();

    private List<Estudiante> estudiantes = new ArrayList<>();

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    @FXML
    private void iniciarSesion() {
        String usuario = txtUsuario.getText();
        String contrasena = txtContraseña.getText();

        for(Estudiante estudiante : estudiantes) {
            if(estudiante.getUsuario().equals(usuario)) {
                if(estudiante.getClave().equals(contrasena)) {
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        PrincipalController principalController = loader.getController();
                        principalController.setEstudiante(estudiante);
                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                        stage.setScene(scene);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @FXML
    private void irRegistrar() {
        try {
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Registro.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                RegistroController registroController = loader.getController();
                registroController.setEstudiantes(estudiantes);
                Stage stage = (Stage) btnRegistrar.getScene().getWindow();
                stage.setScene(scene);
            }catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void agregarUsuario(String usuario, String clave) {
        usuarios.put(usuario, clave);
    }
}
