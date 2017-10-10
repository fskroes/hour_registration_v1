package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.async.client.MongoClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import nl.webedu.hourregistration.dao.IUserAuthenticationDAO;
import nl.webedu.hourregistration.dao.mongodb.MongoUserAuthenticationDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.UserAuthenticationModel;

public class UserAuthenticationController {

    @FXML
    public JFXTextField txtEmail;
    @FXML
    public JFXTextField txtPassword;
    @FXML
    public JFXButton btnRegister;

    private IUserAuthenticationDAO mongoUserAuthenticationDAO;

    public void initialize() {
        mongoUserAuthenticationDAO = DatabaseManager.getInstance().getDaoFactory().getUserAuthenticationDAO();
    }

    public void signButtonHandler(ActionEvent actionEvent) {
        mongoUserAuthenticationDAO.registerUser(txtEmail.getText(), txtPassword.getText());
        mongoUserAuthenticationDAO.authenticateUser();
    }
}
