package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IEmployeeDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.ProjectModel;
import nl.webedu.hourregistration.model.WorkdayModel;

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
        String sql = "INSERT INTO employee"
                + "(email, password, role, firstname, suffix, lastname, active) VALUES"
                + "(?,?,?,?,?,?,?)";
        try {
            database.insertQuery(
                    sql,
                    employee.getEmail(),
                    employee.getPassword(),
                    employee.getRole().getIndex(),
                    employee.getFirstname(),
                    employee.getSuffix(),
                    employee.getLastname(),
                    employee.isActive()
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        for (WorkdayModel workday : employee.getWorkdays()) {
            DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().insertWorkday(workday);
        }
        for (ProjectModel project : employee.getProjects()) {
            DatabaseManager.getInstance().getDaoFactory().getProjectDAO().insertProject(project);
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
        String sql = "UPDATE employee SET email = ?, password = ?, role = ?, firstname = ?," +
                "suffix = ?, lastname = ?, active = ?  WHERE employeeID = ?;";
        try {
            database.updateQuery(
                    sql,
                    employee.getEmail(),
                    employee.getPassword(),
                    employee.getRole().getIndex(),
                    employee.getFirstname(),
                    employee.getSuffix(),
                    employee.getLastname(),
                    employee.isActive(),
                    employee.getId()
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        for (WorkdayModel workday : employee.getWorkdays()) {
            if (DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().findWorkday(workday.getId()) == null) {
                DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().insertWorkday(workday);
                try {
                    database.insertQuery("INSERT INTO employee_workday SET fk_employee_id = ?, fk_workday_id = ?", employee.getId(), workday.getId());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().updateWorkday(workday);
            }
        }
        for (ProjectModel project : employee.getProjects()) {
            if (DatabaseManager.getInstance().getDaoFactory().getProjectDAO().findProject(project.getId()) == null) {
                DatabaseManager.getInstance().getDaoFactory().getProjectDAO().insertProject(project);
            } else {
                DatabaseManager.getInstance().getDaoFactory().getProjectDAO().updateProject(project);
            }
        }
        return true;
    }

    @Override
    public List<EmployeeModel> getAllEmployees() {
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
                    "SELECT * FROM employee INNER JOIN employee_project ON employee.employeeID = employee_project.fk_employee_id where fk_project_id = ?;",
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
