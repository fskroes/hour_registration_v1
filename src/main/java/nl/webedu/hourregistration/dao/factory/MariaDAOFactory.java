package nl.webedu.hourregistration.dao.factory;

import nl.webedu.hourregistration.dao.*;
import nl.webedu.hourregistration.dao.mariadb.*;

public class MariaDAOFactory extends DAOFactory {

    private static MariaDAOFactory instance;

    private MariaDAOFactory() {}

    public static MariaDAOFactory getInstance() {
        if (instance == null) {
            instance = new MariaDAOFactory();
        }
        return instance;
    }

    @Override
    public IActivitiesDAO getActivitiesDAO() {
        return MariadbActivitiesDAO.getInstance();
    }

    @Override
    public IContractDAO getContractDAO() {
        return MariadbContractDAO.getInstance();
    }

    @Override
    public ICustomerDAO getCustomerDAO() {
        return MariadbCustomerDAO.getInstance();
    }

    @Override
    public IEmployeeDAO getEmployeeDAO() {
        return MariadbEmployeeDAO.getInstance();
    }

    @Override
    public ILogDAO getLogDAO() {
        return MariadbLogDAO.getInstance();
    }

    @Override
    public IProjectDAO getProjectDAO() {
        return MariadbProjectDAO.getInstance();
    }

    @Override
    public IReportDAO getReportDAO() {
        return MariadbReportDAO.getInstance();
    }

    @Override
    public ISubjectDAO getSubjectDAO() {
        return MariadbSubjectDAO.getInstance();
    }

    @Override
    public IUserAuthenticationDAO getUserAuthenticationDAO() {
        return MariadbUserAuthenticationDAO.getInstance();
    }

    @Override
    public IWorkdayDAO getWorkdayDAO() {
        return MariadbWorkdayDAO.getInstance();
    }
}
