package nl.webedu.hourregistration.database;

import java.sql.SQLException;

public abstract class Database<T> {

    protected T connection;
    protected DatabaseType type;

    /**
     * Opens the connection to the implemented database
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public abstract T openConnection() throws ClassNotFoundException, SQLException;

    public T getConnection() {
        return connection;
    }

    public DatabaseType getType() {
        return type;
    }

    /**
     * Checks if connection is still active
     * @return
     * @throws SQLException
     */
    public abstract boolean checkConnection() throws SQLException;

    /**
     * Closes the connection to the database
     * @return
     */
    public abstract boolean closeConnecion();

}
