package nl.webedu.hourregistration.model;

import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.DatabaseRowMapper;
import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Model van een workday
 * met alle getters en setters
 */
public class WorkdayModel extends DatabaseRowMapper<WorkdayModel> {

    private String id;
    private LocalDate date;
    private LocalTime startTime, endTime;
    private String dayName;
    private int weekNumber;
    private List<ActivitiesModel> activities;
    private List<String> employee_ids;

    public WorkdayModel() {
        type = WorkdayModel.class;
    }

    public WorkdayModel(LocalDate date, LocalTime startTime, LocalTime endTime, int weekNumber, String workday) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekNumber = weekNumber;
        this.dayName = workday;
        this.activities = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public List<ActivitiesModel> getActivities() {
        return new ArrayList<>(activities);
    }

    public void setActivities(List<ActivitiesModel> activities) {
        this.activities = activities;
    }

    public void addActivity(ActivitiesModel activity) {
        this.activities.add(activity);
    }

    public void removeActivity(ActivitiesModel activity) {
        this.activities.remove(activity);
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
    public WorkdayModel convertSQL(ResultSet set, int rowNum) throws SQLException {
        this.id = String.valueOf(set.getInt("workdayID"));
        this.date = set.getDate("date").toLocalDate();
        this.weekNumber = set.getInt("week_number");
        this.startTime = set.getTime("start_time").toLocalTime();
        this.endTime = set.getTime("end_time").toLocalTime();
        this.dayName = set.getString("day_name");
        this.activities = DatabaseManager.getInstance().getDaoFactory().getActivitiesDAO().selectActivitiesByWorkday(this);
        return this;
    }

    @Override
    public WorkdayModel convertMongo(Document document) {
        this.id = document.getObjectId("_id").toString();
        this.date = LocalDate.parse(document.getString("date"));
        this.weekNumber = document.getInteger("week_number");
        this.startTime = LocalTime.parse(document.getString("start_time"));
        this.endTime = LocalTime.parse(document.getString("end_time"));
        this.dayName = document.getString("day_name");
        return this;
    }

}
