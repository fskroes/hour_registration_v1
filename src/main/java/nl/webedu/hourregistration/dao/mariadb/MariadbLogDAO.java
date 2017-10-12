package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.ILogDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.LogModel;

import java.util.Collection;

public class MariadbLogDAO implements ILogDAO {

    private static MariadbLogDAO instance;
    private MariaDatabaseExtension database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();

    private MariadbLogDAO() {
        this.database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();
    }

    public static MariadbLogDAO getInstance() {
        if (instance == null) {
            instance = new MariadbLogDAO();
        }
        return instance;
    }

    @Override
    public boolean insertLog(LogModel Log) {

        return false;
    }

    @Override
    public boolean deleteLog(String id) {

        return false;
    }

    @Override
    public LogModel findLog(String id) {

        return null;
    }

    @Override
    public boolean updateLog(LogModel Log) {

        return false;
    }

    @Override
    public Collection selectLogByEmployee(int employeeId) {
        return null;
    }

    @Override
    public Collection selectLogBySubject(int subjectId) {
        return null;
    }
}
