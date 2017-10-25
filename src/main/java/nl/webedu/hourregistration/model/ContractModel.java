package nl.webedu.hourregistration.model;

import nl.webedu.hourregistration.database.DatabaseRowMapper;
import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.Optional;

public class ContractModel extends DatabaseRowMapper<ContractModel> {

    private String id;
    private int minHours;
    private int maxHours;
    private Date startTime;
    private Date endTime;

    public ContractModel() {
    }

    public ContractModel(int minHours, int maxHours, Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.minHours = minHours;
        this.maxHours = maxHours;
    }

    public String getId() {
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
        this.id = String.valueOf("contractID");
        this.minHours = set.getInt("min_hours");
        this.maxHours = set.getInt("max_hours");
        this.startTime = set.getTime("start_time");
        this.endTime =set.getTime("end_time");
        return this;
    }

    @Override
    public ContractModel convertMongo(Document set) {
        this.id = set.getString("_id");
        this.minHours = set.getInteger("min_hours");
        this.maxHours = set.getInteger("max_hours");
        this.startTime = set.getDate("start_time");
        this.endTime =set.getDate("end_time");
        return this;
    }
}
