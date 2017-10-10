package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IReportDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ReportModel;

import java.sql.SQLException;

public class MariadbReportDAO implements IReportDAO {

    private MariaDatabaseExtension client;

    private MariadbReportDAO() {
        this.client = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase().getConnection();
    }

    @Override
    public boolean insertProject(ReportModel Report) {
        return false;
    };

    @Override
    public boolean deleteReport(int id) {
        return false;
    };

    @Override
    public ReportModel findReport(int id) {
        return null;
    };

    @Override
    public boolean updateReport(ReportModel Report) {
        return false;
    };

    @Override
    public ReportModel selectReportByCustomer(int customerId) {
        ReportModel report = null;
        try {
            report = client.selectObjectSingle(new ReportModel(), "SELECT * FROM report WHERE id = ?", customerId + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    };
}
