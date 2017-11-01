package nl.webedu.hourregistration.model;

import nl.webedu.hourregistration.database.DatabaseRowMapper;
import org.bson.Document;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerModel extends DatabaseRowMapper<CustomerModel> {

    private String _id;
    private String businessName;
    private ProjectModel projectModel;

    public CustomerModel() {
        type = CustomerModel.class;
    }

    public CustomerModel(String businessName) {
        this.businessName = businessName;
    }

    public CustomerModel(String id, String businessName) {
        this(businessName);
        this._id = id;
    }

    public String getId() {
        return _id;
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

    @Override
    public CustomerModel convertSQL(ResultSet set, int rowNum) throws SQLException {
        this._id = String.valueOf("customerID");
        this.businessName = set.getString("company_name");
        return this;
    }

    @Override
    public CustomerModel convertMongo(Document document) {
        this._id = document.getString("_id");
        this.businessName = document.getString("company_name");
        return this;
    }
}
