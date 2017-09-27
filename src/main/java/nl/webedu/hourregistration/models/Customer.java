package nl.webedu.hourregistration.models;

public class Customer {
    private String businessName;
    private String contactPerson;
    private Project project;
    private String description;

    public Customer(String businessName, String contactPerson, Project project, String description) {
        this.businessName = businessName;
        this.contactPerson = contactPerson;
        this.project = project;
        this.description = description;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
