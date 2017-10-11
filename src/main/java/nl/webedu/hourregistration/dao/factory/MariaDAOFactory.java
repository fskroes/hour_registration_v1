package nl.webedu.hourregistration.dao.factory;

import nl.webedu.hourregistration.dao.*;

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
