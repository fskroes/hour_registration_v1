package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IEmployeeDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.ProjectModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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
                    +"(email, password, role, firstname, suffix, lastname, active) VALUES"
                    +"(?,?,?,?,?,?,?)";

            PreparedStatement ps = database.openConnection().prepareStatement(sql);

            ps.setString(1, String.valueOf(employee.getEmail()));
            ps.setString(2, String.valueOf(employee.getPassword()));
            ps.setInt(3, 1/*employee.getRole().getIndex()*/);
            ps.setString(4, String.valueOf(employee.getFirstname()));
            ps.setString(5, String.valueOf(employee.getSuffix()));
            ps.setString(6, String.valueOf(employee.getLastname()));
            ps.setBoolean(7, true);

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
    public boolean deleteEmployee(String id){
        try {

            String sql = "UPDATE employee SET active= FALSE WHERE employeeID = ?;";

            PreparedStatement ps = database.openConnection().prepareStatement(sql);

            ps.setString(1, id);

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
    public EmployeeModel findEmployee(String id){
        EmployeeModel employee = null;
        try {
            employee = database.selectObjectSingle(
                    new EmployeeModel(),
                    "SELECT* FROM employee WHERE employeeID = ?;",
                    id
            );
            employee.setProjects(DatabaseManager.getInstance().getDaoFactory().getProjectDAO().selectProjectsByEmployee(employee));
            employee.setWorkdays(DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().selectWorkdaysByEmployee(employee));
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return employee;

    }

    @Override
    public boolean updateEmployee(EmployeeModel employee){
        try {

            String sql = "UPDATE employee SET email = ?, password = ?, role = ?, firstname = ?," +
                    "suffix = ?, lastname = ?  WHERE employeeID = ?;";

            PreparedStatement ps = database.openConnection().prepareStatement(sql);

            ps.setString(1, String.valueOf(employee.getEmail()));
            ps.setString(2, String.valueOf(employee.getPassword()));
            ps.setInt(3, 1/*employee.getRole().getIndex()*/);
            ps.setString(4, String.valueOf(employee.getFirstname()));
            ps.setString(5, String.valueOf(employee.getSuffix()));
            ps.setString(6, String.valueOf(employee.getLastname()));
            ps.setString(7, String.valueOf(employee.getId()));

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
    public List<EmployeeModel> selectAllEmployees() {
        List<EmployeeModel> employees = null;
        try {
            employees = database.selectObjectList(new EmployeeModel(), "SELECT * FROM employee");
            for (EmployeeModel employee : employees) {
                employee.setWorkdays(DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().selectWorkdaysByEmployee(employee));
                employee.setProjects(DatabaseManager.getInstance().getDaoFactory().getProjectDAO().selectProjectsByEmployee(employee));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public List<EmployeeModel> selectEmployeesByProject(ProjectModel project){
        List<EmployeeModel> employees = null;
        try {
            employees = database.selectObjectList(
                    new EmployeeModel(),
                    "SELECT * FROM employee WHERE employeeID = " +
                            "(SELECT fk_employee_id FROM employee_project WHERE fk_project_id = ?);",
                    project.getId()
            );
            for (EmployeeModel employee : employees) {
                employee.setWorkdays(DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().selectWorkdaysByEmployee(employee));
                employee.setProjects(DatabaseManager.getInstance().getDaoFactory().getProjectDAO().selectProjectsByEmployee(employee));
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return employees;
    }
}
