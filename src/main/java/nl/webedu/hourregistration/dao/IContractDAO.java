package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.ContractModel;

import java.util.List;

public interface IContractDAO {
    public boolean insertContract(ContractModel contract);
    public ContractModel findContract(String id);
    public int deleteContract(ContractModel contract);
    public int updateContract(ContractModel contract);
    public List<ContractModel> selectAllContracts();
    public ContractModel selectContractByEmployee(String employeeId);
}
