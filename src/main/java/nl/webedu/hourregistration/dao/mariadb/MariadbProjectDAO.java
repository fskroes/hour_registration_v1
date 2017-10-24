package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IProjectDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ProjectModel;

import java.sql.*;

public class MariadbProjectDAO implements IProjectDAO {

    private static MariadbProjectDAO instance;
    private MariaDatabaseExtension database;

    private MariadbProjectDAO() {
        this.database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();
    }

    public static MariadbProjectDAO getInstance() {
        if (instance == null) {
            instance = new MariadbProjectDAO();
        }
        return instance;
    }

    @Override
    public boolean insertProject(ProjectModel project) {
        try {
            String query = "INSERT INTO project"
                    + "(project_name, start_date, end_time, category) VALUES"
                    + "(?,?,?,?)";

            PreparedStatement ps = database.openConnection().prepareStatement(query);
            ps.setString(1, project.getName());
            ps.setDate(2, (Date) project.getStartDate());
            ps.setDate(3, (Date) project.getEndDate());
            ps.setString(4, project.getCategorie());
            ps.executeQuery();
            ps.close();
            database.closeConnecion();
            System.out.println("Query: " + query + " = Success");

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteProject(String id) {
        try {
            String sql = "DELETE project"
                    + " WHERE projectID = ?";

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
    };

    @Override
    public ProjectModel findProject(String id) {

        ProjectModel project = null;
        try {
            project = database.selectObjectSingle(new ProjectModel(), "SELECT * FROM project WHERE projectID = ?", id + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public boolean updateProject(ProjectModel project) {
        Connection dbConnection = null;
        PreparedStatement ps = null;

        String updateSQL = "UPDATE project"
                + " SET project_name = ?, start_date = ?, end_time = ?, category = ?"
                + " WHERE projectID = ?";

        try {
            dbConnection = database.getConnection();
            ps = database.getConnection().prepareStatement(updateSQL);

            ps.setString(1, project.getName());
            ps.setDate(2, (Date) project.getStartDate());
            ps.setDate(3, (Date) project.getEndDate());
            ps.setString(4, project.getCategorie());

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
                }
                    catch (SQLException e) {
                     e.printStackTrace();
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                    }
                    catch (SQLException e) {
                     e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public ProjectModel selectProjectByCustomer(String customerId) {
        ProjectModel project = null;
        try {
            project = database.selectObjectSingle(new ProjectModel(), "SELECT * FROM project WHERE  = ?", customerId + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }
}
