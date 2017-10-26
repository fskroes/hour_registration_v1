package nl.webedu.hourregistration.model;

import nl.webedu.hourregistration.database.DatabaseRowMapper;
import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class ActivitiesModel extends DatabaseRowMapper<ActivitiesModel> {

    private String id;
    private String category;
    private Date startTime, endTime;
    private int workdayId;

    public ActivitiesModel() {
        type = ActivitiesModel.class;
    }

    public ActivitiesModel(String category, Date startTime, Date endTime, int workdayId) {
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workdayId = workdayId;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getWorkdayId() {
        return workdayId;
    }

    public void setWorkday(int workday) {
        this.workdayId = workday;
    }

    @Override
    public String toString() {
        return " id: "+id+ " categorie: " +category+ " stime: " +startTime+ " etime: " +endTime+ " wdayid: " +workdayId;
    }

    @Override
    public ActivitiesModel convertSQL(ResultSet set, int rowNum) throws SQLException {
        this.id = String.valueOf("activityID");
        this.category = set.getString("category");
        this.startTime = set.getDate("start_time");
        this.endTime = set.getDate("end_time");
        return this;
    }

    @Override
    public ActivitiesModel convertMongo(Document set) {
        this.id = String.valueOf(set.getObjectId("_id"));
        this.category = set.getString("category");
        this.startTime = set.getDate("start_time");
        this.endTime = set.getDate("end_time");
        return this;
    }
}
