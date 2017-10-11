package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IReportDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ReportModel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MariadbReportDAO implements IReportDAO {

    private static MariadbReportDAO instance;
    private MariaDatabaseExtension client;

    private MariadbReportDAO() {
        this.client = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase().getConnection();
    }

    public static MariadbReportDAO getInstance() {
        if (instance == null) {
            instance = new MariadbReportDAO();
        }
        return instance;
    }

    @Override
    public boolean insertReport(ReportModel report) {
        try {
            String query = "INSERT INTO report"
                    + "(create_date, end_date, week_number) VALUES"
                    + "(?,?,?)";

            PreparedStatement ps = client.openConnection().prepareStatement(query);
            // TODO ps invullen, tabel Report aanpassen op Model!
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
    public boolean deleteReport(int id) {
        return false;
    };

    @Override
    public ReportModel findReport(int id) {
        return null;
    };

    @Override
    public boolean updateReport(ReportModel Report) {
        return false;
    };

    @Override
    public ReportModel selectReportByCustomer(int customerId) {
        ReportModel report = null;
        try {
            report = client.selectObjectSingle(new ReportModel(), "SELECT * FROM report WHERE id = ?", customerId + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    };
}
