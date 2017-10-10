package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IReportDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ReportModel;

public class MariadbReportDAO implements IReportDAO {

    private MariaDatabaseExtension database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();

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
        ReportModel report = database.selectObjectSingle(Report, "SELECT * FROM report WHERE name = ?", "");
        return report;
    };
}
