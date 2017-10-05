package nl.webedu.hourregistration.model;

public class UserAuthentication {

    private String email;
    private String password;

    public UserAuthentication() {
    }

    public UserAuthentication(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
