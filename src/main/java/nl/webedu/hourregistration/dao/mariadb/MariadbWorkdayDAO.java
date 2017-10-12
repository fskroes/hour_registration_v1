package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IWorkdayDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.WorkdayModel;

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
    public boolean insertWorkday(WorkdayModel Workday) {
        return false;
    }

    @Override
    public boolean deleteWorkday(String id) {
        return false;
    }

    @Override
    public WorkdayModel findWorkday(String id) {
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
