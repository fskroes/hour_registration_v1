package nl.webedu.hourregistration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.webedu.hourregistration.database.DatabaseRowMapper;
import nl.webedu.hourregistration.enumeration.Role;
import org.bson.Document;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeModel extends DatabaseRowMapper<EmployeeModel> implements Serializable {

    @JsonProperty("_id")
    private String _id;
    private String email, password, firstname, suffix, lastname;
    private Role role;
    private ContractModel contractModel;
    private List<ProjectModel> projectModels;
    private List<String> workday_ids;

    /**
     * Empty contructor, for test purposes only
     */
    public EmployeeModel() {

    }

    public EmployeeModel(
            String email,
            String firstname,
            String suffix,
            String lastname)
    {
        this.email = email;
//        this.password = password;
//        this.role = role;
        this.firstname = firstname;
        this.suffix = suffix;
        this.lastname = lastname;
    }

    public String get_id() {
        return _id;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRole(int roleId) {
        switch (roleId) {
            case 1: this.role = Role.ADMIN;
            case 2: this.role = Role.EMPLOYEE;
            case 3: this.role = Role.MANAGER;

        }
    }

    public ContractModel getContractModel() {
        return contractModel;
    }

    public void setContractModel(ContractModel contractModel) {
        this.contractModel = contractModel;
    }

    public List<ProjectModel> getProjectModels() {
        return new ArrayList<>(projectModels);
    }

    public void setProjectModels(List<ProjectModel> projectModels) {
        this.projectModels = projectModels;
    }

    public void addProject(ProjectModel projectModel) {
        this.projectModels.add(projectModel);
    }

    public void removeProject(ProjectModel projectModel) {
        this.projectModels.remove(projectModel);
    }

    public List<String> getWorkday_Ids() {
        return new ArrayList<>(workday_ids);
    }

    public void setWorkdayModels(List<String> workdayModels) {
        this.workday_ids = workdayModels;
    }

    public void addWorkday(String workdayModel) {
        this.workday_ids.add(workdayModel);
    }

    public void removeWorkday(String workdayModel) {
        this.workday_ids.remove(workdayModel);
    }

    @Override
    public String toString() {
        return firstname + " " + suffix + " " + lastname;
    }

    @Override
    public EmployeeModel convertSQL(ResultSet set, int rowNum) throws SQLException {
        this._id = String.valueOf(set.getInt("employeeID"));
        this.email = set.getString("email");
        this.password = set.getString("password");
        this.firstname = set.getString("firstname");
        this.suffix = set.getString("suffix");
        this.lastname = set.getString("lastname");
        return this;
    }

    @Override
    public EmployeeModel convertMongo(Document set) {
//        this._id = String.valueOf(set.ifPresent(e -> e.getObjectId("_id")));
        this._id = set.getObjectId("_id").toString();
        this.email = set.getString("email");
        this.password = set.getString("password");
        this.password = set.getString("firstname");
        this.password = set.getString("suffix");
        this.password = set.getString("lastname");
        return this;
    }
}