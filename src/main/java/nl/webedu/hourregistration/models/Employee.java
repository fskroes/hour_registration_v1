package nl.webedu.hourregistration.models;

public class Employee {
    private String name;
    private Contract contract;
    private Department department;
    private String email;
    private String password;
    private Report report;
    private boolean activeUser;
    private Role rol;

    public Employee(String name, Contract contract, Department department, String email, String password, Report report, boolean activeUser, Role rol) {
        this.name = name;
        this.contract = contract;
        this.department = department;
        this.email = email;
        this.password = password;
        this.report = report;
        this.activeUser = activeUser;
        this.rol = rol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public boolean isActiveUser() {
        return activeUser;
    }

    public void setActiveUser(boolean activeUser) {
        this.activeUser = activeUser;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }
}
