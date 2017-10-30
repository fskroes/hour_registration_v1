package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import nl.webedu.hourregistration.dao.ICustomerDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.CustomerModel;

import java.io.IOException;
import java.util.List;


public class CustomerListController {

    ObservableList obsList = FXCollections.observableArrayList();

    private ICustomerDAO customerDAO;
    private List<CustomerModel> customers;
    private int index;
    private FXMLLoader loader;
    private ProjectInfoController controller;
    private Parent root;


    @FXML
    private JFXListView<?> ListView;

    public void initialize() throws IOException {
        customerDAO = DatabaseManager.getInstance().getDaoFactory().getCustomerDAO();
        loader = new FXMLLoader(getClass().getResource("/ProjectInfoView.fxml"));
        loadData();
    }

    public void loadData() throws IOException{
        root = (Parent) loader.load();
        controller = loader.getController();
        obsList.removeAll();
        customers = customerDAO.selectAllCustomers();
        for(CustomerModel customer : customers){
            obsList.add(customer.getBusinessName());
        }

        ListView.getItems().addAll(obsList);

    }

    public void CustomerSelect(MouseEvent mouseEvent) {

        index = ListView.getSelectionModel().getSelectedIndex();
        controller.showProject(index, customers);
    }
    public void setController(ProjectInfoController controller){
        this.controller = controller;
    }

}
