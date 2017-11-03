package nl.webedu.hourregistration.model;

import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.DatabaseRowMapper;
import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

/**
 * Model van een activitie
 * met alle getters en setters
 */
public class ActivitiesModel extends DatabaseRowMapper<ActivitiesModel> {

    private String id;
    private LocalTime startTime, endTime;
    private WorkdayModel workday;
    private ProjectModel project;

    public ActivitiesModel() {
        type = ActivitiesModel.class;
    }

    public ActivitiesModel(LocalTime startTime, LocalTime endTime, WorkdayModel workday) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.workday = workday;
    }

    public String getId() {
        return id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
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
        this.startTime = set.getTime("start_time").toLocalTime();
        this.endTime = set.getTime("end_time").toLocalTime();
        this.project = DatabaseManager.getInstance().getDaoFactory().getProjectDAO().findProject(String.valueOf(set.getInt("projectID")));
        return this;
    }

    @Override
    public ActivitiesModel convertMongo(Document document) {
        this.id = String.valueOf(document.getObjectId("_id"));
        this.startTime = LocalTime.parse(document.getString("start_time"));
        this.endTime = LocalTime.parse(document.getString("end_time"));
        return this;
    }
}
