package nl.webedu.hourregistration.dao.factory;

import nl.webedu.hourregistration.dao.*;
import nl.webedu.hourregistration.dao.mariadb.MariadbContractDAO;
import nl.webedu.hourregistration.dao.mariadb.MariadbProjectDAO;
import nl.webedu.hourregistration.dao.mongodb.MongoUserAuthenticationDAO;

import java.sql.Connection;

public class MariaDAOFactory extends DAOFactory {

    private static MariaDAOFactory instance;

    private MariaDAOFactory() {  }

    public static MariaDAOFactory getInstance() {
        if (instance == null) {
            instance = new MariaDAOFactory();
        }
        return instance;
    }

    @Override
    public IActivitiesDAO getActivitiesDAO() {
        return null;
    }

    @Override
    public IContractDAO getContractDAO() {
        return MariadbContractDAO.getInstance();
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
        return MariadbProjectDAO.getInstance();
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
