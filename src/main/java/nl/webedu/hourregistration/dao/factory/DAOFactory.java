package nl.webedu.hourregistration.dao.factory;

import nl.webedu.hourregistration.dao.*;

public abstract class DAOFactory {
    /**
     * abstract class activities from the database
     * @return
     */
    public abstract IActivitiesDAO getActivitiesDAO();

    /**
     * abstract class contracts from the database
     * @return
     */
    public abstract IContractDAO getContractDAO();

    /**
     * abstract class customers from the database
     * @return
     */
    public abstract ICustomerDAO getCustomerDAO();

    /**
     * abstract class employees from the database
     * @return
     */
    public abstract IEmployeeDAO getEmployeeDAO();

    /**
     * abstract class logs from the database
     * @return
     */
    public abstract ILogDAO getLogDAO();

    /**
     * abstract class projects from the database
     * @return
     */
    public abstract IProjectDAO getProjectDAO();

    /**
     * abstract class reports from the database
     * @return
     */
    public abstract IReportDAO getReportDAO();

    /**
     * abstract class Userauthentication from the database
     * @return
     */
    public abstract IUserAuthenticationDAO getUserAuthenticationDAO();

    /**
     * abstract class workdays from the database
     * @return
     */
    public abstract IWorkdayDAO getWorkdayDAO();

}
