package edu.martin.javafx.login.mfl_project.controller;

import edu.martin.javafx.login.mfl_project.dao.UsuarioDAO;
import edu.martin.javafx.login.mfl_project.dao.UsuarioDAOImpl;
import edu.martin.javafx.login.mfl_project.model.Usuario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos vacíos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, rellena usuario y contraseña.");
            alert.showAndWait();
            return;
        }

        boolean credencialesValidas = usuarioDAO.validarCredenciales(username, password);

        if (!credencialesValidas) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de autenticación");
            alert.setHeaderText(null);
            alert.setContentText("Nombre de usuario o contraseña incorrectos.");
            alert.showAndWait();
            return;
        }

        Usuario usuario = usuarioDAO.getUsuarioPorUsername(username);

        if (usuario == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error usuario nulo");
            alert.setHeaderText(null);
            alert.setContentText("No se ha podido obtener la información del usuario.");
            alert.showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/edu/martin/javafx/login/mfl_project/main-view.fxml")
        );
        Parent root = loader.load();

        MainController mainController = loader.getController();
        mainController.initData(usuario);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Fondo de Armario - " + usuario.getNombre());
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> usernameField.requestFocus());
    }
}
