package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.model.ReportModel;

import java.util.Collection;

public class MariadbReportDAO {
    public boolean insertProject(ReportModel Report) {
        return false;
    };
    public boolean deleteReport(int id) {
        return false;
    };
    public ReportModel findReport(int id) {
        return null;
    };
    public boolean updateReport(ReportModel Report) {
        return false;
    };
    public Collection selectReportByCustomer(int customerId) {
        return null;
    };
}
