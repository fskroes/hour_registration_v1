package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private FXMLLoader loader;
    private ProjectInfo controller;


    @FXML
    private JFXListView<?> ListView;

    public void initialize() throws IOException {
        customerDAO = DatabaseManager.getInstance().getDaoFactory().getCustomerDAO();
        loader = new FXMLLoader(getClass().getResource("/ProjectInfo.fxml"));
        controller = loader.getController();
        loadData();
    }

    public void loadData(){
        obsList.removeAll();
        customers = customerDAO.selectAllCustomers();
        for(CustomerModel customer : customers){
            obsList.add(customer.getBusinessName());
        }

        ListView.getItems().addAll(obsList);

    }

    public void CustomerSelect(MouseEvent mouseEvent) {
        System.out.println("ik klik");
        index = ListView.getSelectionModel().getSelectedIndex();
        System.out.println(index);
        controller.showProject(index, customers);
    }

}
