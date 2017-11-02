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

    /**
     * register a new user with username and password
     * @param username
     * @param password
     */
    void registerUser(String username, String password);

    /**
     * authenticate a existing user in the database
     * @param email
     * @param password
     * @return boolean - done or faulted
     */
    boolean authenticateUser(String email, String password);

    /**
     * find an employee by email in the database
     * @param email
     * @return EmployeeModel
     */
    EmployeeModel findEmployee(String email);
}