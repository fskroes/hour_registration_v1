package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.CustomerModel;

import java.util.ArrayList;
import java.util.Collection;

public interface ICustomerDAO {
    public boolean insertCustomer(CustomerModel customer);
    public boolean deleteCustomer(CustomerModel customer);
    public CustomerModel findCustomer(int id);
    public ArrayList<CustomerModel> findCustomer(int id, String name);
    public CustomerModel findCustomer(String name);
    public boolean updateCustomer(CustomerModel customer);
    public Collection<CustomerModel> selectCustomersByProject(int projectId);
}
