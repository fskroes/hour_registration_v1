package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IUserAuthenticationDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ActivitiesModel;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.UserAuthenticationModel;

import java.sql.ResultSet;
import java.sql.SQLException;

import static nl.webedu.hourregistration.helpers.PasswordHashing.hashPassword;

public class MariadbUserAuthenticationDAO implements IUserAuthenticationDAO {

    private static MariadbUserAuthenticationDAO instance;

    private UserAuthenticationModel model;
    private MariaDatabaseExtension database;

    private MariadbUserAuthenticationDAO() {
        this.database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();
    }

    public static MariadbUserAuthenticationDAO getInstance() {
        if (instance == null) {
            instance = new MariadbUserAuthenticationDAO();
        }
        return instance;
    }

    @Override
    public UserAuthenticationModel findUser(String email) {
        UserAuthenticationModel user = null;
        String selectSQL = "SELECT * FROM employee WHERE email = ?";
        try {
            user = database.selectObjectSingle(new UserAuthenticationModel(), selectSQL, email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void registerUser(String username, String password) {
        model = null;
        model = findUser(username);
        if(model != null) {
            System.out.println(username + " already exists");
            return;
        }

        String hashedPassword = hashPassword(password);

        String insertSQL = "INSERT INTO employee"
                + "(email, password) VALUES"
                + "(?,?)";

        try {
            database.insertQuery(insertSQL, username, hashedPassword);
            System.out.println(username + " is registered");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        model = null;
        model = findUser(email);
        if(model != null)
            return true;
        return false;
    }
}
