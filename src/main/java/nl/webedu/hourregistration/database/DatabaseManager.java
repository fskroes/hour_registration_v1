package nl.webedu.hourregistration.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {

    private static DatabaseManager instance;

    private Database database;

    private DatabaseManager() {  }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Database connectToDatabase(DatabaseType type) {
        if (type == DatabaseType.MARIADB) {
            database = new MariaDatabase(
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
        }
        return null;
    }

    public Database getDatabase() {
        return database;
    }
}