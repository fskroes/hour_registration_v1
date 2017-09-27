package nl.webedu.hourregistration.database;

import java.sql.SQLException;

public abstract class Database<T> {

    protected T connection;

    public abstract T openConnection() throws SQLException, ClassNotFoundException;

    public T getConnection() {
        return connection;
    }

    public abstract boolean checkConnection() throws SQLException;

    public abstract boolean closeConnecion();

}
