package nl.webedu.hourregistration.factory;

import nl.webedu.hourregistration.daointerfaces.*;

public abstract class DAOFactory {
    public static final int MONGO = 1;
    public static final int MARIA = 2;

    public abstract IActivitiesDAO getActivitiesDAO();
    public abstract IContractDAO getContractDAO();
    public abstract ICustomerDAO getCustomerDAO();
    public abstract IEmployeeDAO getEmployeeDAO();
    public abstract ILogDAO getLogDAO();
    public abstract IProjectDAO getProjectDAO();
    public abstract IReportDAO getReportDAO();
    public abstract ISubjectDAO getSubjectDAO();
    public abstract IUserAuthenticationDAO getUserAuthenticationDAO();
    public abstract IWorkdayDAO getWorkdayDAO();


    public static DAOFactory getDAOFactory(
            int whichFactory) {

        switch (whichFactory) {
            case MONGO:
                return new MongoDAOFactory();
            case MARIA:
                return new MariaDAOFactory();
            default:
                return new MariaDAOFactory();
        }
    }
}
