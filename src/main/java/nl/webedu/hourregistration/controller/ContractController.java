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
    private JFXListView<?> contract;
    @FXML
    private Text max_uren;
    @FXML
    private Text min_uren;
    @FXML
    private Text start_datum;
    @FXML
    private Text eind_datum;

    public void initialize() {
        cDAO = DatabaseManager.getInstance().getDaoFactory().getContractDAO();
        loadData();
    }

    public void employeeSelect(MouseEvent mouseEvent) {

    }

    public void loadData(){

    }
}
