package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.CustomerModel;
import nl.webedu.hourregistration.model.ProjectModel;

import java.util.List;

public interface ICustomerDAO {
    /**
     * Insert a customer
     * @param customer
     * @return String
     */
    public String insertCustomer(CustomerModel customer);

    /**
     * Delete a customer
     * @param customer
     * @return
     */
    public int deleteCustomer(CustomerModel customer);

    /**
     * Find Customer in the database
     * @param id
     * @return CustomerModel
     */
    public CustomerModel findCustomer(String id);

    /**
     * Update a existing customer
     * @param customer
     * @return int
     */
    public int updateCustomer(CustomerModel customer);

    /**
     * Get all customers from the database
     * @return List<CustomerModel>
     */
    public List<CustomerModel> selectAllCustomers();

    /**
     * Get a customer for a specific project
     * @param project
     * @return List<CustomerModel>
     */
    public List<CustomerModel> selectCustomersByProject(ProjectModel project);
}
