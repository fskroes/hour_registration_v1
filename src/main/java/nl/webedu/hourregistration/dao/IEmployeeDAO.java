package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.EmployeeModel;
import java.util.Collection;

public interface IEmployeeDAO {
    public boolean insertEmployee(EmployeeModel employee);
    public boolean deleteEmployee(String id);
    public EmployeeModel findEmployee(String id);
    public boolean updateEmployee(EmployeeModel employee);
    public Collection selectEmployeesByProject(int projectId);
}
