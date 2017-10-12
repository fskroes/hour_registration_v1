package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.ReportModel;

import java.util.Collection;

public interface IReportDAO {
    public boolean insertReport(ReportModel Report);
    public boolean deleteReport(String id);
    public ReportModel findReport(String id);
    public boolean updateReport(ReportModel Report);
    public ReportModel selectReportByCustomer(String customerId);
}
