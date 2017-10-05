package nl.webedu.hourregistration.model;

import java.util.Date;

public class ContractModel {
    private int id;
    private Date startDate;
    private Date endDate;
    private int minHours;
    private int maxHours;

    public ContractModel() {
    }

    public ContractModel(Date startDate, Date endDate, int minHours, int maxHours) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.minHours = minHours;
        this.maxHours = maxHours;
    }

    public int getId() {
        return id;
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
