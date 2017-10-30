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

public class ProjectInfo {

    @FXML
    private Text ProjectName, ProjectTime, StartDate, EndDate;
    private CustomerModel customer;
    private EditProject controller;

//    private FXMLLoader loader;
//    private CustomerList controller;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public void initialize() throws IOException {
    }

    public void showProject(CustomerModel customer){
        this.customer = customer;
        ProjectName.setText(customer.getProjectModel().getName());
        ProjectTime.setText(customer.getProjectModel().getId());
        StartDate.setText(sdf.format(customer.getProjectModel().getStartDate()));
        EndDate.setText(sdf.format(customer.getProjectModel().getEndDate()));
    }
    public CustomerModel getCustomer (){
        return customer;
    }

}
