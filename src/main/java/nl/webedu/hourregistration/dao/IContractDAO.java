package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.ContractModel;

import java.util.List;

public interface IContractDAO {
    public boolean insertContract(ContractModel contract);
    public ContractModel findContract(String id);
    public boolean deleteContract(String id);
    public boolean updateContract(ContractModel contract);
    public List<ContractModel> selectContractByEmployee(int employeeId);
}
