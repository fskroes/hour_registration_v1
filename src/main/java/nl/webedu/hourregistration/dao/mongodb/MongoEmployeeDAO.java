package nl.webedu.hourregistration.dao.mongodb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoCollection;
import nl.webedu.hourregistration.dao.IEmployeeDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.ContractModel;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.ProjectModel;
import org.bson.Document;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.mongodb.client.model.Filters.eq;
import static nl.webedu.hourregistration.database.DatabaseUtil.DATABASE_NAME;
import static nl.webedu.hourregistration.database.DatabaseUtil.EMPLOYEE_COLLECTION;

public class MongoEmployeeDAO implements IEmployeeDAO {

    private MongoClient client;
    private static MongoEmployeeDAO instance;
    private ObjectMapper objectMapper = new ObjectMapper();
    private EmployeeModel model;

    public MongoEmployeeDAO() {
        this.client = (MongoClient) DatabaseManager.getInstance().getDatabase().getConnection();
    }

    public static MongoEmployeeDAO getInstance() {
        if (instance == null)
            instance = new MongoEmployeeDAO();
        return instance;
    }

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

        model = new EmployeeModel();

        MongoCollection<Document> collection = client.getDatabase(DATABASE_NAME).getCollection(EMPLOYEE_COLLECTION);
        CompletableFuture<EmployeeModel> result = new CompletableFuture<>();
        collection
                .find(eq("email", id))
                .first((employeeModelsJson, Throwable) -> { // onResults
                    System.out.println(employeeModelsJson);
                    model.convertMongo(employeeModelsJson);
                    System.out.println(model.getId());
                    System.out.println(model.getEmail());
                    result.complete(model);
                });

        try {
            model = result.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return model;
    }

    @Override
    public boolean updateEmployee(EmployeeModel employee) {
        findEmployee(employee.getEmail().toString());

        MongoCollection<Document> collection = client.getDatabase(DATABASE_NAME).getCollection(EMPLOYEE_COLLECTION);
        CompletableFuture<Boolean> result = new CompletableFuture<>();

        return false; // todo working on this, feri
    }

    @Override
    public List<EmployeeModel> selectAllEmployees() {
        return null;
    }

    @Override
    public List<EmployeeModel> selectEmployeesByProject(ProjectModel project) {
        return null;
    }

    @Override
    public ContractModel findContractByEmployee(EmployeeModel employee) {
        return null;
    }
}
