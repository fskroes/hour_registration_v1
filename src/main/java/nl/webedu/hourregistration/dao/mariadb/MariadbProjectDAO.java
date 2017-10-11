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
        PreparedStatement ps;
        ResultSet rs;

        try {
            String sql = "INSERT INTO project"
                    + "(project_name, start_date, end_time, category) VALUES"
                    + "(?,?,?,?)";

            ps = client.openConnection().prepareStatement(sql);

            ps.setString(1, "PROJECTNAAMPJE");
            ps.setDate(2, new  java.sql.Date(new java.util.Date().getTime()));
            ps.setDate(3, new  java.sql.Date(new java.util.Date().getTime()));
            ps.setString(4, "Category: test");

            rs = ps.executeQuery();

            ps.close();
            client.closeConnecion();

            System.out.println("De query geeft het volgende resultaat:\n");
            System.out.println(rs);

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
