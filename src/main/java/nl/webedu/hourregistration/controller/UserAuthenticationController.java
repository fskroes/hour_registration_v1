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

    private IUserAuthenticationDAO userAuthenticationDAO;

    @FXML
    public AnchorPane root;
    @FXML
    public JFXTextField txtEmail;
    @FXML
    public JFXPasswordField txtPassword;
    @FXML
    public JFXButton btnLogin;
    @FXML
    public JFXButton btnRegister;

    @FXML
    public void initialize() {
        userAuthenticationDAO = DatabaseManager.getInstance().getDaoFactory().getUserAuthenticationDAO();
    }

    public void onLogin(ActionEvent actionEvent) {
        System.out.println("Ja hallo");
        userAuthenticationDAO.authenticateUser();

        Stage primaryStage = (Stage) root.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/RegisterView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[DEBUG] View not found!");
        }
        assert root != null;
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
    }

    public void onRegisterUser(ActionEvent actionEvent) {
        userAuthenticationDAO.registerUser(txtEmail.getText(), txtPassword.getText());

    }
}
