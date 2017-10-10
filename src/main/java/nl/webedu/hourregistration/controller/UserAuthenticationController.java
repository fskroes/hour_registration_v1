package nl.webedu.hourregistration.controller;

import com.mongodb.async.client.MongoClient;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import nl.webedu.hourregistration.dao.mongodb.MongoUserAuthenticationDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.UserAuthenticationModel;

public class UserAuthenticationController {

    public TextField emailText;
    public TextField passwordText;
    public Button signInButton;

    private MongoClient client;

    public void signButtonHandler(ActionEvent actionEvent) {
        MongoUserAuthenticationDAO u = new MongoUserAuthenticationDAO();
        u.registerUser(emailText.getText(), passwordText.getText());
        u.authenticateUser();

    }
}
