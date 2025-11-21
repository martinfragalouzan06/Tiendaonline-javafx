package edu.martin.javafx.login.mfl_project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LoginController {
    @FXML
    private TextField nicknameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        String nickname = nicknameField.getText();
        String password = passwordField.getText();



    }
}
