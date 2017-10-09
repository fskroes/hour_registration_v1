package nl.webedu.hourregistration.factory;

import nl.webedu.hourregistration.daointerfaces.*;

public abstract class DAOFactory {

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

}
