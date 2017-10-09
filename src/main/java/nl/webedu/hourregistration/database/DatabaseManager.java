package nl.webedu.hourregistration.database;

import nl.webedu.hourregistration.factory.DAOFactory;
import nl.webedu.hourregistration.factory.MariaDAOFactory;
import nl.webedu.hourregistration.factory.MongoDAOFactory;

import java.sql.SQLException;

public class DatabaseManager {

    private static DatabaseManager instance;

    private Database database;
    private DAOFactory daoFactory;

    private DatabaseManager() {  }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void connectToDatabase(DatabaseType type) {
        if (type == DatabaseType.MARIADB) {
            database = new MariaDatabaseExtension(
                    "127.0.0.1",
                    "3306",
                    "hour_registration",
                    "root",
                    "root");
            try {
                database.openConnection();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            daoFactory = MongoDAOFactory.getInstance();
        } else if (type == DatabaseType.MONGODB) {
            database = new MongoDatabaseExtension("mongodb://localhost");
            try {
                database.openConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            daoFactory = new MariaDAOFactory();
        }
    }

    public Database getDatabase() {
        return database;
    }

    public DAOFactory getDaoFactory() {
        return daoFactory;
    }
}
