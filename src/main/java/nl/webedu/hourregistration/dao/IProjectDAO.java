package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.CustomerModel;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.ProjectModel;

import java.util.List;

public interface IProjectDAO {
    public boolean insertProject(ProjectModel project);
    public int deleteProject(ProjectModel project);
    public ProjectModel findProject(String id);
    public int updateProject(ProjectModel project);
    public List<ProjectModel> selectAllProjects();
    public ProjectModel selectProjectByCustomer(CustomerModel customer);
    public List<ProjectModel> selectProjectsByEmployee(EmployeeModel employee);
    public int DeleteJunctionItemByEmployee(EmployeeModel employee, ProjectModel project);
    public int addJunctionItemWithProject(EmployeeModel employee, ProjectModel project);
}
