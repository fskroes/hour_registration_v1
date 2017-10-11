package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IProjectDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ProjectModel;

import java.sql.*;

public class MariadbProjectDAO implements IProjectDAO {

    private static MariadbProjectDAO instance;
    private MariaDatabaseExtension client;

    private MariadbProjectDAO() {
        this.client = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();
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

            PreparedStatement ps = client.openConnection().prepareStatement(query);
            ps.setString(1, project.getName());
            ps.setDate(2, (Date) project.getStartDate());
            ps.setDate(3, (Date) project.getEndDate());
            ps.setString(4, project.getCategorie());
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
    public boolean deleteProject(int id) {
        return false;
    };

    @Override
    public ProjectModel findProject(int id) {
        return null;
    };

    @Override
    public boolean updateProject(ProjectModel project) {
        return false;
    };

    @Override
    public ProjectModel selectProjectByCustomer(int customerId) {
        ProjectModel project = null;
        try {
            project = client.selectObjectSingle(new ProjectModel(), "SELECT * FROM project WHERE id = ?", customerId + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    };
}
