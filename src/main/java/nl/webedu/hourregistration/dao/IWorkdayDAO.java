package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.WorkdayModel;

import java.util.Collection;

public interface IWorkdayDAO {
    public boolean insertWorkday(WorkdayModel Workday);
    public boolean deleteWorkday(int id);
    public WorkdayModel findWorkday(int id);
    public boolean updateWorkday(WorkdayModel Workday);
    public Collection selectWorkdaysByEployee(int employeeId);
}
