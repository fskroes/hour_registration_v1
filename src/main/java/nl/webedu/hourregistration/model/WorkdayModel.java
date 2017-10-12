package nl.webedu.hourregistration.model;

import nl.webedu.hourregistration.database.DatabaseRowMapper;
import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkdayModel extends DatabaseRowMapper<WorkdayModel> {

    // TODO: Fill in RowMappers

    private String id;
    private Date date, startTime, endTime;
    private int weekNumber;
    private List<ActivitiesModel> activities;
    private List<EmployeeModel> employeeModels;

    public WorkdayModel() {
        
    }

    public WorkdayModel(String id, Date date, Date startTime, Date endTime, int weekNumber, List<ActivitiesModel> activities, List<EmployeeModel> employeeModels) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekNumber = weekNumber;
        this.activities = activities;
        this.employeeModels = employeeModels;
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

    public List<ActivitiesModel> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivitiesModel> activities) {
        this.activities = activities;
    }

    public List<EmployeeModel> getEmployeeModels() {
        return new ArrayList<>(employeeModels);
    }

    public void setEmployeeModels(List<EmployeeModel> employeeModels) {
        this.employeeModels = employeeModels;
    }

    public void addEmployee(EmployeeModel employeeModel) {
        this.employeeModels.add(employeeModel);
    }

    public void removeEmployee(EmployeeModel employeeModel) {
        this.employeeModels.remove(employeeModel);
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
    public WorkdayModel convertMongo(Document set, int rowNum) throws SQLException {
        // TODO: Setup MongoDB config
        return this;
    }
}
