package nl.webedu.hourregistration.controller;


import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import nl.webedu.hourregistration.dao.ICustomerDAO;
import nl.webedu.hourregistration.dao.IProjectDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.CustomerModel;
import nl.webedu.hourregistration.model.ProjectModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class EditProject {


    @FXML
    private JFXTextField ProjectName;
    @FXML
    private DatePicker EndDate, StartDate;

    CustomerModel customer;
    private ICustomerDAO customerDAO;
    private IProjectDAO projectDAO;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public void initialize()
    {
        customerDAO = DatabaseManager.getInstance().getDaoFactory().getCustomerDAO();
        projectDAO = DatabaseManager.getInstance().getDaoFactory().getProjectDAO();
    }
    public void setCustomer(CustomerModel customer){
        this.customer = customer;
    }

    public void saveProject(){
        if(!ProjectName.getText().equals(null) && StartDate.getValue() != null && EndDate.getValue() != null) {
            customer.getProjectModel().setName(ProjectName.getText());
            customer.getProjectModel().setStartDate(localDateToDate(StartDate.getValue()));
            customer.getProjectModel().setEndDate(localDateToDate(EndDate.getValue()));
            projectDAO.updateProject(customer.getProjectModel());
        }

    }

    public void setDefaults(){
        if (customer.getProjectModel().getName().equals("-")){
            ProjectName.setText("");
        }
        else{
            ProjectName.setText(customer.getProjectModel().getName());
        }

        if (customer.getProjectModel().getStartDate()!= null){
            StartDate.setValue(dateToLocalDate(customer.getProjectModel().getStartDate()));
        }
        else{
            StartDate.setValue(null);
        }
        if (customer.getProjectModel().getEndDate() != null){
            EndDate.setValue(dateToLocalDate(customer.getProjectModel().getEndDate()));
        }
        else{
            EndDate.setValue(null);
        }
    }

    private LocalDate dateToLocalDate(Date date){
        String dateString = sdf.format(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    private Date localDateToDate (LocalDate localDate){
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return date;
    }
    public CustomerModel getCustomer(){
        return customer;
    }
}