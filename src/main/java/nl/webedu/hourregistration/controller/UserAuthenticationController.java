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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nl.webedu.hourregistration.dao.IEmployeeDAO;
import nl.webedu.hourregistration.dao.IUserAuthenticationDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.enumeration.Role;
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
    public JFXTextField txtFirstName;
    @FXML
    public JFXTextField txtSuffix;
    @FXML
    public JFXTextField txtLastName;
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

    /**
     * Wordt aangeroepen wanneer de view wordt opgestart, de DAO's worden geïnitialiseerd.
     */
    public void initialize() {
        mongoUserAuthenticationDAO = DatabaseManager.getInstance().getDaoFactory().getUserAuthenticationDAO();
        mongoEmployeeDAO = DatabaseManager.getInstance().getDaoFactory().getEmployeeDAO();
    }

    /**
     * Zorgt voor het inlogproces zoals het checken van email en password vervolgens worden de timesheets geladen.
     * @param actionEvent het event wat zorgt voor het aanroepen van de methode.
     */
    public void onLogin(ActionEvent actionEvent) {
        if(mongoUserAuthenticationDAO.authenticateUser(txtEmail.getText(), txtPassword.getText())) {
            System.out.println(txtEmail.getText() + " is signing in");

            Stage primaryStage = (Stage) root.getScene().getWindow();
            primaryStage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TimesheetsView.fxml"));

            Parent parent = null;
            try {
                parent = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert parent != null;

            TimeSheetsController controller = loader.getController();
            EmployeeModel emp = mongoUserAuthenticationDAO.findEmployee(txtEmail.getText());
            controller.postConstructor(emp);

            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            System.out.println("Error while trying to sign in");
        }
    }

    /**
     * Zorgt voor het registreerproces zoals het checken of het account niet al bestaat.
     * @param actionEvent het event wat zorgt voor het aanroepen van de methode.
     */
    public void onRegister(ActionEvent actionEvent) {
        mongoUserAuthenticationDAO.registerUser(txtEmail.getText(),
                txtPassword.getText(),
                txtFirstName.getText(),
                txtSuffix.getText(),
                txtLastName.getText());

        if (!txtEmail.getText().isEmpty() && !txtPassword.getText().isEmpty()) {
            Stage primaryStage = (Stage) root.getScene().getWindow();
            primaryStage.hide();
            //
            // TODO: 11/2/17  Refresh van rollen beheer moet hier komen
            //

        }
    }

    /**
     * Zorgt ervoor dat het loginscherm wordt geladen en getoond.
     * @param actionEvent het event wat zorgt voor het aanroepen van de methode.
     */
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
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void onAction(ActionEvent actionEvent) {
        onLogin(actionEvent);
    }
}
