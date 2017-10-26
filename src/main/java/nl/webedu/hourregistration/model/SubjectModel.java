package nl.webedu.hourregistration.model;

import nl.webedu.hourregistration.database.DatabaseRowMapper;
import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class SubjectModel extends DatabaseRowMapper<SubjectModel> {

    // TODO: Fill in RowMappers

    private String id;
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

    public String getId() {
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

    @Override
    public SubjectModel convertSQL(ResultSet set, int rowNum) throws SQLException {
        // TODO: Setup MariaDB config
        return this;
    }

    @Override
    public SubjectModel convertMongo(Document set) {
        // TODO: Setup MongoDB config
        return this;
    }
}
