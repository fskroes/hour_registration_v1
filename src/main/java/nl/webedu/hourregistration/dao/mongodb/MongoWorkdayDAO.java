package nl.webedu.hourregistration.dao.mongodb;

import com.mongodb.async.client.MongoClient;
import nl.webedu.hourregistration.dao.IWorkdayDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.WorkdayModel;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static nl.webedu.hourregistration.database.DatabaseUtil.WORKDAY_COLLECTION;
import static nl.webedu.hourregistration.database.DatabaseUtil.DATABASE_NAME;

public class MongoWorkdayDAO implements IWorkdayDAO {

    private MongoClient client;
    private static MongoWorkdayDAO instance;
    ArrayList<Document> alActivitieDocuments = new ArrayList<>();

    private MongoWorkdayDAO() {
        this.client = (MongoClient) DatabaseManager.getInstance().getDatabase().getConnection();
    }

    public static MongoWorkdayDAO getInstance() {
        if (instance == null)
            instance = new MongoWorkdayDAO();
        return instance;
    }

    @Override
    public boolean insertWorkday(WorkdayModel Workday) {
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();
        Document query = new Document("date", Workday.getDate())
                .append("start_time",Workday.getStartTime())
                .append("end_time",Workday.getEndTime())
                .append("week_number", Workday.getWeekNumber())
                .append("activities",Workday.getActivities())
                .append("employees", Workday.getEmployeeModels());

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
        return null;
    }

    @Override
    public int updateWorkday(WorkdayModel Workday) {
        return 0;
    }

    @Override
    public List<WorkdayModel> selectAllWorkdays() {
        return null;
    }

    @Override
    public WorkdayModel selectWorkdayByEmployee(EmployeeModel employee) {
        return null;
    }
}
