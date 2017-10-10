package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IActivitiesDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ActivitiesModel;

import java.util.Collection;

public class MariadbActivitiesDAO implements IActivitiesDAO {

    private MariaDatabaseExtension database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();

    private MariadbActivitiesDAO() {
        this.database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();
    }

    @Override
    public boolean insertActivitie(ActivitiesModel activitie) {
        return true;
    }

    @Override
    public ActivitiesModel findActivitie(int id) {

        return null;
    }

    @Override
    public boolean deleteActivitie(int id) {

        return false;
    }

    @Override
    public boolean updateActivitie(ActivitiesModel activitie) {
        return false;
    }

    @Override
    public Collection selectActivitiesByWorkday(int wordkdatId) {
        return null;
    }

    @Override
    public ActivitiesModel selectActivitiesByEmployee(int employeeId) {
        return null;
    }
}
