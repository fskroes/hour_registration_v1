package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IProjectDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ProjectModel;

public class MariadbProjectDAO implements IProjectDAO {

    private MariaDatabaseExtension client;

    private MariadbProjectDAO() {
        this.client = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase().getConnection();
    }

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
        ProjectModel project = client.selectObjectSingle(Project, "SELECT * FROM project WHERE name = ?", "");
        return project;
    };
}
