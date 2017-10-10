package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IWorkdayDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ReportModel;
import nl.webedu.hourregistration.model.WorkdayModel;

import java.util.Collection;

public class MariadbWorkdayDAO implements IWorkdayDAO {

    private MariaDatabaseExtension client;

    private MariadbWorkdayDAO() {
        this.client = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase().getConnection();
    }

    @Override
    public boolean insertWorkday(WorkdayModel Workday) {
        return false;
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
         WorkdayModel workday = client.selectObjectSingle(Workday, "SELECT * FROM report WHERE id = ?", employeeId);
        return workday;
    }
}
