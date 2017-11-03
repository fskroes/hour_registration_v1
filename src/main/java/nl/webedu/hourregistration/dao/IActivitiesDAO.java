package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.ActivitiesModel;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.WorkdayModel;

import java.util.List;

public interface IActivitiesDAO {
    /**
     * Method for inserting a activity in Maria or Mongo database
     * @param activitie
     * @return
     */
    public int insertActivitie(ActivitiesModel activitie);

    /**
     * Method for finding an activity in the database
     * @param id
     * @return boolean
     */
    public ActivitiesModel findActivitie(String id);

    /**
     * Method for searching and deleting an activity
     * @param activitie
     * @return int - amount of deleted rows
     */
    public int deleteActivitie(ActivitiesModel activitie);

    /**
     * Method for updating an existing activity
     * @param activitie
     * @return int - amount of edited rows
     */
    public int updateActivitie(ActivitiesModel activitie);

    /**
     * Method for getting all the activity in the database
     * @return List<ActivitiesModel>
     */
    public List<ActivitiesModel> selectAllActivities();

    /**
     * Method for getting activties by a specific workday
     * @param workday
     * @return List<ActivitiesModel>
     */
    public List<ActivitiesModel> selectActivitiesByWorkday(WorkdayModel workday);

    /**
     * Method for getting activities by a specific employee
     * @param employee
     * @return
     */
    public List<ActivitiesModel> selectActivitiesByEmployee(EmployeeModel employee);
}
