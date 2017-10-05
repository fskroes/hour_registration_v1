package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.CustomerModel;

import javax.sql.RowSet;
import java.util.Collection;

public interface ICustomerDAO {
    public int insertCustomer();
    public boolean deleteCustomer();
    public CustomerModel findCustomer();
    public boolean updateCustomer();
    public RowSet selectCustomersRS();
    public Collection selectCustomersTO();
}
