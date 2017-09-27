package nl.webedu.hourregistration.models;

import java.util.Date;

public class Contract {

    private Date startDate; //Er zit ook een type Date bij voor SQL, misschien handig?
    private Date endDate;
    private int minHours;
    private int maxHours;

    public Contract(Date startDate, Date endDate, int minHours, int maxHours) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.minHours = minHours;
        this.maxHours = maxHours;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
}
