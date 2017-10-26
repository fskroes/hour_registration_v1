package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.webedu.hourregistration.dao.ICustomerDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.CustomerModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CustomerController {

    ObservableList list = FXCollections.observableArrayList();

    private ICustomerDAO customerDAO;
    private List<CustomerModel> customers;
    private Boolean geactiveerd = false;
    private Boolean loadData = true;


    @FXML
    private VBox   ListVbox, AddVbox, ListOnlyVbox;

    @FXML
    private JFXTextField CustomerNameText;

    @FXML
    private JFXListView<?> CustomerList;

    @FXML
    private Text ProjectName, ProjectTime, StartDate, EndDate;

    public void initialize() {
        customerDAO = DatabaseManager.getInstance().getDaoFactory().getCustomerDAO();
        if (loadData){
            loadData();
        }
    }

    @FXML
    public void AddCustomer(MouseEvent mouseEvent) throws IOException {

        if(geactiveerd){
            geactiveerd = false;
            loadData = true;
            ListVbox.getChildren().clear();
            ListVbox.getChildren().add(FXMLLoader.load(getClass().getResource("/LoginView")));
            loadData();
        }
        else{
            geactiveerd = true;
            loadData = false;
            ListVbox.getChildren().clear();
            ListVbox.getChildren().add(FXMLLoader.load(getClass().getResource("/AddCustomer.fxml")));
            CustomerList.getItems().removeAll();
        }


    }


    public void CustomerSelect(MouseEvent mouseEvent) {
        int index = CustomerList.getSelectionModel().getSelectedIndex();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        ProjectName.setText(customers.get(index).getProjectModel().getName());
        ProjectTime.setText(customers.get(index).getProjectModel().getId());
        StartDate.setText(sdf.format(customers.get(index).getProjectModel().getStartDate()));
        EndDate.setText(sdf.format(customers.get(index).getProjectModel().getEndDate()));

//        System.out.println(customers.get(0).getProjectModel());
//        System.out.println(CustomerList.getSelectionModel().getSelectedIndex());

    }
    public void loadData(){
        list.removeAll();
        customers = customerDAO.selectAllCustomers();
        for(CustomerModel customer : customers){
            list.add(customer.getBusinessName());
        }

        CustomerList.getItems().addAll(list);

    }

}
