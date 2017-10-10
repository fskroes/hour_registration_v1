package nl.webedu.hourregistration.database;

import nl.webedu.hourregistration.dao.factory.DAOFactory;
import nl.webedu.hourregistration.dao.factory.MongoDAOFactory;

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
                System.out.println("Connected to MongoDB");
            } catch (Exception e) {
                System.out.println("Error connecting to the MongoDB");
                e.printStackTrace();
            }
            daoFactory = MongoDAOFactory.getInstance();
        }
    }

    public Database getDatabase() {
        return database;
    }

    public DAOFactory getDaoFactory() {
        return daoFactory;
    }
}
