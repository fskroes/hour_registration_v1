package nl.webedu.hourregistration.model;

import nl.webedu.hourregistration.database.DatabaseRowMapper;
import nl.webedu.hourregistration.enumeration.Role;
import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeModel extends DatabaseRowMapper<EmployeeModel> {

    private String id;
    private String email, password, firstname, suffix, lastname;
    private Role role;
    private ContractModel contractModel;
    private List<ProjectModel> projects;
    private List<WorkdayModel> workdays;

    /**
     * Empty contructor, for test purposes only
     */
    public EmployeeModel() {
        type = EmployeeModel.class;
    }

    public EmployeeModel(String id, String email, String password, String firstname, String suffix, String lastname) {
        this(email, password, firstname, suffix, lastname);
        this.id = id;
    }

    public EmployeeModel(String email, String password, String firstname, String suffix, String lastname) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstname = firstname;
        this.suffix = suffix;
        this.lastname = lastname;
    }

    public String getId() {
        return id;
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
        if (role != null) return role;

        setRole(2);
        return Optional.ofNullable(role)
                .filter(e -> e != null).orElse(getRole());
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRole(int roleId) {
        switch (roleId) {
            case 1:
                this.role = Role.ADMIN;
                break;
            case 2:
                this.role = Role.EMPLOYEE;
                break;
            case 3:
                this.role = Role.MANAGER;
                break;

        }
    }

    public ContractModel getContractModel() {
        return contractModel;
    }

    public void setContractModel(ContractModel contractModel) {
        this.contractModel = contractModel;
    }

    public List<ProjectModel> getProjects() {
        return new ArrayList<>(projects);
    }

    public void setProjects(List<ProjectModel> projects) {
        this.projects = projects;
    }

    public void addProject(ProjectModel projectModel) {
        this.projects.add(projectModel);
    }

    public void removeProject(ProjectModel projectModel) {
        this.projects.remove(projectModel);
    }

    public List<WorkdayModel> getWorkdays() {
        return new ArrayList<>(workdays);
    }

    public void setWorkdays(List<WorkdayModel> workdays) {
        this.workdays = workdays;
    }

    public void addWorkday(WorkdayModel workdayModel) {
        this.workdays.add(workdayModel);
    }

    public void removeWorkday(WorkdayModel workdayModel) {
        this.workdays.remove(workdayModel);
    }

    @Override
    public String toString() {
        return firstname + " " + suffix + " " + lastname;
    }

    @Override
    public EmployeeModel convertSQL(ResultSet set, int rowNum) throws SQLException {
        this.id = String.valueOf(set.getInt("employeeID"));
        this.email = set.getString("email");
        this.password = set.getString("password");
        this.firstname = set.getString("firstname");
        this.suffix = set.getString("suffix");
        this.lastname = set.getString("lastname");
        int test = set.getInt("role");
        this.setRole(set.getInt("role"));
        return this;
    }

    @Override
    public EmployeeModel convertMongo(Document set) {
        this.id = set.getObjectId("_id").toString();
        this.email = set.getString("email");
        this.password = set.getString("password");
        this.firstname = set.getString("firstname");
        this.suffix = set.getString("suffix");
        this.lastname = set.getString("lastname");
        this.setRole(set.getInteger("role"));
        return this;
    }
}