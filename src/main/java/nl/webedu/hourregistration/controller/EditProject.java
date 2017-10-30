package nl.webedu.hourregistration.controller;


import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import nl.webedu.hourregistration.dao.ICustomerDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.CustomerModel;
import nl.webedu.hourregistration.model.ProjectModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class EditProject {


    @FXML
    private JFXTextField ProjectName;
    @FXML
    private DatePicker EndDate, StartDate;

    CustomerModel customer;
    private ICustomerDAO customerDAO;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public void initialize()
    {
        customerDAO = DatabaseManager.getInstance().getDaoFactory().getCustomerDAO();
    }
    public void setCustomer(CustomerModel customer){
        this.customer = customer;
    }

    public void saveProject(){
        customer.getProjectModel().setName(ProjectName.getText());

    }

    public void setDefaults(){
        ProjectName.setText(customer.getProjectModel().getName());
        StartDate.setValue(dateToLocalDate(customer.getProjectModel().getStartDate()));
    }

    private LocalDate dateToLocalDate(Date date){
        String dateString = sdf.format(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
