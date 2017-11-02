package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.UserAuthenticationModel;

public interface IUserAuthenticationDAO {
    /**
     * find a user by his/her email
     * @param email
     * @return
     */
    UserAuthenticationModel findUser(String email);
    void registerUser(String username, String password, String firstname, String suffix, String lastname);
    boolean authenticateUser(String email, String password);

    /**
     * find an employee by email in the database
     * @param email
     * @return EmployeeModel
     */
    EmployeeModel findEmployee(String email);
}