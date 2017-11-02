package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.ContractModel;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.ProjectModel;

import java.util.List;

public interface IEmployeeDAO {
    /**
     * Insert an employee in the database
     * @param employee
     * @return boolean - done or faulted
     */
    public boolean insertEmployee(EmployeeModel employee);

    /**
     * Delete a specific employee by giving the employee id
     * @param id
     * @return boolean - done or faulted
     */
    public boolean deleteEmployee(String id);

    /**
     * Find employee in the database
     * @param id
     * @return EmployeeModel
     */
    public EmployeeModel findEmployee(String id);

    /**
     * Update a existing employee in the database
     * @param employee
     * @return boolean - done or faulted
     */
    public boolean updateEmployee(EmployeeModel employee);

    /**
     * Get all the employee that are in the database
     * @return List<EmployeeModel>
     */
    public List<EmployeeModel> getAllEmployees();

    /**
     * Get the employees for a specific project
     * @param project
     * @return List<EmployeeModel>
     */
    public List<EmployeeModel> selectEmployeesByProject(ProjectModel project);

    /**
     * Find a contract for giving a employee
     * @param employee
     * @return ContractModel
     */
    public ContractModel findContractByEmployee(EmployeeModel employee);
}
