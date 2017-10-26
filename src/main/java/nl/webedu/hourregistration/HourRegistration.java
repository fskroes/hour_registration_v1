package nl.webedu.hourregistration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.database.DatabaseType;
import nl.webedu.hourregistration.model.ProjectModel;

public class HourRegistration extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        DatabaseManager.getInstance().connectToDatabase(DatabaseType.MARIADB);

        for (ProjectModel project : DatabaseManager.getInstance().getDaoFactory().getProjectDAO().selectAllProjects()) {
            System.out.println(project.getName());
        }

        Parent root = FXMLLoader.load(getClass().getResource("/TimesheetView.fxml"));
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
