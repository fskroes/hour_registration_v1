package nl.webedu.hourregistration.dao.mongodb;

import com.mongodb.async.client.MongoClient;
import nl.webedu.hourregistration.dao.IWorkdayDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.ActivitiesModel;
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
    public boolean insertWorkday(WorkdayModel workday) {
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();
        ArrayList<Document> activitiesDoc = new ArrayList<>();
        ArrayList<Document> employeesDoc = new ArrayList<>();

        for (ActivitiesModel model : workday.getActivities()) {
            activitiesDoc.add(new Document("category", model.getCategory())
                    .append("start_time", model.getStartTime())
                    .append("end_time",model.getEndTime())
                    .append("workday_id",model.getWorkdayId()));
        }

        for (EmployeeModel model: workday.getEmployeeModels()){
            employeesDoc.add(new Document("email", model.getEmail())
                    .append("firstname", model.getFirstname())
                    .append("suffix",model.getSuffix())
                    .append("lastname",model.getLastname()));
        }

        Document query = new Document("date", workday.getDate())
                .append("start_time",workday.getStartTime())
                .append("end_time",workday.getEndTime())
                .append("week_number", workday.getWeekNumber())
                .append("activities",activitiesDoc)
                .append("employees", employeesDoc);

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
