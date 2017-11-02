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

    public void initialize() {
        eDAO = DatabaseManager.getInstance().getDaoFactory().getEmployeeDAO();
        loadData();
    }

    public void employeeSelect(MouseEvent mouseEvent) {
        int index = employeeList.getSelectionModel().getSelectedIndex();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        max_uren.setText(String.valueOf(contracts.get(index).getMaxHours()));
        min_uren.setText(String.valueOf(contracts.get(index).getMinHours()));
        start_datum.setText(sdf.format(contracts.get(index).getStartTime()));
        eind_datum.setText(sdf.format(contracts.get(index).getEndTime()));
    }

    public void loadData(){
        ObservableList list = FXCollections.observableArrayList();
        contracts = new ArrayList<>();
        employees = eDAO.getAllEmployees();
        for(EmployeeModel employee : employees) {
            list.add(employee.getLastname() + ", " + employee.getFirstname());
            contracts.add(eDAO.findContractByEmployee(employee));
        }
        employeeList.getItems().addAll(list);
    }

    public void toPreviousView (ActionEvent actionEvent) {
        Stage primaryStage = (Stage) root.getScene().getWindow();
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
}
