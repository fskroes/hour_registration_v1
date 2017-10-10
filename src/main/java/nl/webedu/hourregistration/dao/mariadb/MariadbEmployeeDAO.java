package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IEmployeeDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.EmployeeModel;

import java.util.Collection;

public class MariadbEmployeeDAO implements IEmployeeDAO {

    private MariaDatabaseExtension database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();

    private MariadbEmployeeDAO() {
        this.database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();
    }

    @Override
    public boolean insertEmployee(EmployeeModel employee){

        return false;
    }

    @Override
    public boolean deleteEmployee(int id){

        return false;
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
    public EmployeeModel selectEmployeesByProject(int projectId){

        EmployeeModel employee = database.selectObjectSingle(Employee, "SELECT * FROM employee WHERE name = ?", "");
        return employee;
    }
}
