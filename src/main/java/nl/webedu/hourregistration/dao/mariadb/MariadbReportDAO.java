package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IReportDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.CustomerModel;
import nl.webedu.hourregistration.model.ReportModel;

import java.sql.SQLException;
import java.util.List;

public class MariadbReportDAO implements IReportDAO {

    private static MariadbReportDAO instance;
    private MariaDatabaseExtension database;

    private MariadbReportDAO() {
        this.database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase().getConnection();
    }

    public static MariadbReportDAO getInstance() {
        if (instance == null) {
            instance = new MariadbReportDAO();
        }
        return instance;
    }

    @Override
    public boolean insertReport(ReportModel report) {
        String querySQL = "INSERT INTO report"
                + "(create_date, end_date, week_number) VALUES"
                + "(?,?,?)";
        try {
            // TODO MODEL IS NOT IN SYNC WITH DATABASE!!!
            database.insertQuery(querySQL, "MODEL IS NOT IN SYNC WITH DATABASE!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int deleteReport(ReportModel report) {
        int result = 0;
        String querySQL = "DELETE report"
                + " WHERE reportID = ?";
        try {
            result = database.deleteQuery(querySQL, report.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ReportModel findReport(String id) {

        ReportModel report = null;
        try {
            report = database.selectObjectSingle(new ReportModel(), "SELECT * FROM project WHERE projectID = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    }

    @Override
    public int updateReport(ReportModel report) {
        int result = 0;
        String updateSQL = "UPDATE report"
                + " SET create_date = ?, end_date = ?, week_number = ?"
                + " WHERE reportID = ?";
        try {
            database.updateQuery(
                    updateSQL,
                    report.getReportDate(),
                    report.getReportEndDate(),
                    report.getWeekNumber(),
                    report.getId()
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<ReportModel> selectAllReports() {
        List<ReportModel> report = null;
        try {
            report = database.selectObjectList(new ReportModel(), "SELECT * FROM report");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    }

    @Override
    public ReportModel selectReportByCustomer(CustomerModel customer) {
        ReportModel report = null;
        try {
            report = database.selectObjectSingle(new ReportModel(), "SELECT * FROM report WHERE customerID = ?", customer.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    }
}
