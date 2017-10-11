package nl.webedu.hourregistration.dao.mongodb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoCollection;
import nl.webedu.hourregistration.dao.IUserAuthenticationDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.UserAuthenticationModel;
import org.bson.Document;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static nl.webedu.hourregistration.database.DatabaseUtil.DATABASE_NAME;
import static nl.webedu.hourregistration.database.DatabaseUtil.EMPLOYEE_COLLECTION;
import static nl.webedu.hourregistration.helpers.PasswordHashing.hashPassword;

import java.security.SecureRandom;

public class MongoUserAuthenticationDAO implements IUserAuthenticationDAO {

    private static MongoUserAuthenticationDAO instance;

    private MongoClient client;
    private UserAuthenticationModel model;
    private ObjectMapper mapper = new ObjectMapper();


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
        String hashedPassword = hashPassword(password);

        MongoCollection<Document> coll = client.getDatabase(DATABASE_NAME).getCollection(EMPLOYEE_COLLECTION);
        Document query =
                new Document("username", username)
                        .append("password", hashedPassword);


        coll.insertOne(query, new SingleResultCallback<Void>() {
            @Override
            public void onResult(Void aVoid, Throwable throwable) {
                System.err.println(throwable.getMessage());
            }
        });
    }


    @Override
    public boolean authenticateUser(String email, String password) {
        model = null;
        model = findUser(email, password);
        if(model != null) {
            return true;
        }
        return false;
    }

    @Override
    public UserAuthenticationModel findUser(String email, String password) {
        final UserAuthenticationModel[] lib = {new UserAuthenticationModel()};

        MongoCollection<Document> collection = client.getDatabase(DATABASE_NAME).getCollection(EMPLOYEE_COLLECTION);
        collection.find(eq("email", email))
                .into(new ArrayList<Document>(), new SingleResultCallback<ArrayList<Document>>() {
                @Override
                public void onResult(ArrayList<Document> documents, Throwable throwable) {
                    for (Document document : documents) {
                        System.out.println(document.toJson());

                        try {

                            lib[0] = mapper.readValue(document.toJson(), UserAuthenticationModel.class);
                            System.out.println(lib[0].get_id());
                            System.out.println(lib[0].getEmail());
                            System.out.println(lib[0].getPassword());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        });

        return lib[0];
    }
}