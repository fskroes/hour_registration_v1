package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.WorkdayModel;

import java.util.List;

public interface IWorkdayDAO {
    public boolean insertWorkday(WorkdayModel Workday);
    public int deleteWorkday(WorkdayModel workday);
    public WorkdayModel findWorkday(String id);
    public int updateWorkday(WorkdayModel Workday);
    public List<WorkdayModel> selectAllWorkdays();
    public WorkdayModel selectWorkdayByEmployee(EmployeeModel employee);
}
