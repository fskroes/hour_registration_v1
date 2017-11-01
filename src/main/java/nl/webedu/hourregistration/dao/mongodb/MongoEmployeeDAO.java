package nl.webedu.hourregistration.dao.mongodb;

import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoCollection;
import nl.webedu.hourregistration.dao.IEmployeeDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.ContractModel;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.ProjectModel;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.mongodb.client.model.Filters.eq;
import static nl.webedu.hourregistration.database.DatabaseUtil.*;

public class MongoEmployeeDAO implements IEmployeeDAO {

    private MongoClient client;
    private static MongoEmployeeDAO instance;

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

        final EmployeeModel model = new EmployeeModel();

        MongoCollection<Document> collection = client.getDatabase(DATABASE_NAME).getCollection(EMPLOYEE_COLLECTION);
        CompletableFuture<EmployeeModel> result = new CompletableFuture<>();
        collection.find(eq("email", id)).first((employeeDoc, Throwable) -> { // onResults
                    System.out.println(employeeDoc);
                    model.convertMongo(employeeDoc);
                    result.complete(model);
                });
        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateEmployee(EmployeeModel employee) {
        findEmployee(employee.getEmail().toString());

        MongoCollection<Document> collection = client.getDatabase(DATABASE_NAME).getCollection(EMPLOYEE_COLLECTION);
        CompletableFuture<Boolean> result = new CompletableFuture<>();

        return false; // todo working on this, feri
    }

    @Override
    public List<EmployeeModel> getAllEmployees() {
        CompletableFuture<List<EmployeeModel>> result = new CompletableFuture<>();
        List<Document> foundDocuments = new ArrayList<>();
        List<EmployeeModel> employees = new ArrayList<>();
        MongoCollection<Document> collection = client.getDatabase(DATABASE_NAME).getCollection(CUSTOMER_COLLECTION);

        collection.find()
                .into(foundDocuments, (documents, throwable) -> {
                    for (Document doc : foundDocuments) {
                        EmployeeModel employee = new EmployeeModel().convertMongo(doc);
                        List<ObjectId> projects = (List<ObjectId>) doc.get("projects");
                        for (ObjectId projectId : projects) {
                            employee.addProject(DatabaseManager.getInstance().getDaoFactory().getProjectDAO().findProject(projectId.toString()));
                        }
                        List<ObjectId> workdays = (List<ObjectId>) doc.get("workdays");
                        for (ObjectId workdayId : workdays) {
                            employee.addWorkday(DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().findWorkday(workdayId.toString()));
                        }
                        employees.add(new EmployeeModel().convertMongo(doc));
                    }
                    result.complete(employees);
                });
        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
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
