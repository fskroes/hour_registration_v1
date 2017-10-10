package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IProjectDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ProjectModel;
import nl.webedu.hourregistration.model.ReportModel;

import java.util.Collection;

public class MariadbProjectDAO implements IProjectDAO {

    private MariaDatabaseExtension database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();

    @Override
    public boolean insertProject(ProjectModel Project) {
        return false;
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
    public boolean updateProject(ProjectModel Project) {
        return false;
    };

    @Override
    public ProjectModel selectProjectByCustomer(int customerId) {
        ProjectModel project = database.selectObjectSingle(Project, "SELECT * FROM project WHERE name = ?", "");
        return project;
    };
}
