package nl.webedu.hourregistration.model;

import nl.webedu.hourregistration.database.DatabaseRowMapper;
import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class LogModel extends DatabaseRowMapper<LogModel> {

    private String id;
    private EmployeeModel employeeModel;
    private Date date;
    private String description;

    public LogModel() {
    }

    public LogModel(EmployeeModel employeeModel, Date date, String description) {
        this.employeeModel = employeeModel;
        this.date = date;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public EmployeeModel getEmployeeModel() {
        return employeeModel;
    }

    public void setEmployeeModel(EmployeeModel employeeModel) {
        this.employeeModel = employeeModel;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public LogModel convertSQL(ResultSet set, int rowNum) throws SQLException {
        this.id = String.valueOf("logID");
        this.date = set.getDate("date");
        this.description = set.getString("description");
        return this;
    }

    @Override
    public LogModel convertMongo(Document set) {
        this.id = set.getString("_id");
        this.date = set.getDate("date");
        this.description = set.getString("description");
        return this;
    }
}
