package nl.webedu.hourregistration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.webedu.hourregistration.dao.factory.MongoDAOFactory;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.DatabaseType;
import nl.webedu.hourregistration.model.ActivitiesModel;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.WorkdayModel;

import java.util.ArrayList;
import java.util.Date;

public class HourRegistration extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        DatabaseManager.getInstance().connectToDatabase(DatabaseType.MONGODB);

        ArrayList<EmployeeModel> em = new ArrayList<EmployeeModel>();
        em.add(new EmployeeModel("mail@m.nl","Leon"," ", "Marchal"));
        em.add(new EmployeeModel("mail@asdasd.nl","Matthijs"," ", "Eikelboom"));

        ArrayList<ActivitiesModel> am = new ArrayList<ActivitiesModel>();
        am.add(new ActivitiesModel( "categorie 2", new Date(), new Date(),2));
        am.add(new ActivitiesModel( "categorie 4", new Date(), new Date(),2));

        WorkdayModel wm = new WorkdayModel(new Date(),new Date(),new Date(),2,am,em);

        DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().insertWorkday(wm);




        Parent root = FXMLLoader.load(getClass().getResource("/LoginView.fxml"));
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
