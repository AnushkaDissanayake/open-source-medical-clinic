package clinic.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginFormController {

    public JFXTextField txtName;
    public JFXPasswordField txtPassword;
    public JFXButton btnLogin;

    public void btnLoginOnAction(ActionEvent actionEvent) {
        String userName = txtName.getText();
        String password = txtPassword.getText();

        if (userName.isBlank()) {
            new Alert(Alert.AlertType.ERROR, "Username can't be empty").showAndWait();
            txtName.requestFocus();
            txtName.selectAll();
            return;
        } else if (password.isBlank()) {
            new Alert(Alert.AlertType.ERROR, "Password can't be empty").showAndWait();
            txtPassword.requestFocus();
            txtPassword.selectAll();
            return;
        } else if (!userName.matches("[a-zA-Z0-9]+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid login credential").showAndWait();
            txtName.requestFocus();
            txtName.selectAll();
            return;
        }

    }
}
