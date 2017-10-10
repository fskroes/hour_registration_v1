package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IUserAuthenticationDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.UserAuthenticationModel;

public class MariadbUserAuthenticationDAO implements IUserAuthenticationDAO {

    private MariaDatabaseExtension database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();

    @Override
    public UserAuthenticationModel findUser() {
        return null;
    };

    @Override
    public UserAuthenticationModel registerUser(String username, String password) {
        return null;
    };

    @Override
    public void authenticateUser() {

    };
}
