package nl.webedu.hourregistration.dao.mongodb;

import com.mongodb.async.client.MongoClient;
import nl.webedu.hourregistration.dao.IActivitiesDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.ActivitiesModel;
import org.bson.Document;

import java.util.Collection;

public class MongoActivitiesDAO implements IActivitiesDAO {

    private MongoClient client;

    private MongoActivitiesDAO() {
        this.client = (MongoClient) DatabaseManager.getInstance().getDatabase();
    }

    @Override
    public boolean insertActivitie(ActivitiesModel activitie) {
        Document query = new Document("category", activitie.getCategory())
                .append("start_time",activitie.getStartTime())
                .append("end_time",activitie.getEndTime())
                .append("workdayId", activitie.getWorkday());

        client.getDatabase("hour_registration").getCollection("activities")
                .insertOne(query, (result, t) -> System.out.println("Documents inserted!"));

        return true;
    }

    @Override
    public ActivitiesModel findActivitie(int id) {
        return null;
    }

    @Override
    public boolean deleteActivitie(int id) {
        return false;
    }

    @Override
    public boolean updateActivitie(ActivitiesModel activitie) {
        return false;
    }

    @Override
    public Collection selectActivitiesByWorkday(int wordkdatId) {
        return null;
    }

    @Override
    public ActivitiesModel selectActivitiesByEmployee(int employeeId) {
        return null;
    }
}
