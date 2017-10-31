package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import nl.webedu.hourregistration.dao.ICustomerDAO;
import nl.webedu.hourregistration.dao.IProjectDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.CustomerModel;
import nl.webedu.hourregistration.model.ProjectModel;

import java.io.IOException;

public class AddCustomer {

    @FXML
    JFXTextField CustomerNameText;

    private ICustomerDAO customerDAO;
    private IProjectDAO projectDAO;

    public void initialize() throws IOException {
        customerDAO = DatabaseManager.getInstance().getDaoFactory().getCustomerDAO();
        projectDAO = DatabaseManager.getInstance().getDaoFactory().getProjectDAO();
    }


    public void newCustomer(){
        String id = customerDAO.insertCustomer(new CustomerModel(CustomerNameText.getText()));
        CustomerModel customer = customerDAO.findCustomer(id);
        projectDAO.insertProject(new ProjectModel("-", null, null, customer.getId()));
    }
}
