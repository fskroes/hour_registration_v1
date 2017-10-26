package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nl.webedu.hourregistration.dao.IEmployeeDAO;
import nl.webedu.hourregistration.dao.IUserAuthenticationDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.EmployeeModel;

import java.io.IOException;

public class UserAuthenticationController {

    @FXML
    public AnchorPane root;
    @FXML
    public JFXTextField txtEmail;
    @FXML
    public JFXPasswordField txtPassword;
    @FXML
    public JFXButton btnGeenAccount;
    @FXML
    public JFXButton btnEenAccount;
    @FXML
    public Button btnSignMeIn;
    @FXML
    public Button btnRegisterMe;

    private IUserAuthenticationDAO mongoUserAuthenticationDAO;
    private IEmployeeDAO mongoEmployeeDAO;

    public void initialize() {
        mongoUserAuthenticationDAO = DatabaseManager.getInstance().getDaoFactory().getUserAuthenticationDAO();
        mongoEmployeeDAO = DatabaseManager.getInstance().getDaoFactory().getEmployeeDAO();
    }

    public void onLogin(ActionEvent actionEvent) {
        if(mongoUserAuthenticationDAO.authenticateUser(txtEmail.getText(), txtPassword.getText())) {
            System.out.println(txtEmail.getText() + " is signing in");

            Stage primaryStage = (Stage) root.getScene().getWindow();
            primaryStage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainView.fxml"));

            Parent parent = null;
            try {
                parent = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert parent != null;

            MainController controller = loader.getController();
            //controller.setSessionEmployee(mongoUserAuthenticationDAO.findEmployee(txtEmail.getText()));

            Scene scene = new Scene(parent, 1200, 800);
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            System.out.println("Error while trying to sign in");
        }
    }

    public void onRegister(ActionEvent actionEvent) {
        mongoUserAuthenticationDAO.registerUser(txtEmail.getText(), txtPassword.getText());
        if (!txtEmail.getText().isEmpty() && !txtPassword.getText().isEmpty()) {
            toLoginView(actionEvent);
        }
    }

    public void toRegisterView(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) root.getScene().getWindow();
        primaryStage.hide();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/RegisterView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert parent != null;
        Scene scene = new Scene(parent, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void toLoginView(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) root.getScene().getWindow();
        primaryStage.hide();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/LoginView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert parent != null;
        Scene scene = new Scene(parent, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
