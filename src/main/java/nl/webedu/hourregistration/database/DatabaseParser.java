package nl.webedu.hourregistration.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DatabaseParser<E> {

    public abstract E convertSQL(ResultSet set, int rowNum) throws SQLException;

    public abstract E convertMongo(ResultSet set, int rowNum) throws SQLException;

}
