package nl.webedu.hourregistration.models;

public class CustomerModel {
    private String businessName;
    private String contactPerson;
    private ProjectModel project;
    private String description;

    public CustomerModel(String businessName, String contactPerson, ProjectModel project, String description) {
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

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
