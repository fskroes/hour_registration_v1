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
    private Optional<String> email, password, firstname, suffix, lastname;
    private Optional<Role> role;
    private ContractModel contractModel;
    private List<ProjectModel> projectModels;
    private List<String> workday_ids;

    /**
     * Empty contructor, for test purposes only
     */
    public EmployeeModel() {

    }

    public EmployeeModel(
            Optional<String> email,
            Optional<String> firstname,
            Optional<String> suffix,
            Optional<String> lastname)
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

    public Optional<String> getEmail() {
        return email;
    }

    public void setEmail(Optional<String> email) {
        this.email = email;
    }

    public Optional<String> getPassword() {
        return password;
    }

    public void setPassword(Optional<String> password) {
        this.password = password;
    }

    public Optional<String> getFirstname() {
        return firstname;
    }

    public void setFirstname(Optional<String> firstname) {
        this.firstname = firstname;
    }

    public Optional<String> getSuffix() {
        return suffix;
    }

    public void setSuffix(Optional<String> suffix) {
        this.suffix = suffix;
    }

    public Optional<String> getLastname() {
        return lastname;
    }

    public void setLastname(Optional<String> lastname) {
        this.lastname = lastname;
    }

    public Optional<Role> getRole() {
        return role;
    }

    public void setRole(Optional<Role> role) {
        this.role = role;
    }

    public void setRole(int roleId) {
        switch (roleId) {
            case 1: this.role = Optional.of(Role.ADMIN);
            case 2: this.role = Optional.of(Role.EMPLOYEE);
            case 3: this.role = Optional.of(Role.MANAGER);

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
        this.email = Optional.ofNullable(set.getString("email"));
        this.password = Optional.ofNullable(set.getString("password"));
        this.firstname = Optional.ofNullable(set.getString("firstname"));
        this.suffix = Optional.ofNullable(set.getString("suffix"));
        this.lastname = Optional.ofNullable(set.getString("lastname"));
        return this;
    }

    @Override
    public EmployeeModel convertMongo(Optional<Document> set) {

//        this._id = String.valueOf(set.ifPresent(e -> e.getObjectId("_id")));
        this._id = String.valueOf(set.get().getObjectId("_id"));
        this.email = Optional.of(set.get().getString("email"));
        this.password = Optional.of(set.get().getString("password"));
        this.password = Optional.of(set.get().getString("firstname"));
        this.password = Optional.of(set.get().getString("suffix"));
        this.password = Optional.of(set.get().getString("lastname"));
        return this;
    }
}