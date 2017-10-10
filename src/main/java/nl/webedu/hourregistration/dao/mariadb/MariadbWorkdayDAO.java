package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IWorkdayDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.WorkdayModel;

import java.util.Collection;

public class MariadbWorkdayDAO implements IWorkdayDAO {

    private MariaDatabaseExtension database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();

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
    public Collection selectWorkdaysByEployee(int employeeId) {
        return null;
    }
}
