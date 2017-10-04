package nl.webedu.hourregistration.model;

import nl.webedu.hourregistration.enumeration.Role;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private int id;
    private String email, password, firstname, suffix, lastname;
    private Role role;
    private Contract contract;
    private List<Project> projects;

    /**
     * Empty contructor, for test purposes mainly
     */
    public Employee() {

    }

    public Employee(int id, String email, String password, String firstname, String suffix, String lastname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.suffix = suffix;
        this.lastname = lastname;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<Project> getProjects() {
        return new ArrayList<>(projects);
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public void removeProject(Project project) {
        this.projects.add(project);
    }

    @Override
    public String toString() {
        return firstname + " " + suffix + " " + lastname;
    }
}