package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IProjectDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.CustomerModel;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.ProjectModel;

import java.sql.SQLException;
import java.util.List;

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
        String querySQL = "INSERT INTO project"
                + "(project_name, start_date, end_time, category) VALUES"
                + "(?,?,?,?)";
        try {
            database.insertQuery(querySQL, project.getName(), project.getStartDate(), project.getEndDate(), project.getCategorie());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int deleteProject(ProjectModel project) {
        int result = 0;
        String querySQL = "DELETE project"
                + " WHERE projectID = ?";
        try {
            result = database.deleteQuery(querySQL, project);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

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
    public int updateProject(ProjectModel project) {
        int result = 0;
        String updateSQL = "UPDATE project"
                + " SET project_name = ?, start_date = ?, end_time = ?, category = ?"
                + " WHERE projectID = ?";
        try {
            database.updateQuery(
                    updateSQL,
                    project.getName(),
                    project.getStartDate(),
                    project.getEndDate(),
                    project.getCategorie(),
                    project.getId()
                    );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<ProjectModel> selectAllProjects() {
        List<ProjectModel> project = null;
        try {
            project = database.selectObjectList(new ProjectModel(), "SELECT * FROM project");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public ProjectModel selectProjectByCustomer(CustomerModel customer) {
        ProjectModel project = null;
        try {
            project = database.selectObjectSingle(new ProjectModel(), "SELECT * FROM project WHERE customerID = ?", customer.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public List<ProjectModel> selectProjectsByEmployee(EmployeeModel employee) {
        List<ProjectModel> project = null;
        try {
            project = database.selectObjectList(new ProjectModel(), "SELECT * FROM project WHERE employeeID = ?", employee.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }
}
