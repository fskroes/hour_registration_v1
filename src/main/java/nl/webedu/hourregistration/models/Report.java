package nl.webedu.hourregistration.models;

import java.util.Date;

public class Report {
    private int workDays;
    private int weekNumber;
    private Date reportDate;

    public Report(int workDays, int weekNumber, Date reportDate) {
        this.workDays = workDays;
        this.weekNumber = weekNumber;
        this.reportDate = reportDate;
    }

    public int getWorkDays() {
        return workDays;
    }

    public void setWorkDays(int workDays) {
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
