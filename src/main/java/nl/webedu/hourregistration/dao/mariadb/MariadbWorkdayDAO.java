package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IWorkdayDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.WorkdayModel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MariadbWorkdayDAO implements IWorkdayDAO {

    private static MariadbWorkdayDAO instance;
    private MariaDatabaseExtension client;

    private MariadbWorkdayDAO() {
        this.client = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase().getConnection();
    }

    public static MariadbWorkdayDAO getInstance() {
        if (instance == null) {
            instance = new MariadbWorkdayDAO();
        }
        return instance;
    }

    @Override
    public boolean insertWorkday(WorkdayModel workday) {
        try {
            String query = "INSERT INTO workday"
                    + "(date, week_number, start_time, end_time) VALUES"
                    + "(?,?,?,?)";

            PreparedStatement ps = client.openConnection().prepareStatement(query);
            ps.setDate(1, (Date) workday.getDate());
            ps.setInt(2, workday.getWeekNumber());
            ps.setDate(3, (Date) workday.getStartTime());
            ps.setDate(4, (Date) workday.getEndTime());
            ps.executeQuery();
            ps.close();
            client.closeConnecion();
            System.out.println("Query: " + query + " = Succes");

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteWorkday(int id) {
        return false;
    }

    @Override
    public WorkdayModel findWorkday(int id) {
        return null;
    }

    @Override
    public boolean updateWorkday(WorkdayModel Workday) {
        return false;
    }

    @Override
    public WorkdayModel selectWorkdayByEmployee(int employeeId) {
        WorkdayModel workday = null;
        try {
            workday = client.selectObjectSingle(new WorkdayModel(), "SELECT * FROM report WHERE id = ?", employeeId + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workday;
    }
}
