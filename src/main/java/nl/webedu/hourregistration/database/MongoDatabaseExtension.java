package nl.webedu.hourregistration.database;

import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;

public class MongoDatabaseExtension extends Database<MongoClient> {

    private final String connectionString;

    public MongoDatabaseExtension(String connectionString) {
        this.connectionString = connectionString;
    }

    @Override
    public MongoClient openConnection() throws ClassNotFoundException {
        if (checkConnection()) {
            return connection;
        }
        connection = MongoClients.create(connectionString);
        return connection;
    }

    @Override
    public boolean checkConnection() {
        return connection != null;
    }

    @Override
    public boolean closeConnecion() {
        return false;
    }
}
