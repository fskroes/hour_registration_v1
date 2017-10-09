package nl.webedu.hourregistration.model;

import java.util.ArrayList;
import java.util.Date;

public class ReportModel {

    private ArrayList<WorkdayModel> workDays;
    private int weekNumber;
    private Date reportDate;

    public ReportModel() {
    }

    public ReportModel(ArrayList<WorkdayModel> workDays, int weekNumber, Date reportDate) {
        this.workDays = workDays;
        this.weekNumber = weekNumber;
        this.reportDate = reportDate;
    }

    public ArrayList<WorkdayModel> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(ArrayList<WorkdayModel> workDays) {
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
