package nl.webedu.hourregistration.dao.factory;

import nl.webedu.hourregistration.dao.*;

import java.sql.Connection;

public class MariaDAOFactory extends DAOFactory {

    public static final String DRIVER=
            "com.mariadb.jdbc.Driver";
    public static final String DBURL=
            "jdbc:mariadb://localhost:1099/CoreJ2EEDB";


    // method to create Cloudscape connections
    public static Connection createConnection() {
        // Use DRIVER and DBURL to create a connection
        // Recommend connection pool implementation/usage

        return null;
    }

    @Override
    public IActivitiesDAO getActivitiesDAO() {
        return null;
    }

    @Override
    public IContractDAO getContractDAO() {
        return null;
    }

    @Override
    public ICustomerDAO getCustomerDAO() {
        return null;
    }

    @Override
    public IEmployeeDAO getEmployeeDAO() {
        return null;
    }

    @Override
    public ILogDAO getLogDAO() {
        return null;
    }

    @Override
    public IProjectDAO getProjectDAO() {
        return null;
    }

    @Override
    public IReportDAO getReportDAO() {
        return null;
    }

    @Override
    public ISubjectDAO getSubjectDAO() {
        return null;
    }

    @Override
    public IUserAuthenticationDAO getUserAuthenticationDAO() {
        return null;
    }

    @Override
    public IWorkdayDAO getWorkdayDAO() {
        return null;
    }
}
