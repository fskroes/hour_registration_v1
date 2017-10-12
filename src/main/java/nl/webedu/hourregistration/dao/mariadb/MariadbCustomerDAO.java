package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.ICustomerDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.CustomerModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class MariadbCustomerDAO implements ICustomerDAO {

    private static MariadbCustomerDAO instance;
    private MariaDatabaseExtension client = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();

    private MariadbCustomerDAO() {
        this.client = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();
    }

    public static MariadbCustomerDAO getInstance() {
        if (instance == null) {
            instance = new MariadbCustomerDAO();
        }
        return instance;
    }

    @Override
    public boolean insertCustomer(CustomerModel customer) {
        try {
            String query = "INSERT INTO customer"
                    + "(company_name, project_name) VALUES"
                    + "(?,?)";

            PreparedStatement ps = client.openConnection().prepareStatement(query);
            ps.setString(1, customer.getBusinessName());
            ps.setString(2, customer.getProjectModel().getName());
            ps.executeQuery();
            ps.close();
            client.closeConnecion();
            System.out.println("Query: " + query + " = Succes");

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteCustomer(CustomerModel customer) {

        Connection dbConnection = null;
        PreparedStatement ps = null;

        String delSQL = "DELETE customer"
                + " WHERE customerID = ?";

        try {
            dbConnection = client.getConnection();
            ps = client.getConnection().prepareStatement(delSQL);

            ps.setString(1, customer.getId());

            ps.executeUpdate();

            System.out.println("Record deleted");
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        finally {
            if (ps != null) {
                try {
                    ps.getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public CustomerModel findCustomer(String id) {

        CustomerModel customer = null;
        try {
            customer = client.selectObjectSingle(new CustomerModel(), "SELECT * FROM customer WHERE customerID = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public boolean updateCustomer(CustomerModel customer) {

        Connection dbConnection = null;
        PreparedStatement ps = null;

        String updateSQL = "UPDATE customer"
                + " SET company_name = ?"
                + " WHERE customerID = ?";

        try {
            dbConnection = client.getConnection();
            ps = client.getConnection().prepareStatement(updateSQL);

            ps.setString(1, customer.getBusinessName());

            ps.executeUpdate();

            System.out.println("Record geupdate");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public Collection selectCustomersByProject(int projectId) {

        List<CustomerModel> customer = null;
        try {
            customer = client.selectObjectList(new CustomerModel(), "SELECT * FROM customer WHERE projectID = ?", projectId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
