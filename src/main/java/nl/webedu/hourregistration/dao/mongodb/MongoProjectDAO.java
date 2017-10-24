package nl.webedu.hourregistration.dao.mongodb;

import com.mongodb.async.client.MongoClient;
import nl.webedu.hourregistration.dao.IProjectDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.ProjectModel;
import org.bson.Document;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static nl.webedu.hourregistration.database.DatabaseUtil.PROJECT_COLLECTION;
import static nl.webedu.hourregistration.database.DatabaseUtil.DATABASE_NAME;

public class MongoProjectDAO implements IProjectDAO {

    private MongoClient client;
    private static MongoProjectDAO instance;
    ArrayList<Document> alProjectDocuments = new ArrayList<>();

    private MongoProjectDAO(){
        this.client = (MongoClient) DatabaseManager.getInstance().getDatabase().getConnection();
    }
    public static  MongoProjectDAO getInstance(){
        if (instance == null)
            instance = new MongoProjectDAO();
        return instance;
    }
    @Override
    public boolean insertProject(ProjectModel Project) {
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();
        Document query = new Document("name", Project.getName())
                .append("start_date",Project.getStartDate())
                .append("end_date",Project.getEndDate())
                .append("category", Project.getCategorie());

        client.getDatabase(DATABASE_NAME).getCollection(PROJECT_COLLECTION)
                .insertOne(query, (result, t) -> completableFuture.complete(true));
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProject(String id) {
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();
        Document query = new Document();
        query.put("_id", id);

        client.getDatabase(DATABASE_NAME).getCollection(PROJECT_COLLECTION)
                .deleteOne(query, (deleteResult, throwable) -> completableFuture.complete(true));
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ProjectModel findProject(String id) {
        CompletableFuture<ProjectModel> completableFuture = new CompletableFuture<>();
        ProjectModel pm = new ProjectModel();

        client.getDatabase(DATABASE_NAME).getCollection(PROJECT_COLLECTION).find(
                eq("_id", id)).first((document, throwable) -> {
                    completableFuture.complete(pm.convertMongo(document, 0));
                });
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }    }

    @Override
    public boolean updateProject(ProjectModel Project) {
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();
        Document query = new Document();
        query.put("_id", Project.getId());
        client.getDatabase(DATABASE_NAME).getCollection(PROJECT_COLLECTION).updateOne(eq("_id", Project.getId())
                , combine(set("name", Project.getName()),
                        set("start_date", Project.getStartDate()),
                        set("end_date", Project.getEndDate()),
                        set("category", Project.getCategorie())), (updateResult, throwable) -> {
                    completableFuture.complete(true);
                });
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }
}
