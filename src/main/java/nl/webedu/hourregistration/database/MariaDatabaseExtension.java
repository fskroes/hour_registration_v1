package nl.webedu.hourregistration.database;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MariaDatabaseExtension extends Database<Connection> {

    private final String hostname;
    private final String port;
    private final String database;
    private final String user;
    private final String password;

    MariaDatabaseExtension(String hostname, String port, String database, String user, String password) {
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
        type = DatabaseType.MARIADB;
    }

    @Override
    public Connection openConnection() throws SQLException, ClassNotFoundException {
        if (checkConnection()) {
            return connection;
        }
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mariadb://"
                            + this.hostname + ":" + this.port + "/" + this.database + "?autoReconnect=true&useSSL=false",
                    this.user, this.password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    @Override
    public boolean checkConnection() throws SQLException {
        return connection != null && !connection.isClosed();
    }

    @Override
    public boolean closeConnecion() {
        try {
            connection.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * search for a object in the database with the given SQL query
     * @param object - object that needs to be mapped and returned
     * @param query - SQL query
     * @param <E> - object type
     * @return - mapped found object
     * @throws SQLException
     */
    public <E> E selectObjectSingle(DatabaseRowMapper<E> object, String query) throws SQLException {
        E result = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = object.convertSQL(resultSet, 1);

                if (resultSet.next()) {
                    throw new SQLException("The query returned more than one result");
                }
            }
        }

        return result;
    }

    /**
     * search for a object in the database with the given SQL query
     * @param object - object that needs to be mapped and returned
     * @param query - SQL query
     * @param params - parameter with in the SQL query
     * @param <E> - object type
     * @return - mapped found object
     * @throws SQLException
     */
    public <E> E selectObjectSingle(DatabaseRowMapper<E> object, String query, Object... params) throws SQLException {
        E result = null;

        try (PreparedStatement statement = prepareStatement(query, params)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = object.convertSQL(resultSet, 1);

                if (resultSet.next()) {
                    throw new SQLException("The query returned more than one result");
                }
            }
        }

        return result;
    }

    /**
     * search for a object in the database with the given SQL query
     * @param object - object that needs to be mapped and returned
     * @param query - SQL query
     * @param <E> - object type
     * @return - mapped found object list
     * @throws SQLException
     */
    public <E> List<E> selectObjectList(DatabaseRowMapper<E> object, String query) throws SQLException {
        List<E> result = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            int rowNum = 0;
            while (resultSet.next()) {
                DatabaseRowMapper<E> newInstance = (DatabaseRowMapper<E>) object.getType().newInstance();
                E obj = newInstance.convertSQL(resultSet, rowNum++);
                result.add(obj);
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * search for a object in the database with the given SQL query
     * @param object - object that needs to be mapped and returned
     * @param query - SQL query
     * @param params - parameter with in the SQL query
     * @param <E> - object type
     * @return - mapped found object list
     * @throws SQLException
     */
    public <E> List<E> selectObjectList(DatabaseRowMapper<E> object, String query, Object... params) throws SQLException {
        List<E> result = new LinkedList<>();
        try (PreparedStatement statement = prepareStatement(query, params)) {
            ResultSet resultSet = statement.executeQuery();
            int rowNum = 0;
            while (resultSet.next()) {
                DatabaseRowMapper<E> newInstance = (DatabaseRowMapper<E>) object.getType().newInstance();
                E obj = newInstance.convertSQL(resultSet, rowNum++);
                result.add(obj);
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Made for custom/complex queries - unused for now
     * @param sql - query
     * @return - found resultSet
     * @throws SQLException
     */
    public ResultSet selectQuery(String sql) throws SQLException {
        PreparedStatement stat = connection.prepareStatement(sql);
        return stat.executeQuery();
    }

    /**
     * Made for custom/complex queries with optional parameters - unused for now
     * @param sql - query
     * @param params - optional parameters
     * @return - found resultSet
     * @throws SQLException
     */
    public ResultSet selectQuery(String sql, Object... params) throws SQLException {
        PreparedStatement stat = prepareStatement(sql, params);
        return stat.executeQuery();
    }

    /**
     * Custom insert query that returns the generated key in the database
     * @param sql - query
     * @return
     * @throws SQLException
     */
    public int insertQuery(String sql) throws SQLException {
        PreparedStatement stat = connection.prepareStatement(sql);
        stat.executeUpdate();
        ResultSet rs = stat.getGeneratedKeys();
        if (rs.next()) {
            return (int) rs.getLong(1);
        }
        return (int) stat.getGeneratedKeys().getLong(1);
    }

    /**
     * Custom insert query that returns the generated key in the database
     * @param sql - query
     * @param params - optional parameters
     * @return
     * @throws SQLException
     */
    public int insertQuery(String sql, Object... params) throws SQLException {
        PreparedStatement stat = prepareStatement(sql, params);
        stat.executeUpdate();
        ResultSet rs = stat.getGeneratedKeys();
        if (rs.next()) {
            return (int) rs.getLong(1);
        }
        return (int) stat.getGeneratedKeys().getLong(1);
    }

    /**
     * Simple update query
     * @param sql - query
     * @return
     * @throws SQLException
     */
    public int updateQuery(String sql) throws SQLException {
        PreparedStatement stat = connection.prepareStatement(sql);
        return stat.executeUpdate();
    }

    /**
     * Simple update query
     * @param sql - query
     * @param params - optional parameters
     * @return
     * @throws SQLException
     */
    public int updateQuery(String sql, Object... params) throws SQLException {
        PreparedStatement stat = prepareStatement(sql, params);
        return stat.executeUpdate();
    }

    /**
     * Simple delete query - uses the same functions as update
     * @param sql - query
     * @return
     * @throws SQLException
     */
    public int deleteQuery(String sql) throws SQLException {
        return updateQuery(sql);
    }

    /**
     * Simple delete query - uses the same functions as update
     * @param sql - query
     * @param params = optional parameters
     * @return
     * @throws SQLException
     */
    public int deleteQuery(String sql, Object... params) throws SQLException {
        return updateQuery(sql, params);
    }

    /**
     * Prepares the statements with optional parameters, prevents SQL injection
     * @param sql - query
     * @param params - optional parameters
     * @return
     * @throws SQLException
     */
    private PreparedStatement prepareStatement(String sql, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        int i = 1;
        for (Object parameter : params) {
            statement.setObject(i++, parameter);
        }

        return statement;
    }
}
