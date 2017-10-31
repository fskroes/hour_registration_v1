package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
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

    public void initialize() {
        setUpUserInterface();
        getEmployeesData();
    }

    private void getEmployeesData() {
//        List<EmployeeModel> allEmployees = new ArrayList<>();
        allEmployees = DatabaseManager.getInstance().getDaoFactory().getEmployeeDAO().getAllEmployees();

        for (EmployeeModel model : allEmployees) {
            employeeListView.getItems().add(new Label(model.getFirstname() + model.getLastname()));
        }
    }

    private void setUpUserInterface() {
//        employeeListView.getItems().add(lbl);
    }

    public void mouseClicked(MouseEvent mouseEvent) {

        employeeCheckboxADMIN.setDisable(false);
        employeeCheckboxADMINISTRATION.setDisable(false);
        employeeCheckboxEMPLOYEE.setDisable(false);
        employeeCheckboxADMIN.setSelected(false);
        employeeCheckboxADMINISTRATION.setSelected(false);
        employeeCheckboxADMINISTRATION.setSelected(false);

        int index = employeeListView.getSelectionModel().getSelectedIndex();
        EmployeeModel model = allEmployees.get(index);


//        EmployeeModel selectedItem = (EmployeeModel) employeeListView.getSelectionModel().getSelectedItem();
        employeeTextfield.setText(model.getFirstname());
        System.out.println("selected: " + model.getRole().toString());
        System.out.println("selected: " + model.getRole().toString());
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

    public void setAdminProperties(ActionEvent actionEvent) {
        int index = employeeListView.getSelectionModel().getSelectedIndex();
        EmployeeModel model = allEmployees.get(index);
        model.setRole(1);

        DatabaseManager.getInstance().getDaoFactory().getEmployeeDAO().updateEmployee(model);
    }

    public void setAdministationProperties(ActionEvent actionEvent) {
        int index = employeeListView.getSelectionModel().getSelectedIndex();
        EmployeeModel model = allEmployees.get(index);
        model.setRole(2);

        DatabaseManager.getInstance().getDaoFactory().getEmployeeDAO().updateEmployee(model);
    }

    public void setEmployeeProperties(ActionEvent actionEvent) {
        int index = employeeListView.getSelectionModel().getSelectedIndex();
        EmployeeModel model = allEmployees.get(index);
        model.setRole(3);

        DatabaseManager.getInstance().getDaoFactory().getEmployeeDAO().updateEmployee(model);
    }

    public void returnToTimesheets(ActionEvent actionEvent) {
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

        //MainController controller = loader.getController();

        Scene scene = new Scene(parent, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
