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


public class CustomerListController {

    ObservableList obsList = FXCollections.observableArrayList();

    private ICustomerDAO customerDAO;
    private List<CustomerModel> customers;
    private int index;
    private ProjectInfoController controller;


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

    public void customerSelect(MouseEvent mouseEvent) {
        index = ListView.getSelectionModel().getSelectedIndex();
        if(index != -1){
            controller.showProject(customers.get(index));
        }

    }
    public void setProjectInfoController(ProjectInfoController controller){
        this.controller = controller;
        controller.showProject(customers.get(0));

    }
    public void addCustomerToList(CustomerModel customer){
        customers.add(0, customer);
        obsList.add(0, customer.getBusinessName());
        ListView.getItems().clear();
        ListView.getItems().addAll(obsList);
        ListView.refresh();

    }

}
