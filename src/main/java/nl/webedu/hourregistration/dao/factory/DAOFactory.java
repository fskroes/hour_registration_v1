package nl.webedu.hourregistration.dao.factory;

import nl.webedu.hourregistration.dao.*;

public abstract class DAOFactory {

    public abstract IActivitiesDAO getActivitiesDAO();
    public abstract IContractDAO getContractDAO();
    public abstract ICustomerDAO getCustomerDAO();
    public abstract IEmployeeDAO getEmployeeDAO();
    public abstract ILogDAO getLogDAO();
    public abstract IProjectDAO getProjectDAO();
    public abstract IReportDAO getReportDAO();
    public abstract IUserAuthenticationDAO getUserAuthenticationDAO();
    public abstract IWorkdayDAO getWorkdayDAO();

}
