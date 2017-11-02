package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.WorkdayModel;

import java.util.List;

public interface IWorkdayDAO {
    /**
     * insert a new workday in the database
     * @param Workday
     * @return boolean - done or faulted
     */
    public int insertWorkday(WorkdayModel Workday);

    /**
     * delete a existing workday in the database
     * @param workday
     * @return int
     */
    public int deleteWorkday(WorkdayModel workday);

    /**
     * find a specific workday by id
     * @param id
     * @return
     */
    public WorkdayModel findWorkday(String id);

    /**
     * update a existing workday
     * @param Workday
     * @return int
     */
    public int updateWorkday(WorkdayModel Workday);

    /**
     * get all workdays
     * @return List<WorkdayModel>
     */
    public List<WorkdayModel> selectAllWorkdays();

    /**
     * Get all workday for a specific employee
     * @param employee
     * @return List<WorkdayModel>
     */
    public List<WorkdayModel> selectWorkdaysByEmployee(EmployeeModel employee);
}
