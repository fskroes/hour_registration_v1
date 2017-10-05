package nl.webedu.hourregistration.model;

import java.util.ArrayList;
import java.util.Date;

public class Report {

    private ArrayList<Workday> workDays;
    private int weekNumber;
    private Date reportDate;

    public Report() {
    }

    public Report(ArrayList<Workday> workDays, int weekNumber, Date reportDate) {
        this.workDays = workDays;
        this.weekNumber = weekNumber;
        this.reportDate = reportDate;
    }

    public ArrayList<Workday> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(ArrayList<Workday> workDays) {
        this.workDays = workDays;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }
}
