package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.CustomerModel;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.ProjectModel;

import java.util.List;

public interface IProjectDAO {
    /**
     * insert new project
     * @param project
     * @return boolean - done or faulted
     */
    public int insertProject(ProjectModel project);

    /**
     * delete a existing project
     * @param project
     * @return int
     */
    public int deleteProject(ProjectModel project);

    /**
     * find a existing project
     * @param id
     * @return ProjectModel
     */
    public ProjectModel findProject(String id);

    /**
     * update an existing project
     * @param project
     * @return int
     */
    public int updateProject(ProjectModel project);

    /**
     * Get all projects
     * @return List<ProjectModel>
     */
    public List<ProjectModel> selectAllProjects();

    /**
     * get all projects by customer
     * @param customer
     * @return
     */
    public ProjectModel selectProjectByCustomer(CustomerModel customer);

    /**
     * get all projects for a specific employee
     * @param employee
     * @return List<ProjectModel>
     */
    public List<ProjectModel> selectProjectsByEmployee(EmployeeModel employee);

    /**
     * delete item for a specific employee
     * @param employee
     * @param project
     * @return List<ProjectModel>
     */
    public int DeleteJunctionItemByEmployee(EmployeeModel employee, ProjectModel project);

    /**
     * add items for a specific project
     * @param employee
     * @param project
     * @return List<ProjectModel>
     */
    public int addJunctionItemWithProject(EmployeeModel employee, ProjectModel project);
}
