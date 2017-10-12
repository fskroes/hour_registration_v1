package nl.webedu.hourregistration.model;

import nl.webedu.hourregistration.database.DatabaseRowMapper;
import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ActivitiesModel extends DatabaseRowMapper<ActivitiesModel> {

    private String id;
    private String category;
    private Date startTime, endTime;
    private WorkdayModel workday;

    public ActivitiesModel() {

    }

    public ActivitiesModel(String id, String category, Date startTime, Date endTime) {
        this.id = id;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public String getActivityId() {
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

    public WorkdayModel getWorkday() {
        return workday;
    }

    public void setWorkday(WorkdayModel workday) {
        this.workday = workday;
    }

    @Override
    public String toString() {
        return this.category;
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
    public ActivitiesModel convertMongo(Document set, int rowNum) {
        this.id = set.getString("_id");
        this.category = set.getString("category");
        this.startTime = set.getDate("start_time");
        this.endTime = set.getDate("end_time");
        return this;
    }
}
