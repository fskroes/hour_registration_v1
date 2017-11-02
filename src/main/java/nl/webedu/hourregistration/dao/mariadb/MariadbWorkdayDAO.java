package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IWorkdayDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ActivitiesModel;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.WorkdayModel;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
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
                + "(date, week_number, start_time, end_time, day_name) VALUES"
                + "(?,?,?,?,?)";
        try {
            database.insertQuery(
                    querySQL,
                    Date.valueOf(workday.getDate()),
                    workday.getWeekNumber(),
                    Time.valueOf(workday.getStartTime()),
                    Time.valueOf(workday.getEndTime()),
                    workday.getDayName()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (ActivitiesModel activity : workday.getActivities()) {
            if (DatabaseManager.getInstance().getDaoFactory().getActivitiesDAO().findActivitie(activity.getId()) == null) {
                DatabaseManager.getInstance().getDaoFactory().getActivitiesDAO().insertActivitie(activity);
            } else {
                DatabaseManager.getInstance().getDaoFactory().getActivitiesDAO().updateActivitie(activity);
            }
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
                + " SET date = ?, week_number = ?, start_time = ?, end_time = ?, day_name = ?"
                + " WHERE workdayID = ?";
        try {
            database.updateQuery(
                    updateSQL,
                    Date.valueOf(workday.getDate()),
                    workday.getWeekNumber(),
                    Time.valueOf(workday.getStartTime()),
                    Time.valueOf(workday.getEndTime()),
                    workday.getDayName(),
                    workday.getId()
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        for (ActivitiesModel activity : workday.getActivities()) {
            if (DatabaseManager.getInstance().getDaoFactory().getActivitiesDAO().findActivitie(activity.getId()) == null) {
                DatabaseManager.getInstance().getDaoFactory().getActivitiesDAO().insertActivitie(activity);
            } else {
                DatabaseManager.getInstance().getDaoFactory().getActivitiesDAO().updateActivitie(activity);
            }
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

    @Override
    public List<WorkdayModel> selectWorkdaysByEmployee(EmployeeModel employee) {
        List<WorkdayModel> workdays = null;
        try {
            workdays = database.selectObjectList(
                    new WorkdayModel(),
                    "SELECT * FROM workday INNER JOIN employee_workday ON workday.workdayID = employee_workday.fk_workday_id WHERE employee_workday.fk_employee_id = ?",
                    employee.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workdays;
    }
}
