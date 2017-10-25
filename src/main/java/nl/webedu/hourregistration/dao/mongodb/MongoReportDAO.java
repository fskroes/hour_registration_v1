package nl.webedu.hourregistration.dao.mongodb;

import nl.webedu.hourregistration.dao.IReportDAO;
import nl.webedu.hourregistration.model.CustomerModel;
import nl.webedu.hourregistration.model.ReportModel;

import java.util.List;

public class MongoReportDAO implements IReportDAO {

    private static MongoReportDAO instance;

    public static MongoReportDAO getInstance() {
        if (instance == null)
            instance = new MongoReportDAO();
        return instance;
    }

    @Override
    public boolean insertReport(ReportModel report) {
        return false;
    }

    @Override
    public int deleteReport(ReportModel report) {
        return 0;
    }

    @Override
    public ReportModel findReport(String id) {
        return null;
    }

    @Override
    public int updateReport(ReportModel report) {
        return 0;
    }

    @Override
    public List<ReportModel> selectAllReports() {
        return null;
    }

    @Override
    public ReportModel selectReportByCustomer(CustomerModel customer) {
        return null;
    }
}
