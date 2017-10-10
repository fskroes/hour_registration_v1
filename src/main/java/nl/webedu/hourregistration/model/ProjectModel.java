package nl.webedu.hourregistration.model;

import java.sql.Date;

public class ProjectModel {

    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private CustomerModel customerModel;
    private String categorie;

    public ProjectModel() {
    }

    public ProjectModel(String name, Date startDate, Date endDate, CustomerModel customerModel, String categorie) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerModel = customerModel;
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

    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
