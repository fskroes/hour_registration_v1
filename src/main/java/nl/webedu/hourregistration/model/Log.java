package nl.webedu.hourregistration.model;

import java.util.Date;

public class Log {

    private Employee employee;
    private Date date;
    private String description;

    public Log() {
    }

    public Log(Employee employee, Date date, String description) {
        this.employee = employee;
        this.date = date;
        this.description = description;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
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
