package nl.webedu.hourregistration.dao.mongodb;

import nl.webedu.hourregistration.dao.IUserAuthenticationDAO;
import nl.webedu.hourregistration.dao.factory.MongoDAOFactory;
import nl.webedu.hourregistration.model.UserAuthenticationModel;

public class MongoUserAuthenticationDAO implements IUserAuthenticationDAO {

    public MongoUserAuthenticationDAO() {
        // initialize

        MongoDAOFactory.createConnection();
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