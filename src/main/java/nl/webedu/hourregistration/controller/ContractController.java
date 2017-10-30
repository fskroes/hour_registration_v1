package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import nl.webedu.hourregistration.dao.IContractDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.ContractModel;

import java.util.List;

public class ContractController {

    private IContractDAO cDAO;
    private List<ContractModel> contracts;

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

    public void initialize() {
        cDAO = DatabaseManager.getInstance().getDaoFactory().getContractDAO();
        loadData();
    }

    public void employeeSelect(MouseEvent mouseEvent) {

    }

    public void loadData(){

    }
}
