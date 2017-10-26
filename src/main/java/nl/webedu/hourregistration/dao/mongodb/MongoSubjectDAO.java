package nl.webedu.hourregistration.dao.mongodb;

import nl.webedu.hourregistration.dao.ISubjectDAO;
import nl.webedu.hourregistration.model.ProjectModel;
import nl.webedu.hourregistration.model.SubjectModel;

import java.util.List;

public class MongoSubjectDAO implements ISubjectDAO {

    private static MongoSubjectDAO instance;

    public static MongoSubjectDAO getInstance() {
        if (instance == null)
            instance = new MongoSubjectDAO();
        return instance;
    }

    @Override
    public boolean insertSubject(SubjectModel subject) {
        return false;
    }

    @Override
    public SubjectModel findSubject(String id) {
        return null;
    }

    @Override
    public int deleteSubject(SubjectModel subject) {
        return 0;
    }

    @Override
    public int updateSubject(SubjectModel subject) {
        return 0;
    }

    @Override
    public List<SubjectModel> selectAllSubject() {
        return null;
    }

    @Override
    public List<SubjectModel> selectSubjectByProject(ProjectModel project) {
        return null;
    }
}
