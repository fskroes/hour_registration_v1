package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IActivitiesDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ActivitiesModel;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.WorkdayModel;

import java.sql.SQLException;
import java.util.List;

public class MariadbActivitiesDAO implements IActivitiesDAO {

    private static MariadbActivitiesDAO instance;
    private MariaDatabaseExtension database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();

    private MariadbActivitiesDAO() {
        this.database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();
    }

    public static MariadbActivitiesDAO getInstance() {
        if (instance == null) {
            instance = new MariadbActivitiesDAO();
        }
        return instance;
    }

    @Override
    public boolean insertActivitie(ActivitiesModel activity) {
        int result = 0;
        String insertSQL = "INSERT INTO activity"
                + "(category, start_time, end_time, fk_workdayID) VALUES"
                + "(?,?,?,?)";

        try {
            database.insertQuery(insertSQL, activity.getStartTime(), activity.getEndTime(), activity.getWorkday());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ActivitiesModel findActivitie(String id) {
        ActivitiesModel activity = null;
        try {
            activity = database.selectObjectSingle(new ActivitiesModel(), "SELECT * FROM activity WHERE activityID = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activity;
    }

    @Override
    public int deleteActivitie(ActivitiesModel activity) {
        int result = 0;
        String deleteSQL = "DELETE activity"
                + " WHERE activityID = ?";
        try {
            result = database.updateQuery(deleteSQL, activity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateActivitie(ActivitiesModel activity) {
        int result = 0;
        String updateSQL = "UPDATE activity" +
                "SET category = ?, start_time = ?, end_time = ?" +
                "WHERE activityID = ?";
        try {
            result = database.updateQuery(updateSQL,
                    activity.getStartTime(),
                    activity.getEndTime(),
                    activity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<ActivitiesModel> selectAllActivities() {
        List<ActivitiesModel> activities = null;
        try {
            activities = database.selectObjectList(new ActivitiesModel(), "SELECT * FROM activity");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }

    @Override
    public List<ActivitiesModel> selectActivitiesByWorkday(WorkdayModel workday) {
        List<ActivitiesModel> activities = null;
        try {
            activities = database.selectObjectList(new ActivitiesModel(), "SELECT * FROM activity WHERE workdayID = ?", workday.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }

    @Override
    public List<ActivitiesModel> selectActivitiesByEmployee(EmployeeModel employee) {
        List<ActivitiesModel> activities = null;
        try {
            activities = database.selectObjectList(new ActivitiesModel(), "SELECT * FROM activity WHERE activityID = ?", String.valueOf(employee.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }
}
