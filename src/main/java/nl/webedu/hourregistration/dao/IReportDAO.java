package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.CustomerModel;
import nl.webedu.hourregistration.model.ReportModel;

import java.util.List;

public interface IReportDAO {
    public boolean insertReport(ReportModel report);
    public int deleteReport(ReportModel report);
    public ReportModel findReport(String id);
    public int updateReport(ReportModel report);
    public List<ReportModel> selectAllReports();
    public ReportModel selectReportByCustomer(CustomerModel customer);
}
