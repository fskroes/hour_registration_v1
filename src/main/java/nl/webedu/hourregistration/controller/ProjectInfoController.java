package nl.webedu.hourregistration.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import nl.webedu.hourregistration.model.CustomerModel;

import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ProjectInfoController {

    @FXML
    private Text ProjectName, ProjectTime, StartDate, EndDate;
    private CustomerModel customer;
    private EditProjectController controller;

//    private FXMLLoader loader;
//    private CustomerList controller;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public void initialize() throws IOException {
    }

    public void showProject(CustomerModel customer){
        this.customer = customer;
        ProjectName.setText(customer.getProjectModel().getName());
//        ProjectTime.setText(customer.getProjectModel().getId());

        if (customer.getProjectModel().getStartDate()!=null) {
            StartDate.setText(sdf.format(customer.getProjectModel().getStartDate()));
        }
        else{
            StartDate.setText("-");
        }
        if (customer.getProjectModel().getEndDate()!=null) {
            EndDate.setText(sdf.format(customer.getProjectModel().getEndDate()));
        }
        else {
            EndDate.setText("-");
        }

    }
    public CustomerModel getCustomer (){
        return customer;
    }
}
