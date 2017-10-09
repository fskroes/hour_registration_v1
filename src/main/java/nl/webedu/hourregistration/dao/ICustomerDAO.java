package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.CustomerModel;

import java.util.Collection;

public interface ICustomerDAO {
    public boolean insertCustomer(CustomerModel customer);
    public boolean deleteCustomer(int id);
    public CustomerModel findCustomer(int id);
    public boolean updateCustomer(CustomerModel customer);
    public Collection selectCustomersByProject(int projectId);
}
