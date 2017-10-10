package nl.webedu.hourregistration.database;

import com.mongodb.async.client.MongoCollection;
import nl.webedu.hourregistration.dao.factory.DAOFactory;
import nl.webedu.hourregistration.dao.factory.MariaDAOFactory;
import nl.webedu.hourregistration.dao.factory.MongoDAOFactory;
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

    public MongoCollection<Document> getMongoCollection() {
        return instance.getMongoCollection();
    }
}
