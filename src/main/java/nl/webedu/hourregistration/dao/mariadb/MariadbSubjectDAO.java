package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.ISubjectDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.SubjectModel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MariadbSubjectDAO implements ISubjectDAO {

    private static MariadbSubjectDAO instance;
    private MariaDatabaseExtension client;

    private MariadbSubjectDAO() {
        this.client = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase().getConnection();
    }

    public static MariadbSubjectDAO getInstance() {
        if (instance == null) {
            instance = new MariadbSubjectDAO();
        }
        return instance;
    }

    @Override
    public boolean insertSubject(SubjectModel subject) {
        try {
            String query = "INSERT INTO subject"
                    + "(subject_name, start_date, end_date) VALUES"
                    + "(?,?,?)";

            PreparedStatement ps = client.openConnection().prepareStatement(query);
            ps.setString(1, subject.getOnderwerpName());
            ps.setDate(2, (Date) subject.getStartDate());
            ps.setDate(3, (Date) subject.getEndDate());
            ps.executeQuery();
            ps.close();
            client.closeConnecion();
            System.out.println("Query: " + query + " = Succes");

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    };

    @Override
    public SubjectModel findSubject(int id) {
        return null;
    };

    @Override
    public boolean deleteSubject(int id) {
        return false;
    };

    @Override
    public boolean updateSubject(SubjectModel Subject) {
        return false;
    };
}
