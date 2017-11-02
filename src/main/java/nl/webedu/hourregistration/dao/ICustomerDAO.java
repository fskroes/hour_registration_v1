package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.CustomerModel;
import nl.webedu.hourregistration.model.ProjectModel;

import java.util.List;

public interface ICustomerDAO {
    public String insertCustomer(CustomerModel customer);
    public int deleteCustomer(CustomerModel customer);
    public CustomerModel findCustomer(String id);
    public int updateCustomer(CustomerModel customer);
    public List<CustomerModel> selectAllCustomers();
    public List<CustomerModel> selectCustomersByProject(ProjectModel project);
}
