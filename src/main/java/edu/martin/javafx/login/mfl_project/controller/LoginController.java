package edu.martin.javafx.login.mfl_project.controller;



import edu.martin.javafx.login.mfl_project.dao.UsuarioDAO;
import edu.martin.javafx.login.mfl_project.dao.UsuarioDAOImpl;
import edu.martin.javafx.login.mfl_project.model.Usuario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;


public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(usuarioDAO.validarCredenciales(username,password)){
            Usuario usuario = usuarioDAO.getUsuarioPorUsername(username);

            System.out.println("Login exitoso para el usuario: " + usuario.getNombre());
            // Aquí puedes agregar la lógica para cambiar a la siguiente vista y pasar el objeto Usuario
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de autenticación");
            alert.setHeaderText(null);
            alert.setContentText("Nombre de usuario o contraseña incorrectos.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        System.out.println("La vista de login está lista. Poniendo el foco en el campo de usuario.");
        Platform.runLater(() -> usernameField.requestFocus());
    }
}
