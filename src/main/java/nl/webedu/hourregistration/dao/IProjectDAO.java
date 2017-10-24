package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.ProjectModel;

import java.util.Collection;

public interface IProjectDAO {
    public boolean insertProject(ProjectModel Project);
    public boolean deleteProject(String id);
    public ProjectModel findProject(String id);
    public boolean updateProject(ProjectModel Project);
}
