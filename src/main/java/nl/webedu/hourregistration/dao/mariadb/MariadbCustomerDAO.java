package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.ICustomerDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.CustomerModel;
import nl.webedu.hourregistration.model.ProjectModel;

import java.sql.SQLException;
import java.util.List;

public class MariadbCustomerDAO implements ICustomerDAO {

    private static MariadbCustomerDAO instance;
    private MariaDatabaseExtension database;

    private MariadbCustomerDAO() {
        this.database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();
    }

    public static MariadbCustomerDAO getInstance() {
        if (instance == null) {
            instance = new MariadbCustomerDAO();
        }
        return instance;
    }

    @Override
    public boolean insertCustomer(CustomerModel customer) {
        String querySQL = "INSERT INTO customer"
                + "(company_name) VALUES"
                + "(?)";
        try {
            database.insertQuery(querySQL, customer.getBusinessName());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int deleteCustomer(CustomerModel customer) {
        int result = 0;
        String querySQL = "DELETE customer"
                + " WHERE customerID = ?";
        try {
            result = database.deleteQuery(querySQL, customer.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public CustomerModel findCustomer(String id) {
        CustomerModel customer = null;
        try {
            customer = database.selectObjectSingle(new CustomerModel(), "SELECT * FROM customer WHERE customerID = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public int updateCustomer(CustomerModel customer) {
        int result = 0;
        String querySQL = "UPDATE customer"
                + " SET company_name = ?"
                + " WHERE customerID = ?";
        try {
            result = database.updateQuery(querySQL, customer.getBusinessName(), customer.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<CustomerModel> selectAllCustomers() {
        List<CustomerModel> customer = null;
        try {
            customer = database.selectObjectList(new CustomerModel(), "SELECT * FROM customer");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<CustomerModel> selectCustomersByProject(ProjectModel project) {
        List<CustomerModel> customer = null;
        try {
            customer = database.selectObjectList(new CustomerModel(), "SELECT * FROM customer WHERE projectID = ?", project.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
