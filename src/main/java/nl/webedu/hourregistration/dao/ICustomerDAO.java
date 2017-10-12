package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.CustomerModel;

import java.util.ArrayList;
import java.util.Collection;

public interface ICustomerDAO {
    public boolean insertCustomer(CustomerModel customer);
    public boolean deleteCustomer(CustomerModel customer);
    public CustomerModel findCustomer(String id);
    public boolean updateCustomer(CustomerModel customer);
}
