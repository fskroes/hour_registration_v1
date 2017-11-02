package nl.webedu.hourregistration.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import nl.webedu.hourregistration.model.CustomerModel;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class ProjectInfoController {

    @FXML
    private Text ProjectName, ProjectTime, StartDate, EndDate;
    private CustomerModel customer;
    private EditProject controller;

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
