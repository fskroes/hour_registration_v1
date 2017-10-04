package nl.webedu.hourregistration.model;

import java.util.Date;

public class Subject {

    private int id;
    private String onderwerpName;
    private Date startDate;
    private Date endDate;
    private Project project;

    public Subject() {
    }

    public Subject(String onderwerpName, Date startDate, Date endDate, Project project) {
        this.onderwerpName = onderwerpName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.project = project;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
