package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.UserAuthenticationModel;

public interface IUserAuthenticationDAO {
    UserAuthenticationModel findUser(String email);
    void registerUser(String username, String password);
    boolean authenticateUser(String email, String password);
    EmployeeModel findEmployee(String email);
}