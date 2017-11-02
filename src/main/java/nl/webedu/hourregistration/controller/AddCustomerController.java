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


public class AddCustomerController {

    @FXML
    JFXTextField CustomerNameText;

    private ICustomerDAO customerDAO;
    private IProjectDAO projectDAO;

    private CustomerModel customer;

    private CustomerListController controller;

    public void initialize() throws IOException {
        customerDAO = DatabaseManager.getInstance().getDaoFactory().getCustomerDAO();
        projectDAO = DatabaseManager.getInstance().getDaoFactory().getProjectDAO();
    }


    public void newCustomer(){
        if (!CustomerNameText.getText().equals("")) {
            String id = customerDAO.insertCustomer(new CustomerModel(CustomerNameText.getText()));
            customer = customerDAO.findCustomer(id);
            ProjectModel newProjectModel = new ProjectModel("-", null, null, null);
            newProjectModel.setCustomer(customer);
            projectDAO.insertProject(newProjectModel);
            customer.setProjectModel(projectDAO.selectProjectByCustomer(customer));
            controller.addCustomerToList(customer);
        }
    }
    public CustomerModel getCustomer(){
        return customer;
    }
    public void emptyNewCustomerText (){
        CustomerNameText.setText("");
    }
    public void setCustomerListController(CustomerListController controller) {
        this.controller = controller;
    }
}

