package nl.webedu.hourregistration.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {

    private int id;
    private String email, password, firstname, suffix, lastname;
    private ContractModel contractModel;
    private List<ProjectModel> projectModels;
    private List<WorkdayModel> workdayModels;

    /**
     * Empty contructor, for test purposes only
     */
    public EmployeeModel() {

    }

    public EmployeeModel(int id, String email, String password, String firstname, String suffix, String lastname, List<WorkdayModel> workdayModels) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.suffix = suffix;
        this.lastname = lastname;
        this.workdayModels = workdayModels;
    }

    public int getId() {
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

    public List<WorkdayModel> getWorkdayModels() {
        return new ArrayList<>(workdayModels);
    }

    public void setWorkdayModels(List<WorkdayModel> workdayModels) {
        this.workdayModels = workdayModels;
    }

    public void addWorkday(WorkdayModel workdayModel) {
        this.workdayModels.add(workdayModel);
    }

    public void removeWorkday(WorkdayModel workdayModel) {
        this.workdayModels.remove(workdayModel);
    }

    @Override
    public String toString() {
        return firstname + " " + suffix + " " + lastname;
    }

}