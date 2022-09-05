package clinic.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginFormController {


    public JFXTextField txtName;
    public JFXPasswordField txtPassword;
    public JFXButton btnLogin;

    public void btnLoginOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException, IOException {
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
        Class.forName("com.mysql.cj.jdbc.Driver");

        try(Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/medical_clinic", "root", "Anushka@1995");){
            String sql ="SELECT role FROM User WHERE username='%s' AND password='%s'";
            sql=String.format(sql,userName,password);
            Statement stm= connection.createStatement();
            ResultSet rst=stm.executeQuery(sql);
            if (rst.next()){
                String role= rst.getNString("role");
                Scene scene= null;
                SecurityContextHolder.setPrinciple(new User(userName, UserRole.valueOf(role)));
                switch (role){
                    case "Admin":
                        scene=new Scene(FXMLLoader.load(this.getClass().getResource("/view/AdminDashBoard.fxml")));
                        break;
                    case "Doctor":
                        scene=new Scene(FXMLLoader.load(this.getClass().getResource("/view/DoctorDashBoard.fxml")));
                        break;
                    default:
                        scene=new Scene(FXMLLoader.load(this.getClass().getResource("/view/ReceptionistDashBoard.fxml")));
                        break;
                }
                Stage stage = (Stage)btnLogin.getScene().getWindow();
                stage.setTitle("Open Source Medical Clinic`");
                stage.setScene(scene);
                stage.show();
                stage.centerOnScreen();
            }else{
                new Alert(Alert.AlertType.ERROR, "Invalid login credential").showAndWait();
                txtName.requestFocus();
                txtName.selectAll();
            }
        } catch (SQLException e) {
        e.printStackTrace();
        new Alert(Alert.AlertType.ERROR, "Failed to connect with the database, try again");
    }

    }
}
