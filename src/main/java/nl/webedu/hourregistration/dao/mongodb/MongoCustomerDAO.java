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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static nl.webedu.hourregistration.database.DatabaseUtil.CUSTOMER_COLLECTION;
import static nl.webedu.hourregistration.database.DatabaseUtil.DATABASE_NAME;
import static nl.webedu.hourregistration.database.DatabaseUtil.EMPLOYEE_COLLECTION;

public class MongoCustomerDAO implements ICustomerDAO {

    private static MongoCustomerDAO instance;

    private MongoClient client;
    private CustomerModel model;
    ArrayList<Document> alCustomerDocuments = new ArrayList<>();

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
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();
        Document query = new Document("business_name", customer.getBusinessName());

        client.getDatabase(DATABASE_NAME).getCollection(CUSTOMER_COLLECTION)
                .insertOne(query, (result, t) -> completableFuture.complete(true));
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCustomer(CustomerModel customer) {
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();
        Document query = new Document();
        query.put("_id", customer.getId());

        client.getDatabase(DATABASE_NAME).getCollection(CUSTOMER_COLLECTION)
                .deleteOne(query, (deleteResult, throwable) -> completableFuture.complete(true));
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CustomerModel findCustomer(int id) {
        CompletableFuture<CustomerModel> completableFuture = new CompletableFuture<>();
        CustomerModel cm = new CustomerModel();
        client.getDatabase(DATABASE_NAME).getCollection(CUSTOMER_COLLECTION).find(
                eq("_id", id)).first((document, throwable) -> {
            completableFuture.complete(cm.convertMongo(document, 0));
        });
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<CustomerModel> findCustomerByName(String name) {
        CompletableFuture<ArrayList<CustomerModel>> completableFuture = new CompletableFuture<>();
        ArrayList<CustomerModel> alCustomers = new ArrayList<>();
        client.getDatabase(DATABASE_NAME).getCollection(CUSTOMER_COLLECTION).find(
                eq("business_name", name)).into(
                alCustomerDocuments,
                (documents, throwable) -> {
                    for (Document d: alCustomerDocuments) {
                        alCustomers.add(new CustomerModel(d.getString("category")));
                    }
                    completableFuture.complete(alCustomers);
                });
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateCustomer(CustomerModel customer) {
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();
        Document query = new Document();
        query.put("_id", customer.getId());
        client.getDatabase(DATABASE_NAME).getCollection(CUSTOMER_COLLECTION).updateOne(eq("_id", customer.getId())
                , combine(set("business_name", customer.getBusinessName())), (updateResult, throwable) -> {
                    completableFuture.complete(true);
                });
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }
}
