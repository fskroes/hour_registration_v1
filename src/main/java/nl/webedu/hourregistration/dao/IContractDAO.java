package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.ContractModel;
import nl.webedu.hourregistration.model.EmployeeModel;

import java.util.List;

public interface IContractDAO {

    /**
     * Insert a contract in the database
     * @param contract
     * @return
     */
    public int insertContract(ContractModel contract);

    /**
     * find a contract by employee id
     * @param id
     * @return boolean
     */
    public ContractModel findContract(String id);

    /**
     * delete contract by a contract
     * @param contract
     * @return int
     */
    public int deleteContract(ContractModel contract);

    /**
     * Update a existing contract by giving a contract
     * @param contract
     * @return int
     */
    public int updateContract(ContractModel contract);

    /**
     * Get all contract from the database
     * @return List<ContractModel>
     */
    public List<ContractModel> selectAllContracts();

    /**
     * Get a contract from an employee
     * @param employee
     * @return ContractModel
     */
    public ContractModel selectContractByEmployee(EmployeeModel employee);
}
