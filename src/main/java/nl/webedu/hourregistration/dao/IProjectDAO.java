package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.ProjectModel;

import java.util.Collection;

public interface IProjectDAO {
    public boolean insertProject(ProjectModel Project);
    public boolean deleteProject(int id);
    public ProjectModel findProject(int id);
    public boolean updateProject(ProjectModel Project);
    public ProjectModel selectProjectByCustomer(int customerId);
}
