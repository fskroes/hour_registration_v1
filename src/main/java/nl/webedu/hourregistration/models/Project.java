package nl.webedu.hourregistration.models;

import java.util.Date;

public class Project {
    private String sprint;
    private String name;
    private Date startDate;
    private Date endDate;
    private Customer customer;
    private String category;

    public Project(String sprint, String name, Date startDate, Date endDate, Customer customer, String category) {
        this.sprint = sprint;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.category = category;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
