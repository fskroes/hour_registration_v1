package nl.webedu.hourregistration.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.webedu.hourregistration.database.DatabaseRowMapper;
import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ProjectModel extends DatabaseRowMapper<ProjectModel> {

    @JsonProperty("_id")
    private String id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String categorie;
    private CustomerModel customerModel;

    public ProjectModel() {
        type = ProjectModel.class;
    }

    public ProjectModel(String id, String name, Date startDate, Date endDate, String categorie) {
        this(name, startDate, endDate, categorie);
        this.id = id;
    }

    public ProjectModel(String name, Date startDate, Date endDate, String categorie) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.categorie = categorie;
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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public ProjectModel convertSQL(ResultSet set, int rowNum) throws SQLException {
        this.id = String.valueOf(set.getInt("projectID"));
        this.name = set.getString("project_name");
        this.startDate = set.getDate("start_date");
        this.endDate = set.getDate("end_date");
        this.categorie = set.getString("category");
        return this;
    }

    @Override
    public ProjectModel convertMongo(Document set) {
        this.id = set.getString("_id");
        this.name = set.getString("project_name");
        this.startDate = set.getDate("start_date");
        this.endDate = set.getDate("end_date");
        this.categorie = set.getString("category");
        return this;
    }
}
