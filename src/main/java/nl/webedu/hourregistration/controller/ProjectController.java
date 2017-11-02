package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import nl.webedu.hourregistration.dao.IEmployeeDAO;
import nl.webedu.hourregistration.dao.IProjectDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.ProjectModel;

import javax.swing.*;
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
    private JFXListView<?> wListView;
    @FXML
    public JFXButton btnTerug;
    @FXML
    public JFXComboBox cbEmployee;

    private int index;
    private int eindex;
    private boolean observe = false;
    private boolean actief = false;

    public void initialize() {
        projectDAO = DatabaseManager.getInstance().getDaoFactory().getProjectDAO();
        employeeDAO = DatabaseManager.getInstance().getDaoFactory().getEmployeeDAO();
        pListView.setFocusTraversable(false);
        wListView.setFocusTraversable(false);
        onStartup();
    }

    public void onStartup(){
        cbEmployee.getItems().addAll(DatabaseManager.getInstance().getDaoFactory().getEmployeeDAO().getAllEmployees());
        ObservableList list = FXCollections.observableArrayList();
        projects = projectDAO.selectAllProjects();


        for (ProjectModel project : projects) {
            list.add(project.getName());
        }

        pListView.getItems().addAll(list);
    }

    @SuppressWarnings("Duplicates")
    public void toTimesheetsView(ActionEvent actionEvent) {

        Stage primaryStage = (Stage) pAnchor.getScene().getWindow();
        primaryStage.hide();
    }

    //On select, geef werknemer first en lastnames die horen bij het projectID
    public void onSelectProject(MouseEvent mouseEvent) {

        int index = pListView.getSelectionModel().getSelectedIndex();
        ProjectModel model = projects.get(index);
        wListView.getItems().clear();

        ObservableList lijst = FXCollections.observableArrayList();

        employees = employeeDAO.selectEmployeesByProject(model);

        for (EmployeeModel employee : employees) {
            lijst.add(employee.getFirstname() + " " + employee.getLastname());
        }
        wListView.getItems().addAll(lijst);
    }

    //On select op de wListView, maak werknemer actief of inactief en verander dit visueel.
    public void selectEmployeeOnList(MouseEvent mouseEvent) {
        int eindex = wListView.getSelectionModel().getSelectedIndex();

        if (eindex < 0 ){
        }else {
            EmployeeModel model = employees.get(eindex);
        }
    }

    private void refreshEmployeeList(){

        wListView.getItems().clear();
        ProjectModel model = projects.get(pListView.getSelectionModel().getSelectedIndex());

        employees = employeeDAO.selectEmployeesByProject(model);
        ObservableList lijst = FXCollections.observableArrayList();
        for (EmployeeModel employee : employees) {
            lijst.add(employee.getFirstname() + " " + employee.getLastname());
        }
        wListView.getItems().addAll(lijst);

    }

    //Actieve employees worden verwijderd van het project.
    public void deleteEmployeeFromProject (ActionEvent actionevent) {
        int eindex = wListView.getSelectionModel().getSelectedIndex();
        int eindex2 = pListView.getSelectionModel().getSelectedIndex();

        if (eindex < 0  || eindex2 < 0){
        }else {
            EmployeeModel emp = employees.get(eindex);
            ProjectModel pro = projects.get(eindex2);
            DatabaseManager.getInstance().getDaoFactory().getProjectDAO().DeleteJunctionItemByEmployee(emp,pro);
            employees.remove(eindex);
            refreshEmployeeList();
        }
    }

        //Actieve employees worden toegevoegd aan het project.
    public void addEmployeeToList (ActionEvent actionEvent) {
        EmployeeModel Emodel =  (EmployeeModel) cbEmployee.getSelectionModel().getSelectedItem();
        wListView.getItems().contains(Emodel);
        int index = pListView.getSelectionModel().getSelectedIndex();
        ProjectModel Pmodel = projects.get(index);

        DatabaseManager.getInstance().getDaoFactory().getProjectDAO().addJunctionItemWithProject(Emodel, Pmodel);

        refreshEmployeeList();
    }
}