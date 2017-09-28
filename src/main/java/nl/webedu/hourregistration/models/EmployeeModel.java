package nl.webedu.hourregistration.models;

public class EmployeeModel {
    private String name;
    private ContractModel contract;
    private DepartmentModel department;
    private String email;
    private String password;
    private ReportModel report;
    private boolean activeUser;
    private RoleModel rol;

    public EmployeeModel(String name, ContractModel contract, DepartmentModel department, String email, String password, ReportModel report, boolean activeUser, RoleModel rol) {
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

    public ContractModel getContract() {
        return contract;
    }

    public void setContract(ContractModel contract) {
        this.contract = contract;
    }

    public DepartmentModel getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentModel department) {
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

    public ReportModel getReport() {
        return report;
    }

    public void setReport(ReportModel report) {
        this.report = report;
    }

    public boolean isActiveUser() {
        return activeUser;
    }

    public void setActiveUser(boolean activeUser) {
        this.activeUser = activeUser;
    }

    public RoleModel getRol() {
        return rol;
    }

    public void setRol(RoleModel rol) {
        this.rol = rol;
    }
}
