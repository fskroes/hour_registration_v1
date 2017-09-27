package nl.webedu.hourregistration.database;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MariaDatabase extends Database<Connection> {

    private final String hostname;
    private final String port;
    private final String database;
    private final String user;
    private final String password;

    MariaDatabase(String hostname, String port, String database, String user, String password) {
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    @Override
    public Connection openConnection() throws SQLException, ClassNotFoundException {
        if (checkConnection()) {
            return connection;
        }
        try {
            Class.forName("com.mariadb.jdbc.Driver");
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

    public <E> E selectObjectSingle(DatabaseParser<E> object, String query) throws SQLException {
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

    public <E> E selectObjectSingle(DatabaseParser<E> object, String query, Object... params) throws SQLException {
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

    public <E> List<E> selectObjectList(DatabaseParser<E> object, String query) throws SQLException {
        List<E> result = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            int rowNum = 0;
            while (resultSet.next()) {
                E obj = object.convertSQL(resultSet, rowNum++);
                result.add(obj);
            }
        }

        return result;
    }

    public <E> List<E> selectObjectList(DatabaseParser<E> object, String query, Object... params) throws SQLException {
        List<E> result = new LinkedList<>();
        try (PreparedStatement statement = prepareStatement(query, params)) {
            ResultSet resultSet = statement.executeQuery();
            int rowNum = 0;
            while (resultSet.next()) {
                E obj = object.convertSQL(resultSet, rowNum++);
                result.add(obj);
            }
        }

        return result;
    }

    public ResultSet selectQuery(String sql) throws SQLException {
        PreparedStatement stat = connection.prepareStatement(sql);
        return stat.executeQuery();
    }

    public ResultSet selectQuery(String sql, Object... params) throws SQLException {
        PreparedStatement stat = prepareStatement(sql, params);
        return stat.executeQuery();
    }

    public int updateQuery(String sql) throws SQLException {
        PreparedStatement stat = connection.prepareStatement(sql);
        return stat.executeUpdate();
    }

    public int updateQuery(String sql, Object... params) throws SQLException {
        PreparedStatement stat = prepareStatement(sql, params);
        return stat.executeUpdate();
    }

    private PreparedStatement prepareStatement(String sql, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        int i = 1;
        for (Object parameter : params) {
            statement.setObject(i++, parameter);
        }

        return statement;
    }
}
