package nl.webedu.hourregistration.model;

public class Customer {
    private int id;
    private String businessName;
    private Project project;

    public Customer() {
    }

    public Customer(String businessName, Project project) {
        this.businessName = businessName;
        this.project = project;
    }

    public int getId() {
        return id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
