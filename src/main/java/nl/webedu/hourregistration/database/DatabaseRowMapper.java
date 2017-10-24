package nl.webedu.hourregistration.database;

import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DatabaseRowMapper<E> {

    public abstract E convertSQL(ResultSet set, int rowNum) throws SQLException;

    public abstract E convertMongo(Document set, int rowNum);

}
