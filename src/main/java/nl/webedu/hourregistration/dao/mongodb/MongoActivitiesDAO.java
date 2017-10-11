package nl.webedu.hourregistration.dao.mongodb;

import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.MongoClient;
import nl.webedu.hourregistration.dao.IActivitiesDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.ActivitiesModel;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static nl.webedu.hourregistration.database.DatabaseUtil.DATABASE_NAME;
import static nl.webedu.hourregistration.database.DatabaseUtil.ACTIVITY_COLLECTION;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
        Document query = new Document("category", activitie.getCategory())
                .append("start_time",activitie.getStartTime())
                .append("end_time",activitie.getEndTime())
                .append("workday_id", activitie.getWorkdayId());

        client.getDatabase(DATABASE_NAME).getCollection(ACTIVITY_COLLECTION)
                .insertOne(query, (result, t) -> System.out.println("Documents inserted!"));
        return true;
    }

    @Override
    public ActivitiesModel findActivitie(int id) {
        return null;
    }

    @Override
    public boolean deleteActivitie(int id) {
        return false;
    }

    @Override
    public boolean updateActivitie(ActivitiesModel activitie) {
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
                        System.out.println("HIER2!!!!!!!!!!");
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
