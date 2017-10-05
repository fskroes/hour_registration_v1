package nl.webedu.hourregistration.model;

public class CustomerModel {
    private int id;
    private String businessName;
    private ProjectModel projectModel;

    public CustomerModel() {
    }

    public CustomerModel(String businessName, ProjectModel projectModel) {
        this.businessName = businessName;
        this.projectModel = projectModel;
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

    public ProjectModel getProjectModel() {
        return projectModel;
    }

    public void setProjectModel(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }
}
