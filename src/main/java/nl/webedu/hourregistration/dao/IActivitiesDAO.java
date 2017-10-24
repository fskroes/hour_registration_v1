package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.ActivitiesModel;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.WorkdayModel;

import java.util.List;

public interface IActivitiesDAO {
    public boolean insertActivitie(ActivitiesModel activitie);
    public ActivitiesModel findActivitie(String id);
    public int deleteActivitie(ActivitiesModel activitie);
    public int updateActivitie(ActivitiesModel activitie);
    public List<ActivitiesModel> selectAllActivities();
    public List<ActivitiesModel> selectActivitiesByWorkday(WorkdayModel workday);
    public List<ActivitiesModel> selectActivitiesByEmployee(EmployeeModel employee);
}
