package nl.webedu.hourregistration.database;

import nl.webedu.hourregistration.model.ContractModel;
import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public abstract class DatabaseRowMapper<E> {

    public abstract E convertSQL(ResultSet set, int rowNum) throws SQLException;

    public abstract E convertMongo(Document document) throws SQLException;
}
