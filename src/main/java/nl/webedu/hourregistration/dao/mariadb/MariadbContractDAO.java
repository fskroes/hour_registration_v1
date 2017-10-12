package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IContractDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ContractModel;

import java.sql.*;
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
        try {
            String query = "INSERT INTO contract"
                    + "(max_hours, min_hours, start_time, end_time) VALUES"
                    + "(?,?,?,?)";

            PreparedStatement ps = database.openConnection().prepareStatement(query);
            ps.setInt(1, contract.getMaxHours());
            ps.setInt(2, contract.getMinHours());
            ps.setDate(3, (Date) contract.getStartTime());
            ps.setDate(4, (Date) contract.getEndTime());
            ps.executeQuery();
            ps.close();
            database.closeConnecion();
            System.out.println("Query: " + query + " = Success");

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public ContractModel findContract(String id) {

        ContractModel contract = null;
        try {
            contract = database.selectObjectSingle(new ContractModel(), "SELECT * FROM contract WHERE contractID = ?", id + "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contract;
    }

    @Override
    public boolean deleteContract(String id){
        try {
        String sql = "DELETE contract"
                + " WHERE conractID = ?";


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
    public boolean updateContract(ContractModel contract){
        return false;
    }

    @Override
    public List<ContractModel> selectContractByEmployee(int employeeId){
        List<ContractModel> contract = null;

        try {
            contract = database.selectObjectList(new ContractModel(), "SELECT * FROM contract WHERE employeeID = ?", employeeId);
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
        return contract;
    }
}
