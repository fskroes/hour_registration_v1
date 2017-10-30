package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import nl.webedu.hourregistration.dao.ICustomerDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.CustomerModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;


public class CustomerList {

    ObservableList obsList = FXCollections.observableArrayList();

    private ICustomerDAO customerDAO;
    private List<CustomerModel> customers;
    private int index;
    private ProjectInfo controller;


    @FXML
    private JFXListView<?> ListView;

    public void initialize() throws IOException {
        customerDAO = DatabaseManager.getInstance().getDaoFactory().getCustomerDAO();
        loadData();
    }

    public void loadData() throws IOException{
        obsList.removeAll();
        customers = customerDAO.selectAllCustomers();
        for(CustomerModel customer : customers){
            obsList.add(customer.getBusinessName());
        }

        ListView.getItems().addAll(obsList);

    }

    public void CustomerSelect(MouseEvent mouseEvent) {

        index = ListView.getSelectionModel().getSelectedIndex();
        controller.showProject(customers.get(index));
    }
    public void setProjectInfoController(ProjectInfo controller){
        this.controller = controller;
    }

}
