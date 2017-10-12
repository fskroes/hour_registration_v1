package nl.webedu.hourregistration.dao.factory;

import nl.webedu.hourregistration.dao.*;
import nl.webedu.hourregistration.dao.mongodb.MongoContractDAO;
import nl.webedu.hourregistration.dao.mongodb.MongoUserAuthenticationDAO;

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
        return null;
    }

    @Override
    public IContractDAO getContractDAO() {
        return MongoContractDAO.getInstance();
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
        return MongoUserAuthenticationDAO.getInstance();
    }

    @Override
    public IWorkdayDAO getWorkdayDAO() {
        return null;
    }
}
