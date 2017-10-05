package nl.webedu.hourregistration.model;

import java.util.Date;

public class SubjectModel {

    private int id;
    private String onderwerpName;
    private Date startDate;
    private Date endDate;
    private ProjectModel projectModel;

    public SubjectModel() {
    }

    public SubjectModel(String onderwerpName, Date startDate, Date endDate, ProjectModel projectModel) {
        this.onderwerpName = onderwerpName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectModel = projectModel;
    }

    public int getId() {
        return id;
    }

    public String getOnderwerpName() {
        return onderwerpName;
    }

    public void setOnderwerpName(String onderwerpName) {
        this.onderwerpName = onderwerpName;
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

    public ProjectModel getProjectModel() {
        return projectModel;
    }

    public void setProjectModel(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }
}
