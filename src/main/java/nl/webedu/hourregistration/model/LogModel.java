package nl.webedu.hourregistration.model;

import java.util.Date;

public class LogModel {

    private String id;
    private EmployeeModel employeeModel;
    private Date date;
    private String description;

    public LogModel() {
    }

    public LogModel(EmployeeModel employeeModel, Date date, String description) {
        this.employeeModel = employeeModel;
        this.date = date;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public EmployeeModel getEmployeeModel() {
        return employeeModel;
    }

    public void setEmployeeModel(EmployeeModel employeeModel) {
        this.employeeModel = employeeModel;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
