package nl.webedu.hourregistration.dao.mongodb;

import com.mongodb.Block;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoCollection;
import nl.webedu.hourregistration.dao.IWorkdayDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.ActivitiesModel;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.WorkdayModel;
import org.bson.Document;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static nl.webedu.hourregistration.database.DatabaseUtil.EMPLOYEE_COLLECTION;
import static nl.webedu.hourregistration.database.DatabaseUtil.WORKDAY_COLLECTION;
import static nl.webedu.hourregistration.database.DatabaseUtil.DATABASE_NAME;

public class MongoWorkdayDAO implements IWorkdayDAO {

    private MongoClient client;
    private static MongoWorkdayDAO instance;
    ArrayList<Document> alworkdayDocuments = new ArrayList<>();

    private MongoWorkdayDAO() {
        this.client = (MongoClient) DatabaseManager.getInstance().getDatabase().getConnection();
    }

    public static MongoWorkdayDAO getInstance() {
        if (instance == null)
            instance = new MongoWorkdayDAO();
        return instance;
    }

    @Override
    public boolean insertWorkday(WorkdayModel workday) {
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();

        Document query = new Document("date", workday.getDate())
                .append("start_time",workday.getStartTime())
                .append("end_time",workday.getEndTime())
                .append("week_number", workday.getWeekNumber())
                .append("activities",getLists(workday).get("activitiesDoc"))
                .append("employees", getLists(workday).get("employeesDoc"));

        client.getDatabase(DATABASE_NAME).getCollection(WORKDAY_COLLECTION)
                .insertOne(query, (result, t) -> completableFuture.complete(true));
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int deleteWorkday(WorkdayModel workday) {
        return 0;
    }

    public boolean deleteWorkday(String id) {
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();
        Document query = new Document();
        query.put("_id", id);

        client.getDatabase(DATABASE_NAME).getCollection(WORKDAY_COLLECTION)
                .deleteOne(query, (deleteResult, throwable) -> completableFuture.complete(true));
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public WorkdayModel findWorkday(String id) {

        MongoCollection<Document> collection = client.getDatabase(DATABASE_NAME).getCollection(WORKDAY_COLLECTION);
        CompletableFuture<WorkdayModel> result = new CompletableFuture<>();
        collection
                .find(eq("_id", id))
                .first((document, Throwable) -> { // onResults
                    System.out.println(document.toJson());
                    result.complete(new WorkdayModel().convertMongo(Optional.of(document)));
                });
        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateWorkday(WorkdayModel workday) {

        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        Document query = new Document();
        query.put("workday_id", workday.getId());
        client.getDatabase(DATABASE_NAME).getCollection(WORKDAY_COLLECTION).updateOne(
                eq("date", workday.getDate()),
                combine(set("start_time", workday.getStartTime()),
                        set("end_time", workday.getEndTime()),
                        set("week_number", workday.getWeekNumber()),
                        set("activities", getLists(workday).get("activitiesDoc")),
                        set("employees",getLists(workday).get("employeesDoc"))),
                (updateResult, throwable) -> {
                    completableFuture.complete((int) updateResult.getModifiedCount());
                });
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<WorkdayModel> selectAllWorkdays() {

        CompletableFuture<List<WorkdayModel>> result = new CompletableFuture<>();
        ArrayList<WorkdayModel> alWorkdays = new ArrayList<>();
        MongoCollection<Document> collection = client.getDatabase(DATABASE_NAME).getCollection(WORKDAY_COLLECTION);

        collection
                .find()
                .into(alworkdayDocuments,(documents, throwable) -> {
                    for (Document d: alworkdayDocuments) {
                        alWorkdays.add(new WorkdayModel().convertMongo(Optional.of(d)));
                    }
                    result.complete(alWorkdays);
                });
        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WorkdayModel> selectWorkdaysByEmployee(EmployeeModel employee) {
        CompletableFuture<List<WorkdayModel>> result = new CompletableFuture<>();
        ArrayList<WorkdayModel> alWorkdays = new ArrayList<>();
        MongoCollection<Document> collection = client.getDatabase(DATABASE_NAME).getCollection(EMPLOYEE_COLLECTION);

        collection
                .find(eq("_id ",employee.get_id()))
                .first((document, Throwable) -> { // onResults
                   employee.convertMongo(Optional.of(document));

                   MongoCollection<Document> collection2 = client.getDatabase(DATABASE_NAME).getCollection(WORKDAY_COLLECTION);
                   for (String s:employee.getWorkday_Ids() ){
                       collection2
                               .find(eq("_id", s))
                               .into(alworkdayDocuments,(documents, throwable) -> {
                                   for (Document d: alworkdayDocuments) {
                                       alWorkdays.add(new WorkdayModel().convertMongo(Optional.of(d)));
                                   }
                               });
                   }
                    result.complete(alWorkdays);
                });

        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Map<String,List<Document>> getLists(WorkdayModel workday) {

        List<Document> activitiesDoc = new ArrayList<Document>();
        List<Document> employeesDoc = new ArrayList<Document>();

        for (ActivitiesModel model : workday.getActivities()) {
            activitiesDoc.add(new Document("category", model.getCategory())
                    .append("start_time", model.getStartTime())
                    .append("end_time",model.getEndTime())
                    .append("workday_id",model.getWorkdayId()));
        }

//        for (EmployeeModel model: workday.getEmployeeModels()){
//            employeesDoc.add(new Document("email", model.getEmail())
//                    .append("firstname", model.getFirstname())
//                    .append("suffix",model.getSuffix())
//                    .append("lastname",model.getLastname()));
//        }

        Map<String, List<Document>> map = new HashMap<>();
        map.put("activitiesDoc", activitiesDoc);
        map.put("employeesDoc", employeesDoc);
        return map;
    }
}

