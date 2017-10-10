package nl.webedu.hourregistration.dao.mongodb;

import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoCollection;
import nl.webedu.hourregistration.dao.IUserAuthenticationDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.UserAuthenticationModel;
import org.bson.Document;

import static nl.webedu.hourregistration.database.DatabaseUtil.DATABASE_NAME;
import static nl.webedu.hourregistration.database.DatabaseUtil.EMPLOYEE_COLLECTION;

public class MongoUserAuthenticationDAO implements IUserAuthenticationDAO {

    private static MongoUserAuthenticationDAO instance;

    private MongoClient client;
    private SingleResultCallback<Void> callbackWhenFinished = (aVoid, throwable) -> System.out.println("Operation Finished");

    public MongoUserAuthenticationDAO() {
        this.client = (MongoClient) DatabaseManager.getInstance().getDatabase().getConnection();
    }

    public static MongoUserAuthenticationDAO getInstance() {
        if (instance == null)
            instance = new MongoUserAuthenticationDAO();
        return instance;
    }

    @Override
    public UserAuthenticationModel findUser() {
        return null;
    }

    @Override
    public void registerUser(String username, String password) {
        MongoCollection<Document> coll = client.getDatabase(DATABASE_NAME).getCollection(EMPLOYEE_COLLECTION);
        Document query =
                new Document("username", username)
                        .append("password", password);

        coll.insertOne(query, callbackWhenFinished);
    }

    @Override
    public boolean authenticateUser() {
        return false;
    }
}