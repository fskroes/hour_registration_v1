package nl.webedu.hourregistration.dao.mongodb;

import com.mongodb.async.client.MongoClient;
import nl.webedu.hourregistration.dao.IActivitiesDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.ActivitiesModel;
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
import static nl.webedu.hourregistration.database.DatabaseUtil.ACTIVITY_COLLECTION;
import static nl.webedu.hourregistration.database.DatabaseUtil.DATABASE_NAME;

public class MongoActivitiesDAO implements IActivitiesDAO {

    private MongoClient client;
    private static MongoActivitiesDAO instance;
    ArrayList<Document> alActivitieDocuments = new ArrayList<>();

    private MongoActivitiesDAO() {
        this.client = (MongoClient) DatabaseManager.getInstance().getDatabase().getConnection();
    }

    public static MongoActivitiesDAO getInstance() {
        if (instance == null)
            instance = new MongoActivitiesDAO();
        return instance;
    }

    @Override
    public int insertActivitie(ActivitiesModel activitie) {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        Document query = new Document("start_time",activitie.getStartTime().toString())
                .append("end_time",activitie.getEndTime().toString())
                .append("workday_id", activitie.getWorkday().getId());

        client.getDatabase(DATABASE_NAME).getCollection(ACTIVITY_COLLECTION)
                .insertOne(query, (result, t) -> completableFuture.complete(1));
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return 0;
        }
    }

    //needs to be tested!!
    @Override
    public ActivitiesModel findActivitie(String id) {
        CompletableFuture<ActivitiesModel> completableFuture = new CompletableFuture<>();
        ActivitiesModel ac = new ActivitiesModel();

        client.getDatabase(DATABASE_NAME).getCollection(ACTIVITY_COLLECTION).find(
                eq("_id", id)).first((document, throwable) -> {
                    completableFuture.complete(ac.convertMongo(document));
                });
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public int deleteActivitie(ActivitiesModel activity) {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        Document query = new Document();
        query.put("_id", activity.getId());

        client.getDatabase(DATABASE_NAME).getCollection(ACTIVITY_COLLECTION).deleteOne(
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
    public int updateActivitie(ActivitiesModel activity) {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        Document query = new Document();
        query.put("workday_id", activity.getId());
        client.getDatabase(DATABASE_NAME).getCollection(ACTIVITY_COLLECTION).updateOne(
                eq("workday_id", activity.getWorkday().getId()),
                combine(set("start_time", activity.getStartTime().toString()),
                        set("end_time", activity.getEndTime().toString()),
                        set("workday_id", activity.getWorkday().getId())),
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
    public List<ActivitiesModel> selectAllActivities() {
        return null;
    }

    @Override
    public List<ActivitiesModel> selectActivitiesByWorkday(WorkdayModel workday) {
        CompletableFuture<List<ActivitiesModel>> completableFuture = new CompletableFuture<>();
        ArrayList<ActivitiesModel> alActivitiesmodels = new ArrayList<>();
        client.getDatabase(DATABASE_NAME).getCollection(ACTIVITY_COLLECTION).find(
                eq("workday_id", workday.getId())).into(
                alActivitieDocuments,
                (documents, throwable) -> {
                    for (Document d: alActivitieDocuments) {

//                        alActivitiesmodels.add(new ActivitiesModel(d.getDate("start_time"),
//                                d.getDate("end_time"),
//                                d.getInteger("workday_id")));
                    }
                    completableFuture.complete(alActivitiesmodels);
                });
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ActivitiesModel> selectActivitiesByEmployee(EmployeeModel employee) {
        return null;
    }
}