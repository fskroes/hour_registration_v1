package nl.webedu.hourregistration.model;

import java.util.Date;

public class ActivitiesModel {

    private int id;
    private String category;
    private Date startTime, endTime;
    private int workdayId;

    public ActivitiesModel() {

    }

    public ActivitiesModel(int id, String category, Date startTime, Date endTime, int workdayId) {
        this.id = id;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workdayId = workdayId;
    }


    public int getId() {
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

    @Override
    public String toString() {
        return this.category;
    }
}
