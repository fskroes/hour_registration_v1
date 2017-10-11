package nl.webedu.hourregistration.database;

import com.mongodb.async.client.MongoCollection;
import nl.webedu.hourregistration.dao.factory.DAOFactory;
import nl.webedu.hourregistration.dao.factory.MariaDAOFactory;
import nl.webedu.hourregistration.dao.factory.MongoDAOFactory;
import nl.webedu.hourregistration.dao.mariadb.MariadbProjectDAO;
import org.bson.Document;

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
                    "80.208.224.30",
                    "3306",
                    "hour_registration",
                    "root",
                    "Chaud");
            try {
                database.openConnection();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            daoFactory = MariaDAOFactory.getInstance();
        } else if (type == DatabaseType.MONGODB) {
            database = new MongoDatabaseExtension("mongodb://80.208.224.30");
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

    public MongoCollection<Document> getMongoCollection() {
        return instance.getMongoCollection();
    }
}
