package nl.webedu.hourregistration.dao.mongodb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoCollection;
import nl.webedu.hourregistration.dao.ICustomerDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.CustomerModel;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.mongodb.client.model.Filters.eq;
import static nl.webedu.hourregistration.database.DatabaseUtil.CUSTOMER_COLLECTION;
import static nl.webedu.hourregistration.database.DatabaseUtil.DATABASE_NAME;

public class MongoCustomerDAO implements ICustomerDAO {

    private static MongoCustomerDAO instance;

    private MongoClient client;
    private CustomerModel model;
    private ObjectMapper mapper = new ObjectMapper();


    public MongoCustomerDAO() {
        this.client = (MongoClient) DatabaseManager.getInstance().getDatabase().getConnection();
    }

    public static MongoCustomerDAO getInstance() {
        if (instance == null)
            instance = new MongoCustomerDAO();
        return instance;
    }


    @Override
    public boolean insertCustomer(CustomerModel customer) {
        return false;
    }

    @Override
    public boolean deleteCustomer(CustomerModel customer) {
        return false;
    }

    @Override
    public CustomerModel findCustomer(int id) {
        return null;
    }

    @Override
    public ArrayList<CustomerModel> findCustomer(int id, String customerName) {
        model = new CustomerModel();
        ArrayList<CustomerModel> models = new ArrayList<CustomerModel>();
        MongoCollection<Document> collection = client.getDatabase(DATABASE_NAME).getCollection(CUSTOMER_COLLECTION);
        collection.find(eq("customer_name", customerName))
                .into(new ArrayList<Document>(), new SingleResultCallback<ArrayList<Document>>() {
                    @Override
                    public void onResult(ArrayList<Document> documents, Throwable throwable) {
                        for (Document document : documents) {
                            System.out.println(document.toJson());

                            try {
                                model = mapper.readValue(document.toJson(), CustomerModel.class);
                                models.add(model);
                                //
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                return models;
    }

    @Override
    public CustomerModel findCustomer(String name) {
        return null;
    }

    @Override
    public boolean updateCustomer(CustomerModel customer) {
        return false;
    }

    @Override
    public Collection<CustomerModel> selectCustomersByProject(int projectId) {
        return null;
    }
}
