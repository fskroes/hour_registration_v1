package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.CustomerModel;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.ReportModel;

import java.util.List;

public interface IReportDAO {
    /**
     * insert a new report
     * @param report
     * @return boolean - done or faulted
     */
    public boolean insertReport(ReportModel report);

    /**
     * delete a project
     * @param report
     * @return int
     */
    public int deleteReport(ReportModel report);

    /**
     * find a specific report in the database by id
     * @param id
     * @return int
     */
    public ReportModel findReport(String id);

    /**
     * update a an existing report
     * @param report
     * @return int
     */
    public int updateReport(ReportModel report);

    /**
     * get all reports in the database
     * @return List<ReportModel>
     */
    public List<ReportModel> selectAllReports();

    /**
     * get all projects by a specific employee
     * @param employee
     * @return List<ReportModel>
     */
    public List<ReportModel> selectReportByEmployee(EmployeeModel employee);
}
