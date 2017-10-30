package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IWorkdayDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.WorkdayModel;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MariadbWorkdayDAO implements IWorkdayDAO {

    private static MariadbWorkdayDAO instance;
    private MariaDatabaseExtension database;

    private MariadbWorkdayDAO() {
        this.database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();
    }

    public static MariadbWorkdayDAO getInstance() {
        if (instance == null) {
            instance = new MariadbWorkdayDAO();
        }
        return instance;
    }

    @Override
    public boolean insertWorkday(WorkdayModel workday) {
        String querySQL = "INSERT INTO workday"
                + "(date, week_number, start_time, end_time, workday_day) VALUES"
                + "(?,?,?,?,?)";
        try {
            database.insertQuery(
                    querySQL,
                    workday.getDate(),
                    workday.getWeekNumber(),
                    workday.getStartTime(),
                    workday.getEndTime(),
                    workday.getDay()
            );
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int deleteWorkday(WorkdayModel workday) {
        int result = 0;
        String querySQL = "DELETE workday"
                + " WHERE workdayID = ?";
        try {
            result = database.deleteQuery(querySQL, workday.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public WorkdayModel findWorkday(String id) {
        WorkdayModel workday = null;
        try {
            workday = database.selectObjectSingle(new WorkdayModel(), "SELECT * FROM workday WHERE workdayID = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workday;
    }

    @Override
    public int updateWorkday(WorkdayModel workday) {
        int result = 0;
        String updateSQL = "UPDATE workday"
                + " SET date = ?, week_number = ?, start_time = ?, end_time = ?"
                + " WHERE workdayID = ?";
        try {
            database.updateQuery(
                    updateSQL,
                    workday.getDay(),
                    workday.getWeekNumber(),
                    workday.getStartTime(),
                    workday.getEndTime(),
                    workday.getId()
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<WorkdayModel> selectAllWorkdays() {
        List<WorkdayModel> workday = null;
        try {
            workday = database.selectObjectList(new WorkdayModel(), "SELECT * FROM workday");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workday;
    }

    //Sorry moet opnieuw!!!
    //moet een lijst terug geven van alle werkdagen die een employee heeft gewerkt.
    @Override
    public List<WorkdayModel> selectWorkdaysByEmployee(EmployeeModel employee) {
        WorkdayModel workday = null;
        try {
            workday = database.selectObjectSingle(new WorkdayModel(), "SELECT * FROM workday WHERE id = ?", String.valueOf(employee.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return workday;
        return null;
    }
}
