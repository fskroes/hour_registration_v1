package nl.webedu.hourregistration.dao;

import nl.webedu.hourregistration.model.UserAuthenticationModel;

public interface IUserAuthenticationDAO {
    public UserAuthenticationModel findUser(String email);
    public void registerUser(String username, String password);
    public boolean authenticateUser(String email, String password);
}