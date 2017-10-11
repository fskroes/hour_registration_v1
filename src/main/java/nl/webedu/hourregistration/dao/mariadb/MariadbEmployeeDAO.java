package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IEmployeeDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.EmployeeModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class MariadbEmployeeDAO implements IEmployeeDAO {

    private static MariadbEmployeeDAO instance;

    private MariaDatabaseExtension database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();

    private MariadbEmployeeDAO() {
        this.database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();
    }
    public static MariadbEmployeeDAO getInstance() {
        if (instance == null) {
            instance = new MariadbEmployeeDAO();
        }
        return instance;
    }

    @Override
    public boolean insertEmployee(EmployeeModel employee){
        try {
            String sql = "INSERT INTO employee"
                    +"(employeeID, email, password, role, firstname, suffix, lastname, active) VALUES"
                    +"(?,?,?,?,?,?,?,TRUE)";

            PreparedStatement ps = database.openConnection().prepareStatement(sql);

            ps.setString(1, employee.getId());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getPassword());
            ps.setInt(4, employee.getRole().getIndex());
            ps.setString(5, employee.getFirstname());
            ps.setString(6, employee.getSuffix());
            ps.setString(7, employee.getLastname());

            ps.executeUpdate();
            ps.close();
            database.closeConnecion();

            System.out.println("Record toegevoegd");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteEmployee(int id){
        try {

            String sql = "UPDATE employee SET active=False WHERE employeeID = ?;";

            PreparedStatement ps = database.openConnection().prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();
            database.closeConnecion();

            System.out.println("Record toegevoegd");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return true;
    }

    @Override
    public EmployeeModel findEmployee(int id){

        return null;
    }

    @Override
    public boolean updateEmployee(EmployeeModel customer){

        return false;
    }

    @Override
    public Collection selectEmployeesByProject(int projectId){
        return null;
    }
}
