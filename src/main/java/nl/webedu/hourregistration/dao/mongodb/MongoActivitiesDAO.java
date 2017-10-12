package nl.webedu.hourregistration.dao.mongodb;

import com.mongodb.async.client.MongoClient;
import nl.webedu.hourregistration.dao.IActivitiesDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.ActivitiesModel;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static nl.webedu.hourregistration.database.DatabaseUtil.DATABASE_NAME;
import static nl.webedu.hourregistration.database.DatabaseUtil.ACTIVITY_COLLECTION;

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
    public boolean insertActivitie(ActivitiesModel activitie) {
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();
        Document query = new Document("category", activitie.getCategory())
                .append("start_time",activitie.getStartTime())
                .append("end_time",activitie.getEndTime())
                .append("workday_id", activitie.getWorkdayId());

        client.getDatabase(DATABASE_NAME).getCollection(ACTIVITY_COLLECTION)
                .insertOne(query, (result, t) -> completableFuture.complete(true));
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    //needs to be tested!!
    @Override
    public ActivitiesModel findActivitie(int id) {
        CompletableFuture<ActivitiesModel> completableFuture = new CompletableFuture<>();
        ActivitiesModel ac = new ActivitiesModel();
        client.getDatabase(DATABASE_NAME).getCollection(ACTIVITY_COLLECTION).find(
                eq("_id", id)).first((document, throwable) -> {
                    completableFuture.complete(ac.convertMongo(document, 0));
                });
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteActivitie(ActivitiesModel activitie) {
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();
        Document query = new Document();
        query.put("_id", activitie.getActivityId());

        client.getDatabase(DATABASE_NAME).getCollection(ACTIVITY_COLLECTION)
                .deleteOne(query, (deleteResult, throwable) -> completableFuture.complete(true));
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateActivitie(ActivitiesModel activitie) {
        Document query = new Document();
        query.put("workday_id", activitie.getActivityId());
        client.getDatabase(DATABASE_NAME).getCollection(ACTIVITY_COLLECTION).updateOne(eq("workday_id", activitie.getWorkdayId())
                , combine(set("category", activitie.getCategory()),
                        set("start_time", activitie.getStartTime()),
                        set("end_time", activitie.getEndTime()),
                        set("workday_id", activitie.getWorkdayId())), (updateResult, throwable) -> {
                });
        return false;
    }

    @Override
    public Collection selectActivitiesByWorkday(int workdayId) {
        CompletableFuture<List<ActivitiesModel>> completableFuture = new CompletableFuture<>();
        ArrayList<ActivitiesModel> alActivitiesmodels = new ArrayList<>();
        client.getDatabase(DATABASE_NAME).getCollection(ACTIVITY_COLLECTION).find(
                eq("workday_id", workdayId)).into(
                alActivitieDocuments,
                (documents, throwable) -> {
                    for (Document d: alActivitieDocuments) {

                        alActivitiesmodels.add(new ActivitiesModel(d.getString("category"),
                                d.getDate("start_time"),
                                d.getDate("end_time"),
                                d.getInteger("workday_id")));
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
}