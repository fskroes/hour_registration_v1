package nl.webedu.hourregistration.dao;

public interface IUserAuthenticationDAO {
    public void findUser(String email, String password);
    public void registerUser(String username, String password);
    public boolean authenticateUser(String email, String password);
}