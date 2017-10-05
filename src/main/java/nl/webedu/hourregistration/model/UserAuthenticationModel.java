package nl.webedu.hourregistration.model;

import java.io.Serializable;

public class UserAuthenticationModel implements Serializable {

    private String email;
    private String password;

    public UserAuthenticationModel(String email, String password) {
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
    } // TODO TEST PURPUSE

    public void setPassword(String password) {
        this.password = password;
    }
}
