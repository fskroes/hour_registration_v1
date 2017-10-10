package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IProjectDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ProjectModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MariadbProjectDAO implements IProjectDAO {

    private MariaDatabaseExtension client;

    private MariadbProjectDAO() {
        this.client = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase().getConnection();
    }

    @Override
    public boolean insertProject(ProjectModel project) {

        Connection dbConnection = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO project"
                + "(NAME, STARTDATE, ENDDATE, CUSTOMERMODEL, CATEORIE) VALUES"
                + "(?,?,?,?,?)";

        try {
            dbConnection = client.getConnection();
            ps = client.getConnection().prepareStatement(sql);

            ps.setString(1, project.getName());
            ps.setDate(2, project.getStartDate());
            ps.setDate(3, project.getEndDate());
            ps.setObject(4, project.getCustomerModel());
            ps.setString(5, project.getCategorie());

            ps.executeUpdate();

            System.out.println("Record toegevoegd");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
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
    };

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
        ProjectModel project = client.selectObjectSingle(Project, "SELECT * FROM project WHERE id = ?", customerId);
        return project;
    };
}
