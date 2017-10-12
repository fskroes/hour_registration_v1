package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IActivitiesDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ActivitiesModel;
import nl.webedu.hourregistration.model.WorkdayModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class MariadbActivitiesDAO implements IActivitiesDAO {

    private static MariadbActivitiesDAO instance;
    private MariaDatabaseExtension client = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();

    private MariadbActivitiesDAO() {
        this.client = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();
    }

    public static MariadbActivitiesDAO getInstance() {
        if (instance == null) {
            instance = new MariadbActivitiesDAO();
        }
        return instance;
    }

    @Override
    public boolean insertActivitie(ActivitiesModel activitie) {
        try {
            String query = "INSERT INTO activity"
                    + "(category, start_time, end_time, fk_workdayID) VALUES"
                    + "(?,?,?,?)";

            PreparedStatement ps = client.openConnection().prepareStatement(query);
            ps.setString(1, activitie.getCategory());
            ps.setDate(2, (Date) activitie.getStartTime());
            ps.setDate(3, (Date) activitie.getEndTime());
            ps.setInt(4, activitie.getWorkdayId());
            ps.executeQuery();
            ps.close();
            client.closeConnecion();
            System.out.println("Query: " + query + " = Succes");

            ps.setString(1, activitie.getCategory());
            ps.setDate(2, (Date) activitie.getStartTime());
            ps.setDate(3, (Date) activitie.getEndTime());
            ps.setObject(5, activitie.getWorkdayId());

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public ActivitiesModel findActivitie(int id) {

        ActivitiesModel activities = null;
        try {
            activities = client.selectObjectSingle(new ActivitiesModel(), "SELECT * FROM activities WHERE activityID = ?", id + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }

    @Override
    //Is not done yet.
    public boolean deleteActivitie(ActivitiesModel activitie) {

        Connection dbConnection = null;
        PreparedStatement ps = null;

        String deleteSQL = "DELETE activity"
                + " WHERE activityID = ?";

        try {
            dbConnection = client.getConnection();
            ps = client.getConnection().prepareStatement(deleteSQL);

            ps.setString(1, activitie.getActivityId());

            ps.executeUpdate();

            System.out.println("Record deleted");
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
    }

    @Override
    public boolean updateActivitie(ActivitiesModel activitie) {

        Connection dbConnection = null;
        PreparedStatement ps = null;

        String updateSQL = "UPDATE activity"
                + " SET category = ?, start_time = ?, end_time = ?"
                + " WHERE activityID = ?";

        try {
            dbConnection = client.getConnection();
            ps = client.getConnection().prepareStatement(updateSQL);

            ps.setString(1, activitie.getCategory());
            ps.setDate(2, (Date) activitie.getStartTime());
            ps.setDate(3, (Date) activitie.getEndTime());
            ps.setString(4, activitie.getActivityId());

            ps.executeUpdate();

            System.out.println("Record geupdate");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
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
    }

    @Override
    public Collection<ActivitiesModel> selectActivitiesByWorkday(int wordkdatId) {

        List<ActivitiesModel> activities = null;

        try {
            activities = client.selectObjectList(new ActivitiesModel(), "SELECT * FROM activity WHERE activityID = ?", wordkdatId);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }

    @Override
    public Collection<ActivitiesModel> selectActivitiesByEmployee(int employeeId) {
        List<ActivitiesModel> activities = null;

        try {
            activities = client.selectObjectList(new ActivitiesModel(), "SELECT * FROM activity WHERE activityID = ?", employeeId);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }
}
