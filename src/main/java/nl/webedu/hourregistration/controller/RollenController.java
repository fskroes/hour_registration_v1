package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.EmployeeModel;

import java.io.IOException;
import java.util.List;

public class RollenController {

    public JFXListView employeeListView;
    public JFXTextField employeeTextfield;
    public JFXCheckBox employeeCheckboxADMIN;
    public JFXCheckBox employeeCheckboxEMPLOYEE;
    public JFXCheckBox employeeCheckboxADMINISTRATION;
    public SplitPane root;
    private List<EmployeeModel> allEmployees;

    /**
     * Wordt aangeroepen wanneer de view wordt opgestart, de DAO's worden geïnitialiseerd.
     */
    public void initialize() {
        getEmployeesData();
    }

    /**
     * Zorgt voor het laden van de werknemer data in de listview.
     */
    private void getEmployeesData() {
        allEmployees = DatabaseManager.getInstance().getDaoFactory().getEmployeeDAO().getAllEmployees();

        for (EmployeeModel model : allEmployees) {
            employeeListView.getItems().add(new Label(model.getLastname() + ", " + model.getFirstname()));
        }
    }

    /**
     * Zorgt ervoor dat de gegevens van de werknemer worden geladen als erop wordt geklikt.
     * @param mouseEvent het event wat zorgt voor het aanroepen van de methode.
     */
    public void mouseClicked(MouseEvent mouseEvent) {
        employeeCheckboxADMIN.setDisable(false);
        employeeCheckboxADMINISTRATION.setDisable(false);
        employeeCheckboxEMPLOYEE.setDisable(false);
        employeeCheckboxADMIN.setSelected(false);
        employeeCheckboxADMINISTRATION.setSelected(false);
        employeeCheckboxADMINISTRATION.setSelected(false);

        int index = employeeListView.getSelectionModel().getSelectedIndex();
        EmployeeModel model = allEmployees.get(index);

        employeeTextfield.setText(model.getLastname() + ", " + model.getFirstname());
        switch (model.getRole()) {
            case EMPLOYEE:
                employeeCheckboxEMPLOYEE.setSelected(true);
                employeeCheckboxADMIN.setDisable(true);
                employeeCheckboxADMINISTRATION.setDisable(true);
                break;
            case MANAGER:
                employeeCheckboxADMINISTRATION.setSelected(true);
                employeeCheckboxEMPLOYEE.setDisable(true);
                employeeCheckboxADMIN.setDisable(true);
                break;
            case ADMIN:
                employeeCheckboxADMIN.setSelected(true);
                employeeCheckboxADMINISTRATION.setDisable(true);
                employeeCheckboxEMPLOYEE.setDisable(true);
                break;
        }
    }

    /**
     * Zorgt ervoor dat de gegevens van de admin wordt geladen als erop wordt geklikt.
     * @param actionEvent het event wat zorgt voor het aanroepen van de methode.
     */
    public void setAdminProperties(ActionEvent actionEvent) {
        int index = employeeListView.getSelectionModel().getSelectedIndex();
        EmployeeModel model = allEmployees.get(index);
        model.setRole(1);

        DatabaseManager.getInstance().getDaoFactory().getEmployeeDAO().updateEmployee(model);
    }

    /**
     * Zorgt ervoor dat de gegevens van de administration wordt geladen als erop wordt geklikt.
     * @param actionEvent het event wat zorgt voor het aanroepen van de methode.
     */
    public void setAdministationProperties(ActionEvent actionEvent) {
        int index = employeeListView.getSelectionModel().getSelectedIndex();
        EmployeeModel model = allEmployees.get(index);
        model.setRole(2);

        DatabaseManager.getInstance().getDaoFactory().getEmployeeDAO().updateEmployee(model);
    }

    /**
     * Zorgt ervoor dat de gegevens van de werknemer wordt geladen als erop wordt geklikt.
     * @param actionEvent het event wat zorgt voor het aanroepen van de methode.
     */
    public void setEmployeeProperties(ActionEvent actionEvent) {
        int index = employeeListView.getSelectionModel().getSelectedIndex();
        EmployeeModel model = allEmployees.get(index);
        model.setRole(3);

        DatabaseManager.getInstance().getDaoFactory().getEmployeeDAO().updateEmployee(model);
    }

    /**
     * Gaat terug naar de vorige view.
     * @param actionEvent het event wat zorgt voor het aanroepen van de methode.
     */
    public void returnToTimesheets(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) root.getScene().getWindow();
        primaryStage.hide();
    }

    /**
     * Knop die zorgt dat de registreren view wordt geopend.
     * @param actionEvent het event wat zorgt voor het aanroepen van de methode.
     */
    public void AddEmployeePressed(ActionEvent actionEvent) {
        Stage stage = new Stage();
        stage.hide();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/RegisterView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert parent != null;
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}
