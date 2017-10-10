package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.ICustomerDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.CustomerModel;

import java.util.Collection;

public class MariadbCustomerDAO implements ICustomerDAO {


    private MariadbCustomerDAO() {

    }

    @Override
    public boolean insertCustomer(CustomerModel customer){

        return false;
    }

    @Override
    public boolean deleteCustomer(int id){

        return false;
    }

    @Override
    public CustomerModel findCustomer(int id){

        return null;
    }

    @Override
    public boolean updateCustomer(CustomerModel customer){

        return false;
    }

    @Override
    public Collection selectCustomersByProject(int projectId){

        return null;
    }
}
