package nl.webedu.hourregistration.dao.mongodb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoCollection;
import nl.webedu.hourregistration.dao.IEmployeeDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.EmployeeModel;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.mongodb.client.model.Filters.eq;
import static nl.webedu.hourregistration.database.DatabaseUtil.*;

public class MongoEmployeeDAO implements IEmployeeDAO {

    private MongoClient client;
    private static MongoEmployeeDAO instance;
    private ObjectMapper objectMapper = new ObjectMapper();
    private EmployeeModel model;
    private List<String> projectsByEmployeeList;

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

        Boolean b = false;
        employee = findEmployee(employee.getEmail());

        if (employee != null) {
            System.out.println("employee not found, nothing happened \t" + "'¯\\(°_o)/¯'");
            return false;
        }

        MongoCollection<Document> collection = client.getDatabase(DATABASE_NAME).getCollection(EMPLOYEE_COLLECTION);
        CompletableFuture<Boolean> result = new CompletableFuture<>();

        String json = null;
        try {
            json = objectMapper.writeValueAsString(employee);
            System.out.println("employee toJson " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }

        Document d = Document.parse(json);
        collection
                .insertOne(d, (aVoid, throwable) ->  {
                    result.complete(true);
                    System.out.println("Employee inserted! Do a dance now \t (•_•) ( •_•)>⌐■-■ (⌐■_■)");
                });


        try {
            b = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return b;
    }

    @Override
    public boolean deleteEmployee(String email) {
        Boolean br = false;

        MongoCollection<Document> collection = client.getDatabase(DATABASE_NAME).getCollection(EMPLOYEE_COLLECTION);
        CompletableFuture<Boolean> result = new CompletableFuture<>();
        collection
                .findOneAndDelete(eq("email", email),
                        ((document, throwable) -> {
                            System.out.println("employee deleted: " + document.toJson());
                            result.complete(true);
                }));

        try {
            br = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return br;
    }

    @Override
    public EmployeeModel findEmployee(String email) {

        model = new EmployeeModel();

        MongoCollection<Document> collection = client.getDatabase(DATABASE_NAME).getCollection(EMPLOYEE_COLLECTION);
        CompletableFuture<EmployeeModel> result = new CompletableFuture<>();
        collection
                .find(eq("email", email))
                .first((employeeModelsJson, Throwable) -> { // onResults
                    System.out.println(employeeModelsJson);

                    model.convertMongo(employeeModelsJson);

                    System.out.println(model.get_id());
                    System.out.println(model.getEmail());

                    result.complete(model);
                });

        try {
            model = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return model;
    }

    @Override
    public boolean updateEmployee(EmployeeModel employee) {
        if(findEmployee(employee.getEmail()) != null) return false;

        return insertEmployee(employee);
    }

    @Override
    public List<String> selectProjectsByEmployee(String employeeEmail) {

        model = new EmployeeModel();
        projectsByEmployeeList = new ArrayList<>();

        MongoCollection<Document> collectionProjects = client.getDatabase(DATABASE_NAME).getCollection(PROJECT_COLLECTION);
        CompletableFuture<List<String>> result = new CompletableFuture<>();

        collectionProjects
                .find(eq("employees", employeeEmail))
                .map(Document::toString)
                .forEach((document -> {
                    System.out.println("foreach, document: " + document + "╭∩╮（︶︿︶）╭∩╮");
                    projectsByEmployeeList.add(document);
                }), (aVoid, throwable) -> {
                    result.complete(projectsByEmployeeList);
                });

        try {
            projectsByEmployeeList = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return projectsByEmployeeList;
    }

    @Override
    public List<String> selectEmployeeByProject(String projectName) {
        return null;
    }
}
