package nl.webedu.hourregistration.dao.mongodb;

import com.mongodb.async.client.MongoClient;
import nl.webedu.hourregistration.dao.IUserAuthenticationDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.UserAuthenticationModel;

public class MongoUserAuthenticationDAO implements IUserAuthenticationDAO {

    private static MongoUserAuthenticationDAO instance;

    private MongoClient client = (MongoClient) DatabaseManager.getInstance().getDatabase();

    public MongoUserAuthenticationDAO() {
        // initialize
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
    public UserAuthenticationModel registerUser(String username, String password) {
        return null;
    }

    @Override
    public void authenticateUser() {

    }
}