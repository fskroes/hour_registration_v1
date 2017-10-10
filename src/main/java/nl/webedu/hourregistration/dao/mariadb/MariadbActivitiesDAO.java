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

        String insertActivitieSQL = "INSERT INTO DBUSER"
                + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) VALUES"
                + "(?,?,?,?)";

        PreparedStatement ps = dbConnection.prepareStatement(insertActivitiesSQL);
        ps.setInt(1, 11);
        ps.setString(2, "mkyong");
        ps.setString(3, "system");
        ps.setTimestamp(4, get());
// execute insert SQL stetement
        preparedStatement .executeUpdate();
    }

    @Override
    public ActivitiesModel findActivitie(int id) {
        ActivitiesModel activities = database.selectObjectSingle(Activities, "SELECT * FROM activities WHERE activitie = ?", id);
        return activities;
    }

    @Override
    public boolean deleteActivitie(int id) {
        ActivitiesModel activities = database.selectObjectSingle(Activities, "DELETE FROM activities WHERE activitie = ?", id);
        return false;
    }

    @Override
    public boolean updateActivitie(ActivitiesModel activitie) {
        ActivitiesModel activities = database.selectObjectSingle(Activities, "UPDATE activities SET activitie = '' WHERE activitie = ?", activitie);
        return false;
    }

    @Override
    public ActivitiesModel selectActivitiesByWorkday(int wordkdatId) {
        ActivitiesModel activities = database.selectObjectSingle(Activities, "SELECT * FROM activities WHERE workday = ?", wordkdatId);

        return activities;
    }

    @Override
    public ActivitiesModel selectActivitiesByEmployee(int employeeId) {
        ActivitiesModel activities = database.selectObjectSingle(Activities, "SELECT * FROM activities WHERE name = ?", employeeId);
        return activities;
    }
}
