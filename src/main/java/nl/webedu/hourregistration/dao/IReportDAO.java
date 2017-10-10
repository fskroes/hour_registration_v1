package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.ReportModel;

import java.util.Collection;

public interface IReportDAO {
    public boolean insertProject(ReportModel Report);
    public boolean deleteReport(int id);
    public ReportModel findReport(int id);
    public boolean updateReport(ReportModel Report);
    public ReportModel selectReportByCustomer(int customerId);
}
