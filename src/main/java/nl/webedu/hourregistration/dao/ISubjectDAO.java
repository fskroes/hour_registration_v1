package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.ProjectModel;
import nl.webedu.hourregistration.model.SubjectModel;

import java.util.List;

public interface ISubjectDAO {
    public boolean insertSubject(SubjectModel subject);
    public SubjectModel findSubject(String id);
    public int deleteSubject(SubjectModel subject);
    public int updateSubject(SubjectModel subject);
    public List<SubjectModel> selectAllSubject();
    public List<SubjectModel> selectSubjectByPorject(ProjectModel project);
}
