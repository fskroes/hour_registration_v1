package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.SubjectModel;

import java.util.Collection;

public interface ISubjectDAO {
    public boolean insertSubject(SubjectModel Subject);
    public SubjectModel findSubject(int id);
    public boolean deleteSubject(int id);
    public boolean updateSubject(SubjectModel Subject);
}
