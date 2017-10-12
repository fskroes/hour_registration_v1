package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IReportDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ReportModel;

import java.sql.SQLException;

public class MariadbReportDAO implements IReportDAO {

    private static MariadbReportDAO instance;
    private MariaDatabaseExtension client;

    private MariadbReportDAO() {
        this.client = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase().getConnection();
    }

    public static MariadbReportDAO getInstance() {
        if (instance == null) {
            instance = new MariadbReportDAO();
        }
        return instance;
    }

    @Override
    public boolean insertProject(ReportModel Report) {
        return false;
    };

    @Override
    public boolean deleteReport(String id) {
        return false;
    };

    @Override
    public ReportModel findReport(String id) {
        return null;
    };

    @Override
    public boolean updateReport(ReportModel Report) {
        return false;
    };

    @Override
    public ReportModel selectReportByCustomer(String customerId) {
        ReportModel report = null;
        try {
            report = client.selectObjectSingle(new ReportModel(), "SELECT * FROM report WHERE id = ?", customerId + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    };
}
