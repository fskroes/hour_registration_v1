package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.LogModel;

import java.util.Collection;

public interface ILogDAO {
    /**
     * Insert log
     * @param Log
     * @return boolean - done or faulted
     */
    public boolean insertLog(LogModel Log);

    /**
     * delete log
     * @param id
     * @return boolean - done or faulted
     */
    public boolean deleteLog(String id);

    /**
     * find a log by id
     * @param id
     * @return
     */
    public LogModel findLog(String id);

    /**
     * update log by inserting a new log file
     * @param Log
     * @return boolean - done or faulted
     */
    public boolean updateLog(LogModel Log);

    /**
     * select a log by giving a employee
     * @param employee
     * @return Collection<LogModel>
     */
    public Collection<LogModel> selectLogByEmployee(EmployeeModel employee);

}
