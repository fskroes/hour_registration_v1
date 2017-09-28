package nl.webedu.hourregistration.models;

import java.util.Date;

public class LogModel {
    private EmployeeModel employee;
    private Date date;
    private String description;

    public LogModel(EmployeeModel employee, Date date, String description) {
        this.employee = employee;
        this.date = date;
        this.description = description;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
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
