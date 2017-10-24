package nl.webedu.hourregistration.dao.mongodb;

import com.mongodb.async.client.MongoClient;
import nl.webedu.hourregistration.dao.IEmployeeDAO;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.ProjectModel;

import java.util.List;

public class MongoEmployeeDAO implements IEmployeeDAO {

    private MongoClient client;
    private static MongoEmployeeDAO instance;

    @Override
    public boolean insertEmployee(EmployeeModel employee) {
    return true;
    }

    @Override
    public boolean deleteEmployee(String id) {
        return false;
    }

    @Override
    public EmployeeModel findEmployee(String id) {
        return null;
    }

    @Override
    public boolean updateEmployee(EmployeeModel employee) {
        return false;
    }

    @Override
    public List<EmployeeModel> selectEmployeesByProject(ProjectModel project) {
        return null;
    }
}
