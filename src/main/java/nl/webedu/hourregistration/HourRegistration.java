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
import nl.webedu.hourregistration.model.ProjectModel;
import nl.webedu.hourregistration.model.WorkdayModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HourRegistration extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        DatabaseManager.getInstance().connectToDatabase(DatabaseType.MONGODB);

        Parent root = FXMLLoader.load(getClass().getResource("/LoginView.fxml"));
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
