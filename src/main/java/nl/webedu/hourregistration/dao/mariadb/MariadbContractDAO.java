package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IContractDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ContractModel;

import java.sql.*;

public class MariadbContractDAO implements IContractDAO {

    private static MariadbContractDAO instance;
    private MariaDatabaseExtension client = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();

    private MariadbContractDAO() {
        this.client = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();
    }

    public static MariadbContractDAO getInstance() {
        if (instance == null) {
            instance = new MariadbContractDAO();
        }
        return instance;
    }

    @Override
    public boolean insertContract(ContractModel contract) {
        try {
            String query = "INSERT INTO contract"
                    + "(max_hours, min_hours, start_time, end_time) VALUES"
                    + "(?,?,?,?)";

            PreparedStatement ps = client.openConnection().prepareStatement(query);
            ps.setInt(1, contract.getMaxHours());
            ps.setInt(2, contract.getMinHours());
            ps.setDate(3, (Date) contract.getStartTime());
            ps.setDate(4, (Date) contract.getEndTime());
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
    }

    @Override
    public ContractModel findContract(String id){
        return null;
    }

    @Override
    public boolean deleteContract(int id){
        return false;
    }

    @Override
    public boolean updateContract(ContractModel contract){
        return false;
    }

    @Override
    public ContractModel selectContractByEmployee(int employeeId){
        return null;
    }

}
