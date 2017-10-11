package nl.webedu.hourregistration.dao.mongodb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Block;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.FindIterable;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoCollection;
import nl.webedu.hourregistration.dao.IUserAuthenticationDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.UserAuthenticationModel;
import org.bson.BsonDocument;
import org.bson.Document;

import java.io.IOException;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static nl.webedu.hourregistration.database.DatabaseUtil.DATABASE_NAME;
import static nl.webedu.hourregistration.database.DatabaseUtil.EMPLOYEE_COLLECTION;

public class MongoUserAuthenticationDAO implements IUserAuthenticationDAO {

    private static MongoUserAuthenticationDAO instance;

    private MongoClient client;
    private UserAuthenticationModel model;

    public MongoUserAuthenticationDAO() {
        this.client = (MongoClient) DatabaseManager.getInstance().getDatabase().getConnection();
    }

    public static MongoUserAuthenticationDAO getInstance() {
        if (instance == null)
            instance = new MongoUserAuthenticationDAO();
        return instance;
    }

    @Override
    public void registerUser(String username, String password) {
        MongoCollection<Document> coll = client.getDatabase(DATABASE_NAME).getCollection(EMPLOYEE_COLLECTION);
        Document query =
                new Document("username", username)
                        .append("password", password);

        coll.insertOne(query, new SingleResultCallback<Void>() {
            @Override
            public void onResult(Void aVoid, Throwable throwable) {
                System.err.println(throwable.getMessage());
            }
        });
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        findUser(email, password);
        if (email == model.getEmail()) {
            return true;
        }

        return false;
    }

    @Override
    public void findUser(String email, String password) {
        client.getDatabase(DATABASE_NAME).getCollection(EMPLOYEE_COLLECTION)
                .find(eq(email, password)).forEach(printBlock, new SingleResultCallback<Void>() {
            @Override
            public void onResult(Void aVoid, Throwable throwable) {

            }
        });
    }

    Block<Document> printBlock = new Block<Document>() {
        @Override
        public void apply(final Document document) {
            System.out.println(document.toJson());

            ObjectMapper mapper = new ObjectMapper();
            model = null;
            try {
                model = mapper.readValue(document.toJson(), UserAuthenticationModel.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("model.toString: " + model.toString());
            System.out.println("model email: " + model.getEmail());
        }
    };






    SingleResultCallback<Document> printDocument = new SingleResultCallback<Document>() {
        @Override
        public void onResult(final Document document, final Throwable t) {
            System.out.println(document.toJson());
        }
    };
}