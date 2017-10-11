package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IActivitiesDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ActivitiesModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class MariadbActivitiesDAO implements IActivitiesDAO {

    private MariaDatabaseExtension database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();

    private MariadbActivitiesDAO() {
        this.database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();
    }

    @Override
    public boolean insertActivitie(ActivitiesModel activitie) {

        Connection dbConnection = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO activity"
                + "(activityID, category, start_time, end_time, fk_workdayID) VALUES"
                + "(?,?,?,?,?)";
        try {
            dbConnection = database.getConnection();
            ps = database.getConnection().prepareStatement(sql);

            ps.setInt(1, activitie.getId());
            ps.setString(2, activitie.getCategory());
            ps.setDate(3, (Date) activitie.getStartTime());
            ps.setDate(4, (Date) activitie.getEndTime());
            ps.setObject(5, activitie.getWorkday());

            ps.executeUpdate();

            System.out.println("Record toegevoegd");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (ps != null) {
                try {
                    ps.getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    };

    @Override
    public ActivitiesModel findActivitie(int id) {

        ActivitiesModel activities = null;
        try {
            activities = database.selectObjectSingle(new ActivitiesModel(), "SELECT * FROM activities WHERE activityID = ?", id + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
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
    public Collection<ActivitiesModel> selectActivitiesByWorkday(int wordkdatId) {

        List<ActivitiesModel> activities = null;
        try {
            activities = database.selectObjectList(new ActivitiesModel(), "SELECT * FROM activity WHERE activityID = ?", wordkdatId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }

    @Override
    public ActivitiesModel selectActivitiesByEmployee(int employeeId) {
        ActivitiesModel activities = null;
        try {
            activities = database.selectObjectSingle(new ActivitiesModel(), "SELECT * FROM activity WHERE activityID = ?", employeeId + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }
}
