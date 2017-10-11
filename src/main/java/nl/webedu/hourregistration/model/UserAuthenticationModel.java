package nl.webedu.hourregistration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAuthenticationModel implements Serializable {

    private ObjectId _id;
    private String email;
    private String password;

    public ObjectId get_id() {
        return _id;
    }

    public String getEmail() { return this.email; }
    public void setEmail(String username) { this.email = username; }
    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }




    public UserAuthenticationModel() {

    }

    public UserAuthenticationModel(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

