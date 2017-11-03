package nl.webedu.hourregistration.model;

import nl.webedu.hourregistration.database.DatabaseRowMapper;
import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

/**
 * Model van een report
 * met alle getters en setters
 */
public class ReportModel extends DatabaseRowMapper<ReportModel> {

    // TODO: Fill in RowMappers

    private String id;
    private ArrayList<WorkdayModel> workDays;
    private int weekNumber;
    private Date reportDate;
    private Date endDate;

    public ReportModel() {
        type = ReportModel.class;
    }

    public ReportModel(String id, ArrayList<WorkdayModel> workDays, int weekNumber, Date reportDate, Date endDate) {
        this(workDays, weekNumber, reportDate, endDate);
        this.id = id;
    }

    public ReportModel(ArrayList<WorkdayModel> workDays, int weekNumber, Date reportDate, Date endDate) {
        this.workDays = workDays;
        this.weekNumber = weekNumber;
        this.reportDate = reportDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
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

    public Date getReportEndDate(){ return endDate; }

    public void setReportEndDate(Date endDate){ this.endDate = endDate;}

    @Override
    public ReportModel convertSQL(ResultSet set, int rowNum) throws SQLException {
        // TODO: Setup MariaDB config
        return this;
    }

    @Override
    public ReportModel convertMongo(Document set) {
        // TODO: Setup MongoDB config
        return this;
    }
}
