package nl.webedu.hourregistration.dao.mongodb;

import com.mongodb.async.client.MongoClient;
import nl.webedu.hourregistration.dao.IProjectDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.CustomerModel;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.ProjectModel;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static nl.webedu.hourregistration.database.DatabaseUtil.DATABASE_NAME;
import static nl.webedu.hourregistration.database.DatabaseUtil.PROJECT_COLLECTION;

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
    public int insertProject(ProjectModel Project) {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        Document query = new Document("name", Project.getName())
                .append("start_date",Project.getStartDate())
                .append("end_date",Project.getEndDate());

        client.getDatabase(DATABASE_NAME).getCollection(PROJECT_COLLECTION)
                .insertOne(query, (result, t) -> completableFuture.complete(1));
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public int deleteProject(ProjectModel project) {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        Document query = new Document();
        query.put("_id", project.getId());

        client.getDatabase(DATABASE_NAME).getCollection(PROJECT_COLLECTION)
                .deleteOne(query, (deleteResult, throwable) -> completableFuture.complete((int) deleteResult.getDeletedCount()));
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ProjectModel findProject(String id) {
        CompletableFuture<ProjectModel> completableFuture = new CompletableFuture<>();
        ProjectModel pm = new ProjectModel();

        client.getDatabase(DATABASE_NAME).getCollection(PROJECT_COLLECTION).find(
                eq("_id", id)).first((document, throwable) -> completableFuture.complete(pm.convertMongo(document)));
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateProject(ProjectModel Project) {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        Document query = new Document();
        query.put("_id", Project.getId());
        client.getDatabase(DATABASE_NAME).getCollection(PROJECT_COLLECTION).updateOne(eq("_id", Project.getId())
                , combine(set("name", Project.getName()),
                        set("start_date", Project.getStartDate()),
                        set("end_date", Project.getEndDate())), (updateResult, throwable) -> {
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
    public List<ProjectModel> selectAllProjects() {
        return null;
    }

    @Override
    public ProjectModel selectProjectByCustomer(CustomerModel customer) {
        return null;
    }

    @Override
    public List<ProjectModel> selectProjectsByEmployee(EmployeeModel employee) {
        return null;
    }

    @Override
    public int DeleteJunctionItemByEmployee(EmployeeModel employee, ProjectModel project) {
        return 0;
    }

    @Override
    public int addJunctionItemWithProject(EmployeeModel employee, ProjectModel project) {
        return 0;
    }
}
