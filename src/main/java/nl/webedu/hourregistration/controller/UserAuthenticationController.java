package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nl.webedu.hourregistration.dao.IUserAuthenticationDAO;
import nl.webedu.hourregistration.database.DatabaseManager;

import java.io.IOException;

public class UserAuthenticationController {

    @FXML
    public AnchorPane root;
    @FXML
    public JFXTextField txtEmail;
    @FXML
    public JFXPasswordField txtPassword;
    @FXML
    public JFXButton btnRegister;
    @FXML
    public JFXButton btnLogin;

    private IUserAuthenticationDAO mongoUserAuthenticationDAO;

    public void initialize() {
        mongoUserAuthenticationDAO = DatabaseManager.getInstance().getDaoFactory().getUserAuthenticationDAO();
    }

    public void onLogin(ActionEvent actionEvent) {
        if(mongoUserAuthenticationDAO.authenticateUser(txtEmail.getText(), txtPassword.getText())) {
            Stage primaryStage = (Stage) root.getScene().getWindow();
            primaryStage.hide();
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert parent != null;
            Scene scene = new Scene(parent, 1200, 800);
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            System.out.println("Error while trying to sign in");
        }
    }

    public void onRegister(ActionEvent actionEvent) {
        mongoUserAuthenticationDAO.registerUser(txtEmail.getText(), txtPassword.getText());
        onLogin(actionEvent);
    }
}
