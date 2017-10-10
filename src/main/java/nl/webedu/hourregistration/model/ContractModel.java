package nl.webedu.hourregistration.model;

import nl.webedu.hourregistration.database.DatabaseRowMapper;
import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

public class ContractModel extends DatabaseRowMapper<ContractModel> {

    private int id;
    private int minHours;
    private int maxHours;
    private Date startTime;
    private Date endTime;

    public ContractModel() {
    }

    public ContractModel(int id, int minHours, int maxHours, Date startTime, Date endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.minHours = minHours;
        this.maxHours = maxHours;
    }

    public int getId() {
        return id;
    }

    public int getMinHours() {
        return minHours;
    }

    public void setMinHours(int minHours) {
        this.minHours = minHours;
    }

    public int getMaxHours() {
        return maxHours;
    }

    public void setMaxHours(int maxHours) {
        this.maxHours = maxHours;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public ContractModel convertSQL(ResultSet set, int rowNum) throws SQLException {
        return new ContractModel(
                set.getInt("contractID"),
                set.getInt("min_hours"),
                set.getInt("max_hours"),
                set.getTime("start_time"),
                set.getTime("end_time")
        );
    }

    @Override
    public ContractModel convertMongo(Document set, int rowNum) {
        return new ContractModel(
                set.getInteger("_id"),
                set.getInteger("min_hours"),
                set.getInteger("max_hours"),
                set.getDate("start_time"),
                set.getDate("end_time")
        );
    }
}
