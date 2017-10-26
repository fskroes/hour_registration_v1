package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.LogModel;
import nl.webedu.hourregistration.model.SubjectModel;

import java.util.Collection;

public interface ILogDAO {
    public boolean insertLog(LogModel Log);
    public boolean deleteLog(String id);
    public LogModel findLog(String id);
    public boolean updateLog(LogModel Log);
    public Collection<LogModel> selectLogByEmployee(EmployeeModel employee);
    public Collection<LogModel> selectLogBySubject(SubjectModel subject);

}
