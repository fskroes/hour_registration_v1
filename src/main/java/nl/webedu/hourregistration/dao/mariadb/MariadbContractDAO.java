package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IContractDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ContractModel;
import nl.webedu.hourregistration.model.EmployeeModel;

import java.sql.SQLException;
import java.util.List;

public class MariadbContractDAO implements IContractDAO {

    private static MariadbContractDAO instance;
    private MariaDatabaseExtension database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();

    private MariadbContractDAO() {
        this.database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();
    }

    public static MariadbContractDAO getInstance() {
        if (instance == null) {
            instance = new MariadbContractDAO();
        }
        return instance;
    }

    @Override
    public boolean insertContract(ContractModel contract) {
        String querySQL = "INSERT INTO contract"
                + "(max_hours, min_hours, start_time, end_time) VALUES"
                + "(?,?,?,?)";
        try {
            database.insertQuery(querySQL, contract.getMaxHours(), contract.getMinHours(), contract.getStartTime(), contract.getEndTime());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ContractModel findContract(String id) {

        ContractModel contract = null;
        try {
            contract = database.selectObjectSingle(new ContractModel(), "SELECT * FROM contract WHERE contractID = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contract;
    }

    @Override
    public int deleteContract(ContractModel contract){
        int result = 0;
        String querySQL = "DELETE contract"
                + " WHERE conractID = ?";
        try {
            result = database.deleteQuery(querySQL, contract.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateContract(ContractModel contract){
        int result = 0;
        String updateSQL = "UPDATE contract"
                + " SET max_hours = ?, min_hours = ?, start_time = ?, end_time = ?"
                + " WHERE activityID = ?";
        try {
            result = database.updateQuery(
                    updateSQL,
                    contract.getMaxHours(),
                    contract.getMinHours(),
                    contract.getStartTime(),
                    contract.getEndTime());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<ContractModel> selectAllContracts() {
        List<ContractModel> contract = null;
        try {
            contract = database.selectObjectList(new ContractModel(), "SELECT * FROM contract");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contract;
    }

    @Override
    public ContractModel selectContractByEmployee(EmployeeModel employee){
        ContractModel contract = null;

        try {
            contract = database.selectObjectSingle(new ContractModel(), "SELECT * FROM contract WHERE employeeID = ?", employee.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contract;
    }
}
