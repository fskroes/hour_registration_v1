package nl.webedu.hourregistration.daointerfaces;

import nl.webedu.hourregistration.model.UserAuthenticationModel;

public interface IUserAuthenticationDAO {
    public UserAuthenticationModel findUser();
    public UserAuthenticationModel registerUser(String username, String password);
    public void authenticateUser();
}