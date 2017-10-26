package nl.webedu.hourregistration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.webedu.hourregistration.database.DatabaseRowMapper;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAuthenticationModel extends DatabaseRowMapper<UserAuthenticationModel> implements Serializable {

    @JsonProperty("_id")
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
        // empty constructor for init
    }

    public UserAuthenticationModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public UserAuthenticationModel convertSQL(ResultSet set, int rowNum) throws SQLException {
        this.email = set.getString("email");
        this.password = set.getString("password");
        return this;
    }

    @Override
    public UserAuthenticationModel convertMongo(Document document) throws SQLException {
        return null;
    }
}

