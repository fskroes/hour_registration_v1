package nl.webedu.hourregistration.dao.mongodb;

import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoCollection;
import nl.webedu.hourregistration.dao.IWorkdayDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.WorkdayModel;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static nl.webedu.hourregistration.database.DatabaseUtil.DATABASE_NAME;
import static nl.webedu.hourregistration.database.DatabaseUtil.WORKDAY_COLLECTION;

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
                .append("activities",workday.getActivities())
                .append("employees", workday.getEmployeeId());

        client.getDatabase(DATABASE_NAME).getCollection(WORKDAY_COLLECTION)
                .insertOne(query, (result, t) -> completableFuture.complete(true));
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public int deleteWorkday(WorkdayModel workday) {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        Document query = new Document();
        query.put("_id", workday.getId());

        client.getDatabase(DATABASE_NAME).getCollection(WORKDAY_COLLECTION).deleteOne(
                query,
                (deleteResult, throwable) -> completableFuture.complete((int) deleteResult.getDeletedCount()));
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return 0;
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
                    result.complete(new WorkdayModel().convertMongo(document));
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
                eq("date", workday.getDate().toString()),
                combine(set("start_time", workday.getStartTime().toString()),
                        set("end_time", workday.getEndTime().toString()),
                        set("week_number", workday.getWeekNumber()),
                        set("activities", workday.getActivities()),
                        set("employees",workday.getEmployeeId())),
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
                        alWorkdays.add(new WorkdayModel().convertMongo(d));
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
        MongoCollection<Document> collection = client.getDatabase(DATABASE_NAME).getCollection(WORKDAY_COLLECTION);
        System.out.println("!!!!!!! "+employee.getId());
        collection
                .find(eq("employee",employee.getId()))
                .forEach((document -> {
                    System.out.println(document.toJson());
                    alWorkdays.add(new WorkdayModel().convertMongo(document));
                }), (aVoid, throwable) -> {
            result.complete(alWorkdays);
        });
        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}

