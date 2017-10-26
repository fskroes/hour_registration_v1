package nl.webedu.hourregistration.dao.mongodb;

import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoCollection;
import nl.webedu.hourregistration.dao.IReportDAO;
import nl.webedu.hourregistration.model.CustomerModel;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.ReportModel;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static nl.webedu.hourregistration.database.DatabaseUtil.DATABASE_NAME;
import static nl.webedu.hourregistration.database.DatabaseUtil.REPORT_COLLECTION;

public class MongoReportDAO implements IReportDAO {

    private static MongoReportDAO instance;
    private MongoClient client;
    ArrayList<Document> alReportDocuments = new ArrayList<>();


    public static MongoReportDAO getInstance() {
        if (instance == null)
            instance = new MongoReportDAO();
        return instance;
    }

    @Override
    public boolean insertReport(ReportModel report) {
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();

        Document query = new Document("workdays", report.getWorkDays())
                .append("week_number",report.getWeekNumber())
                .append("report_date",report.getReportDate())
                .append("report_end_date", report.getReportEndDate());

        client.getDatabase(DATABASE_NAME).getCollection(REPORT_COLLECTION)
                .insertOne(query, (result, t) -> completableFuture.complete(true));
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public int deleteReport(ReportModel report) {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        Document query = new Document();
        query.put("_id", report.getId());

        client.getDatabase(DATABASE_NAME).getCollection(REPORT_COLLECTION).deleteOne(
                query,
                (deleteResult, throwable) -> completableFuture.complete((int) deleteResult.getDeletedCount()));
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ReportModel findReport(String id) {
        MongoCollection<Document> collection = client.getDatabase(DATABASE_NAME).getCollection(REPORT_COLLECTION);
        CompletableFuture<ReportModel> result = new CompletableFuture<>();
        collection
                .find(eq("_id", id))
                .first((document, Throwable) -> { // onResults
                    System.out.println(document.toJson());
                    result.complete(new ReportModel().convertMongo(document));
                });
        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }    }

    @Override
    public int updateReport(ReportModel report) {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        Document query = new Document();
        query.put("workday_id", report.getId());
        client.getDatabase(DATABASE_NAME).getCollection(REPORT_COLLECTION).updateOne(
                eq("date", report.getWorkDays()),
                combine(set("start_time", report.getWeekNumber()),
                        set("end_time", report.getReportDate()),
                        set("week_number", report.getReportEndDate())),
                (updateResult, throwable) -> {
                    completableFuture.complete((int) updateResult.getModifiedCount());
                });
        try {
            return completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return 0;
        }    }

    @Override
    public List<ReportModel> selectAllReports() {
        CompletableFuture<List<ReportModel>> result = new CompletableFuture<>();
        ArrayList<ReportModel> alWorkdays = new ArrayList<>();
        MongoCollection<Document> collection = client.getDatabase(DATABASE_NAME).getCollection(REPORT_COLLECTION);

        collection
                .find()
                .into(alReportDocuments,(documents, throwable) -> {
                    for (Document d: alReportDocuments) {
                        alWorkdays.add(new ReportModel().convertMongo(d));
                    }
                    result.complete(alWorkdays);
                });
        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ReportModel> selectReportByEmployee(EmployeeModel employee) {
        CompletableFuture<List<ReportModel>> result = new CompletableFuture<>();
        ArrayList<ReportModel> alWorkdays = new ArrayList<>();
        MongoCollection<Document> collection = client.getDatabase(DATABASE_NAME).getCollection(REPORT_COLLECTION);
        System.out.println("!!!!!!! "+employee.get_id());
        collection
                .find(eq("reports",employee.get_id()))
                .forEach((document -> {
                    System.out.println(document.toJson());
                    alWorkdays.add(new ReportModel().convertMongo(document));
                }), (aVoid, throwable) -> {
                    result.complete(alWorkdays);
                });
        try {
            return result.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
