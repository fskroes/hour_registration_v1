package nl.webedu.hourregistration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UserAuthenticationModel implements Serializable {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
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

    public void setPassword(String password) {
        this.password = password;
    }
}
