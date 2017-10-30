package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import nl.webedu.hourregistration.dao.IEmployeeDAO;
import nl.webedu.hourregistration.dao.IProjectDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.ProjectModel;

import java.io.IOException;
import java.util.List;

public class ProjectController {

    @FXML
    private AnchorPane pAnchor;
    @FXML
    private IProjectDAO projectDAO;
    @FXML
    private IEmployeeDAO employeeDAO;
    @FXML
    private List<ProjectModel> projects;
    @FXML
    private List<EmployeeModel> employees;
    @FXML
    private JFXListView<?> pListView;
    @FXML
    private JFXListView<?> wLijstView;

    public void initialize() {
        projectDAO = DatabaseManager.getInstance().getDaoFactory().getProjectDAO();
        employeeDAO = DatabaseManager.getInstance().getDaoFactory().getEmployeeDAO();
        onStartup();
    }

    public void onStartup(){
        ObservableList list = FXCollections.observableArrayList();
        projects = projectDAO.selectAllProjects();

        for (ProjectModel project : projects) {
            list.add(project.getName());
        }

        pListView.getItems().addAll(list);
    }

    public void toCustomerView(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) pAnchor.getScene().getWindow();
        primaryStage.hide();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/CustomerView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert parent != null;
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void onSelectProject(MouseEvent mouseEvent) {

        ObservableList lijst = FXCollections.observableArrayList();
        employees = employeeDAO.selectEmployeesByProject((ProjectModel) projects);

        for (EmployeeModel employee : employees) {
            lijst.add(employee.getFirstname() + " " + employee.getLastname());
        }
        wLijstView.getItems().addAll(lijst);
    }


//    public void addEmployeeToList (ActionEvent actionevent) {
//
//
//
//    }
//
//    public void removeEmployeeFromList (ActionEvent actionevent) {
//
//    }
}