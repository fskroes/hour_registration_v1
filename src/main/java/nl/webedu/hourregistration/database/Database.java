package nl.webedu.hourregistration.database;

import java.sql.SQLException;

public abstract class Database<T> {

    protected T connection;
    protected DatabaseType type;

    public abstract T openConnection() throws ClassNotFoundException, SQLException;

    public T getConnection() {
        return connection;
    }

    public DatabaseType getType() {
        return type;
    }

    public abstract boolean checkConnection() throws SQLException;

    public abstract boolean closeConnecion();

}
