package nl.webedu.hourregistration.model;

import java.util.Date;

public class Project {

    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private Customer customer;
    private String categorie;

    public Project() {
    }

    public Project(String name, Date startDate, Date endDate, Customer customer, String categorie) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.categorie = categorie;
    }

    public int getId() {
        return id;
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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
