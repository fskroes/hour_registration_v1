package nl.webedu.hourregistration.dao.mongodb;

import com.mongodb.async.client.MongoClient;
import nl.webedu.hourregistration.dao.IEmployeeDAO;
import nl.webedu.hourregistration.model.EmployeeModel;
import org.bson.Document;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static nl.webedu.hourregistration.database.DatabaseUtil.ACTIVITY_COLLECTION;
import static nl.webedu.hourregistration.database.DatabaseUtil.DATABASE_NAME;

public class MongoEmployeeDAO implements IEmployeeDAO {

    private MongoClient client;
    private static MongoEmployeeDAO instance;

    @Override
    public boolean insertEmployee(EmployeeModel employee) {
    return true;
    }

    @Override
    public boolean deleteEmployee(int id) {
        return false;
    }

    @Override
    public EmployeeModel findEmployee(int id) {
        return null;
    }

    @Override
    public boolean updateEmployee(EmployeeModel employee) {
        return false;
    }

    @Override
    public Collection selectEmployeesByProject(int projectId) {
        return null;
    }
}
