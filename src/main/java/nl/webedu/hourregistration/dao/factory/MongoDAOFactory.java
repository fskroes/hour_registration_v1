package nl.webedu.hourregistration.dao.factory;

import nl.webedu.hourregistration.dao.*;
import nl.webedu.hourregistration.dao.mongodb.*;

/**
 * DAO Factory for Mongo database
 */
public class MongoDAOFactory extends DAOFactory {

    private static MongoDAOFactory instance;

    private MongoDAOFactory() {  }

    public static MongoDAOFactory getInstance() {
        if (instance == null)
            instance = new MongoDAOFactory();
        return instance;
    }

    @Override
    public IActivitiesDAO getActivitiesDAO() {
        return MongoActivitiesDAO.getInstance();
    }

    @Override
    public IContractDAO getContractDAO() {
        return MongoContractDAO.getInstance();
    }

    @Override
    public ICustomerDAO getCustomerDAO() {
        return MongoCustomerDAO.getInstance();
    }

    @Override
    public IEmployeeDAO getEmployeeDAO() {
        return MongoEmployeeDAO.getInstance();
    }

    @Override
    public ILogDAO getLogDAO() {
        return null;
    }

    @Override
    public IProjectDAO getProjectDAO() {
        return MongoProjectDAO.getInstance();
    }

    @Override
    public IReportDAO getReportDAO() {
        return MongoReportDAO.getInstance();
    }

    @Override
    public IUserAuthenticationDAO getUserAuthenticationDAO() {
        return MongoUserAuthenticationDAO.getInstance();
    }

    @Override
    public IWorkdayDAO getWorkdayDAO() {
        return MongoWorkdayDAO.getInstance();
    }
}
