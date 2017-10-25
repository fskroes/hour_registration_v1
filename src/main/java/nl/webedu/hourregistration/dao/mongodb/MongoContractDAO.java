package nl.webedu.hourregistration.dao.mongodb;

import com.mongodb.async.client.MongoClient;
import nl.webedu.hourregistration.dao.IContractDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.DatabaseUtil;
import nl.webedu.hourregistration.model.ContractModel;
import nl.webedu.hourregistration.model.EmployeeModel;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class MongoContractDAO implements IContractDAO {

    private static MongoContractDAO instance;
    private MongoClient client = (MongoClient) DatabaseManager.getInstance().getDatabase().getConnection();

    public static MongoContractDAO getInstance() {
        if (instance == null) {
            instance = new MongoContractDAO();
        }
        return instance;
    }

    @Override
    public boolean insertContract(ContractModel contract) {
        CompletableFuture<Boolean> queryTimer = new CompletableFuture<>();
        Document doc = new Document("max_hours", contract.getMaxHours())
                .append("min_hours", contract.getMinHours())
                .append("start_time", contract.getStartTime())
                .append("end_time", contract.getEndTime());
        client.getDatabase(DatabaseUtil.DATABASE_NAME).getCollection(DatabaseUtil.CONTRACT_COLLECTION).insertOne(
                doc,
                (aVoid, throwable) -> {
                    System.out.println("[DEBUG] {DATABASE} -> {MONGO} Inserted document " + doc.get("_id"));
                    queryTimer.complete(true);

                }
        );
        try {
            return queryTimer.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ContractModel findContract(String id) {
        CompletableFuture<ContractModel> queryTimer = new CompletableFuture<>();
        client.getDatabase(DatabaseUtil.DATABASE_NAME).getCollection(DatabaseUtil.CONTRACT_COLLECTION).find(
                eq("_id", new ObjectId("59dde9480c74011202d9fff9"))
        ).forEach(
                (document) -> queryTimer.complete(new ContractModel().convertMongo(Optional.of(document))),
                (aVoid, throwable) -> System.out.println("Found a record")
        );
        try {
            return queryTimer.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteContract(ContractModel contract) {
        CompletableFuture<Integer> queryTimer = new CompletableFuture<>();
        client.getDatabase(DatabaseUtil.DATABASE_NAME).getCollection(DatabaseUtil.CONTRACT_COLLECTION).deleteOne(
                eq("_id", new ObjectId(String.valueOf(contract.getId()))),
                (deleteResult, throwable) -> {
                    System.out.println("Deleted: " + deleteResult.getDeletedCount());
                    queryTimer.complete((int) deleteResult.getDeletedCount());
                }
        );
        try {
            return queryTimer.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateContract(ContractModel contract) {
        CompletableFuture<Integer> queryTimer = new CompletableFuture<>();
        client.getDatabase(DatabaseUtil.DATABASE_NAME).getCollection(DatabaseUtil.CONTRACT_COLLECTION).updateOne(
                eq("_id", contract.getId()),
                combine(set( "min_hours", contract.getMinHours()),
                        set("max_hours", contract.getMaxHours()),
                        set("start_time", contract.getStartTime()),
                        set("end_time", contract.getEndTime())),
                (updateResult, throwable) -> {
                    System.out.println("Updated: " + updateResult.getModifiedCount());
                    queryTimer.complete((int) updateResult.getModifiedCount());
                });
        try {
            return queryTimer.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<ContractModel> selectAllContracts() {
        return null;
    }

    @Override
    public ContractModel selectContractByEmployee(EmployeeModel employee) {
        return null;
    }
}
