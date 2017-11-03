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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.webedu.hourregistration.dao.IEmployeeDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.ContractModel;
import nl.webedu.hourregistration.model.EmployeeModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ContractController {

    @FXML
    private AnchorPane root;
    @FXML
    public JFXListView<?> employeeList;
    @FXML
    public Text max_uren;
    @FXML
    public Text min_uren;
    @FXML
    public Text start_datum;
    @FXML
    public Text eind_datum;
    @FXML
    public JFXButton btnTerug;

    private IEmployeeDAO eDAO;
    private List<EmployeeModel> employees;
    private List<ContractModel> contracts;

    /**
     * Wordt aangeroepen wanneer de view wordt opgestart, de DAO's worden geïnitialiseerd.
     */
    public void initialize() {
        eDAO = DatabaseManager.getInstance().getDaoFactory().getEmployeeDAO();
        loadData();
    }

    /**
     * Zorgt ervoor dat de gegevens van de werknemer worden geladen als erop wordt geklikt.
     * @param mouseEvent het event wat zorgt voor het aanroepen van de methode.
     */
    public void employeeSelect(MouseEvent mouseEvent) throws NullPointerException {
        int index = employeeList.getSelectionModel().getSelectedIndex();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        max_uren.setText(String.valueOf(contracts.get(index).getMaxHours()));
        min_uren.setText(String.valueOf(contracts.get(index).getMinHours()));
        start_datum.setText(sdf.format(contracts.get(index).getStartTime()));
        eind_datum.setText(sdf.format(contracts.get(index).getEndTime()));
    }

    /**
     * Zorgt voor het laden van de werknemer data in de listview.
     */
    public void loadData(){
        ObservableList list = FXCollections.observableArrayList();
        contracts = new ArrayList<>();
        employees = eDAO.getAllEmployees();
        for(EmployeeModel employee : employees) {
            list.add(employee.getLastname() + ", " + employee.getFirstname());
            if(employee.getContract() != null)
                contracts.add(employee.getContract());
            System.out.println("No contract found for " + employee.getLastname());
        }
        employeeList.getItems().addAll(list);
    }

    /**
     * Gaat terug naar de vorige view.
     * @param actionEvent het event wat zorgt voor het aanroepen van de methode.
     */
    public void returnToTimesheets(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) root.getScene().getWindow();
        primaryStage.hide();
    }
}
