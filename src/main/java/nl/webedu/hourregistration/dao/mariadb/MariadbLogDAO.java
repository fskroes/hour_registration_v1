package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.ILogDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.LogModel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class MariadbLogDAO implements ILogDAO {

    private static MariadbLogDAO instance;
    private MariaDatabaseExtension client = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();

    private MariadbLogDAO() {
        this.client = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();
    }

    public static MariadbLogDAO getInstance() {
        if (instance == null) {
            instance = new MariadbLogDAO();
        }
        return instance;
    }

    @Override
    public boolean insertLog(LogModel log) {
        try {
            String query = "INSERT INTO log"
                    + "(date, description) VALUES"
                    + "(?,?)";

            PreparedStatement ps = client.openConnection().prepareStatement(query);
            ps.setDate(1, (Date) log.getDate());
            ps.setString(2, log.getDescription());
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
    public boolean deleteLog(int id) {

        return false;
    }

    @Override
    public LogModel findLog(int id) {

        return null;
    }

    @Override
    public boolean updateLog(LogModel log) {

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
