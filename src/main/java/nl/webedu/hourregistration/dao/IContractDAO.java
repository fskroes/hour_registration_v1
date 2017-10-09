package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.ContractModel;

public interface IContractDAO {
    public boolean insertContract(ContractModel contract);
    public ContractModel findContract(int id);
    public boolean deleteContract(int id);
    public boolean updateContract(ContractModel contract);
    public ContractModel selectContractByEmployee(int employeeId);
}
