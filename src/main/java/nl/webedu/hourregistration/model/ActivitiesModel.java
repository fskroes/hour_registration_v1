package nl.webedu.hourregistration.model;

import nl.webedu.hourregistration.database.DatabaseRowMapper;
import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ActivitiesModel extends DatabaseRowMapper<ActivitiesModel> {

    private String id;
    private Date startTime, endTime;
    private WorkdayModel workday;
    private ProjectModel project;

    public ActivitiesModel() {
        type = ActivitiesModel.class;
    }

    public ActivitiesModel(Date startTime, Date endTime, WorkdayModel workday) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.workday = workday;
    }

    public String getId() {
        return id;
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

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return " id: "+id+ " stime: " +startTime+ " etime: " +endTime+ " wdayid: " + workday;
    }

    @Override
    public ActivitiesModel convertSQL(ResultSet set, int rowNum) throws SQLException {
        this.id = String.valueOf(set.getInt("activityID"));
        this.startTime = set.getDate("start_time");
        this.endTime = set.getDate("end_time");
        return this;
    }

    @Override
    public ActivitiesModel convertMongo(Document set) {
        this.id = String.valueOf(set.getObjectId("_id"));
        this.startTime = set.getDate("start_time");
        this.endTime = set.getDate("end_time");
        return this;
    }
}
