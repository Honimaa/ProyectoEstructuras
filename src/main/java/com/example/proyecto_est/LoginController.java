package com.example.proyecto_est;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.HashMap;
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

    @FXML
    private void iniciarSesion() {
        String usuario = txtUsuario.getText();
        String contraseña = txtContraseña.getText();

        if (usuarios.containsKey(usuario) && usuarios.get(usuario).equals(contraseña)) {
            try {
                HelloApplication.cambiarVista("MenuPrincipal.fxml", "EduTimePro - Menú Principal");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            lblError.setText("Usuario o contraseña incorrectos.");
        }
    }

    @FXML
    private void irRegistrar() {
        try {
            HelloApplication.cambiarVista("Registro.fxml", "EduTimePro - Registro");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void agregarUsuario(String usuario, String clave) {
        usuarios.put(usuario, clave);
    }
}
