package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.ISubjectDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ProjectModel;
import nl.webedu.hourregistration.model.SubjectModel;

import java.sql.SQLException;
import java.util.List;

public class MariadbSubjectDAO implements ISubjectDAO {

    private static MariadbSubjectDAO instance;
    private MariaDatabaseExtension database;

    private MariadbSubjectDAO() {
        this.database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase().getConnection();
    }

    public static MariadbSubjectDAO getInstance() {
        if (instance == null) {
            instance = new MariadbSubjectDAO();
        }
        return instance;
    }

    @Override
    public boolean insertSubject(SubjectModel subject) {
        String querySQL = "INSERT INTO subject"
                + "(subject_name, start_date, end_date) VALUES"
                + "(?,?,?)";
        try {
            database.insertQuery(querySQL, subject.getOnderwerpName(), subject.getStartDate(), subject.getEndDate());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public SubjectModel findSubject(String id) {
        SubjectModel subject = null;
        try {
            subject = database.selectObjectSingle(new SubjectModel(), "SELECT * FROM project WHERE projectID = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }

    @Override
    public int deleteSubject(SubjectModel subject) {
        int result = 0;
        String querySQL = "DELETE subject"
                + " WHERE subjectID = ?";
        try {
            result = database.deleteQuery(querySQL, subject.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateSubject(SubjectModel subject) {
        int result = 0;
        String updateSQL = "UPDATE subject"
                + " SET subject_name = ?, start_date = ?, end_date = ?"
                + " WHERE subjectID = ?";
        try {
            database.updateQuery(
                    updateSQL,
                    subject.getOnderwerpName(),
                    subject.getStartDate(),
                    subject.getEndDate(),
                    subject.getId()
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<SubjectModel> selectAllSubject() {
        List<SubjectModel> subject = null;
        try {
            subject = database.selectObjectList(new SubjectModel(), "SELECT * FROM subject");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }

    @Override
    public List<SubjectModel> selectSubjectByProject(ProjectModel project) {

        List<SubjectModel> subject = null;
        try {
            subject = database.selectObjectList(new SubjectModel(), "SELECT * FROM subject WHERE projectID = ?", project.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }
}
