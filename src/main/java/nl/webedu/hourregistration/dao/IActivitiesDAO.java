package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.ActivitiesModel;
import java.util.Collection;
import java.util.Date;

public interface IActivitiesDAO {
    public boolean insertActivitie(ActivitiesModel activitie);
    public ActivitiesModel findActivitie(int id);
    public boolean deleteActivitie(int id);
    public boolean updateActivitie(ActivitiesModel activitie);
    public Collection selectActivitiesByWorkday(int wordkdatId);
    public Collection selectActivitiesByEmployee(int employeeId);
}
