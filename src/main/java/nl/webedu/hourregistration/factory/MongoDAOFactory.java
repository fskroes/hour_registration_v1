package nl.webedu.hourregistration.factory;

import nl.webedu.hourregistration.dao.*;

import java.sql.Connection;

public class MongoDAOFactory extends DAOFactory {

    private static final String DATABASE_SCHEME = "hour_registration";

    // method to create Mongo connections
    public static Connection createConnection() {
        // The following is the standard URI connection scheme:
        // mongodb://[username:password@]host1[:port1][,host2[:port2],...[,hostN[:portN]]][/[database][?options]]



//        MongoCredential mongoCredential = MongoCredential.createCredential("root", "hour_registration", "root".toCharArray());
//        com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient("localhost", 27017);

//        MongoDatabase db = mongoClient.getDatabase(DATABASE_SCHEME);


        return null;
    }


    @Override
    public IActivitiesDAO getActivitiesDAO() {
        return null;
    }

    @Override
    public IContractDAO getContractDAO() {
        return null;
    }

    @Override
    public ICustomerDAO getCustomerDAO() {
        return null;
    }

    @Override
    public IEmployeeDAO getEmployeeDAO() {
        return null;
    }

    @Override
    public ILogDAO getLogDAO() {
        return null;
    }

    @Override
    public IProjectDAO getProjectDAO() {
        return null;
    }

    @Override
    public IReportDAO getReportDAO() {
        return null;
    }

    @Override
    public ISubjectDAO getSubjectDAO() {
        return null;
    }

    @Override
    public IUserAuthenticationDAO getUserAuthenticationDAO() {
        return null;
    }

    @Override
    public IWorkdayDAO getWorkdayDAO() {
        return null;
    }
}
