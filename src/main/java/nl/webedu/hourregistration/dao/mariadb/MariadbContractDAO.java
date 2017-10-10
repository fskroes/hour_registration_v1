package nl.webedu.hourregistration.dao.mariadb;

import nl.webedu.hourregistration.dao.IContractDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.ContractModel;

public class MariadbContractDAO implements IContractDAO {


    private MariadbContractDAO() {

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

        return null;
    }

}
