package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import nl.webedu.hourregistration.dao.ICustomerDAO;
import nl.webedu.hourregistration.dao.IUserAuthenticationDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.CustomerModel;

import java.lang.management.MemoryManagerMXBean;
import java.text.SimpleDateFormat;
import java.util.List;

public class CustomerController {

    ObservableList list = FXCollections.observableArrayList();

    private ICustomerDAO customerDAO;
    private List<CustomerModel> customers;

    @FXML
    private JFXListView<?> CustomerList;

    @FXML
    private Text ProjectTime;

    @FXML
    private Text StartDate;

    @FXML
    private Text EndDate;

    @FXML
    private Text ProjectName;

    public void initialize() {
        customerDAO = DatabaseManager.getInstance().getDaoFactory().getCustomerDAO();
        loadData();
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
