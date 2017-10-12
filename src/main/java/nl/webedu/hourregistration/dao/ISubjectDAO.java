package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.SubjectModel;

import java.util.Collection;

public interface ISubjectDAO {
    public boolean insertSubject(SubjectModel Subject);
    public SubjectModel findSubject(String id);
    public boolean deleteSubject(String id);
    public boolean updateSubject(SubjectModel Subject);
}
