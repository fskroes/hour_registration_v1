package nl.webedu.hourregistration.database;

import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DatabaseRowMapper<E> {

    protected Class<E> type;

    /**
     * Maps the given resultSet to the implemented object
     * @param set - set returned by the query
     * @param rowNum - rownumber of the returned set
     * @return - The object that has implemented
     * @throws SQLException
     */
    public abstract E convertSQL(ResultSet set, int rowNum) throws SQLException;

    /**
     * Maps the given document to the implemented object
     * @param document - document found by MongoDB from the query
     * @return
     */
    public abstract E convertMongo(Document document);

    /**
     * Class indetifier for the DatabaseExtention
     * @return
     */
    public Class<E> getType() {
        return type;
    }

}
