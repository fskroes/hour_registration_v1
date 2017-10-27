package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.ILogDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.LogModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

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
    public boolean insertLog(LogModel log) {
        try {
            String query = "INSERT INTO log"
                    + "(date, description) VALUES"
                    + "(?,?)";

            PreparedStatement ps = database.openConnection().prepareStatement(query);
            ps.setDate(1, (Date) log.getDate());
            ps.setString(2, log.getDescription());
            ps.executeQuery();
            ps.close();
            database.closeConnecion();
            System.out.println("Query: " + query + " = Succes");

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteLog(String id) {
        try {
            String sql = "DELETE log"
                    + " WHERE logID = ?";

            PreparedStatement ps = database.openConnection().prepareStatement(sql);
            ps.setString(1, id);


            ps.executeUpdate();
            ps.close();
            database.closeConnecion();

            System.out.println("Record toegevoegd");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public LogModel findLog(String id) {

        LogModel log = null;
        try {
            log = database.selectObjectSingle(new LogModel(), "SELECT * FROM log WHERE logID = ?", log.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return log;
    }

    @Override
    public boolean updateLog(LogModel log) {
        Connection dbConnection = null;
        PreparedStatement ps = null;

        String updateSQL = "UPDATE log"
                + " SET date = ?, description = ?"
                + " WHERE logID = ?";

        try {
            dbConnection = database.getConnection();
            ps = database.getConnection().prepareStatement(updateSQL);

            ps.setDate(1, (Date)log.getDate());
            ps.setString(2, log.getDescription());

            ps.executeUpdate();

            System.out.println("Record geupdate");
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
    public Collection<LogModel> selectLogByEmployee(EmployeeModel employee) {

    List<LogModel> log = null;

        try {
            log = database.selectObjectList(new LogModel(), "SELECT * FROM contract WHERE employeeID = ?", employee.getId());
        }
        catch (SQLException e) {
        e.printStackTrace();
    }
        return log;
    }
}
