package nl.webedu.hourregistration.models;

import java.sql.Time;

public class Workday {
    private int weekNumber;
    private int weekDay;
    private Time startTime;
    private Time endTime;
    private Project project;
    private String category;
    private String description;

    public Workday(int weekNumber, int weekDay, Time startTime, Time endTime, Project project, String category, String description) {
        this.weekNumber = weekNumber;
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.project = project;
        this.category = category;
        this.description = description;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
