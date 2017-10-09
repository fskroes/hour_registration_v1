package nl.webedu.hourregistration;

import javafx.application.Application;
import javafx.stage.Stage;
import nl.webedu.hourregistration.daointerfaces.IUserAuthenticationDAO;
import nl.webedu.hourregistration.factory.DAOFactory;
import nl.webedu.hourregistration.model.UserAuthenticationModel;

public class HourRegistration extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        // create the required DAO Factory
        DAOFactory mongoFactory = DAOFactory.getDAOFactory(DAOFactory.MONGO);

        // Create a DAO
        IUserAuthenticationDAO mongoUserAuthenticationDAO = mongoFactory.getUserAuthenticationDAO();

        // create a new Authentication model
        UserAuthenticationModel aModel = mongoUserAuthenticationDAO.registerUser("root", "root");

        // modify the values in the Transfer Object
        aModel.setEmail("");
        aModel.setPassword("");



    }
}
