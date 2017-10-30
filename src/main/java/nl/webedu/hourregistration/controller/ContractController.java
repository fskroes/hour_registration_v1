package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import nl.webedu.hourregistration.dao.IEmployeeDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.ContractModel;
import nl.webedu.hourregistration.model.EmployeeModel;

import java.text.SimpleDateFormat;
import java.util.List;

public class ContractController {

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

    private IEmployeeDAO eDAO;
    private List<EmployeeModel> employees;

    public void initialize() {
        eDAO = DatabaseManager.getInstance().getDaoFactory().getEmployeeDAO();
        loadData();
    }

    public void employeeSelect(MouseEvent mouseEvent) {
        int index = employeeList.getSelectionModel().getSelectedIndex();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        max_uren.setText(String.valueOf(employees.get(index).getContractModel().getMaxHours()));
        min_uren.setText(String.valueOf(employees.get(index).getContractModel().getMinHours()));
        start_datum.setText(sdf.format(employees.get(index).getContractModel().getStartTime()));
        eind_datum.setText(sdf.format(employees.get(index).getContractModel().getEndTime()));


    }

    public void loadData(){
        ObservableList list = FXCollections.observableArrayList();
        employees = eDAO.selectAllEmployees();
        for(EmployeeModel employee : employees) {
            list.add(employee.getLastname() + ", "+ employee.getFirstname());
            ContractModel m = eDAO.findContractByEmployee(employee);
            System.out.println("contract id :" + m.getId());
        }
        employeeList.getItems().addAll(list);
    }
}
