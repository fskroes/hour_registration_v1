package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXButton;
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

    private int index;
    private boolean observe = false;
    private boolean actief = false;

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

    public void toTimesheetsView(ActionEvent actionEvent) {

        Stage primaryStage = (Stage) pAnchor.getScene().getWindow();
        primaryStage.hide();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/TimesheetsView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert parent != null;
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //On select, geef werknemer first en lastnames die horen bij het projectID
    public void onSelectProject(MouseEvent mouseEvent) {

        int index = pListView.getSelectionModel().getSelectedIndex();
        ProjectModel model = projects.get(index);

        ObservableList lijst = FXCollections.observableArrayList();

        employees = employeeDAO.selectEmployeesByProject(model);

        for (EmployeeModel employee : employees) {
            lijst.add(employee.getFirstname() + " " + employee.getLastname());
        }
        wListView.getItems().addAll(lijst);
    }

    //On select op de wListView, maak werknemer actief of inactief en verander dit visueel.
    public void selectEmployeeOnList(MouseEvent mouseEvent) {
        int index = wListView.getSelectionModel().getSelectedIndex();

        if(actief == false) {
            actief = true;
        }
        else if (actief == true){
            actief = false;
        }
    }

    //Actieve employees worden toegevoegd aan het project.
    public void addEmployeeToList (ActionEvent actionEvent) {

        if(actief == true) {

        }
    }

//    }
//    //Actieve employees worden verwijderd van het project.
//    public void removeEmployeeFromList (ActionEvent actionevent) {
//
//    }
}