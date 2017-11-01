package nl.webedu.hourregistration.model;

import nl.webedu.hourregistration.database.DatabaseRowMapper;
import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ProjectModel extends DatabaseRowMapper<ProjectModel> {

    private String id;
    private String name;
    private Date startDate;
    private Date endDate;
    private CustomerModel customerModel;

    public ProjectModel() {
        type = ProjectModel.class;
    }

    public ProjectModel(String id, String name, Date startDate, Date endDate) {
        this(name, startDate, endDate);
        this.id = id;
    }

    public ProjectModel(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
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


    @Override
    public ProjectModel convertSQL(ResultSet set, int rowNum) throws SQLException {
        this.id = String.valueOf(set.getInt("projectID"));
        this.name = set.getString("project_name");
        this.startDate = set.getDate("start_date");
        this.endDate = set.getDate("end_date");
        return this;
    }

    @Override
    public ProjectModel convertMongo(Document set) {
        this.id = set.getString("_id");
        this.name = set.getString("project_name");
        this.startDate = set.getDate("start_date");
        this.endDate = set.getDate("end_date");
        return this;
    }
}
