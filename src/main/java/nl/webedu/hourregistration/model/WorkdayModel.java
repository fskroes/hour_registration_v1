package nl.webedu.hourregistration.model;

import nl.webedu.hourregistration.database.DatabaseRowMapper;
import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class WorkdayModel extends DatabaseRowMapper<WorkdayModel> {

    // TODO: Fill in RowMappers

    private String id;
    private Date date, startTime, endTime;
    private int weekNumber;
    private List<String> activities;
    private List<String> employee_ids;

    public WorkdayModel() {
        
    }

    public WorkdayModel(String id, Date date, Date startTime, Date endTime, int weekNumber, List<String> activities, List<String> employee_ids) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekNumber = weekNumber;
        this.activities = activities;
        this.employee_ids = employee_ids;
    }
    public WorkdayModel(Date date, Date startTime, Date endTime, int weekNumber, List<String> activities, List<String> employee_ids) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekNumber = weekNumber;
        this.activities = activities;
        this.employee_ids = employee_ids;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public List<String> getActivitieIds() {
        return new ArrayList<>(activities);
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    public List<String> getEmployeeId() {
        return new ArrayList<>(employee_ids);
    }

    public void setEmployeeModels(List<String> employeeModels) {
        this.employee_ids = employeeModels;
    }

    public void addEmployee(String employeeModel) {
        this.employee_ids.add(employeeModel);
    }

    public void removeEmployee(String employeeModel) {
        this.employee_ids.remove(employeeModel);
    }

    @Override
    public String toString() {
        return weekNumber + " " + date.toString();
    }

    @Override
    public WorkdayModel convertSQL(ResultSet set, int rowNum) throws SQLException {
        // TODO: Setup MariaDB config
        return this;
    }

    @Override
    public WorkdayModel convertMongo(Document document) {
        // TODO: Setup MongoDB config
        return this;
    }
}
