package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IContractDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.MariaDatabaseExtension;
import nl.webedu.hourregistration.model.ContractModel;

public class MariadbContractDAO implements IContractDAO {

    private MariaDatabaseExtension database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();

    private MariadbContractDAO() {

        this.database = (MariaDatabaseExtension) DatabaseManager.getInstance().getDatabase();
    }

    @Override
    public boolean insertContract(ContractModel contract){

        return false;
    }

    @Override
    public ContractModel findContract(int id){

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
        ContractModel contract = database.selectObjectSingle(Contract, "SELECT * FROM contract WHERE name = ?", "");
        return contract;
    }

}
