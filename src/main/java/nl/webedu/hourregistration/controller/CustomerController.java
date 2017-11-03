package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerController {


    public AnchorPane root;
    private boolean geactiveerd = false;
    private boolean editing = false;

    @FXML
    private VBox   ListVbox, AddVbox, InfoVbox;
    @FXML
    private JFXButton EditButton, AddCustomer, Back;

    FXMLLoader loader;

    Parent projectInfoView;
    Parent customerListView;
    Parent editProjectView;
    Parent addCustomerView;

    ProjectInfoController projectInfoController;
    CustomerListController customerListController;
    EditProjectController editProjectController;
    AddCustomerController addCustomerController;

    /**
     * Wordt aangeroepen wanneer de view wordt opgestart, de views die moeten worden getoond na het drukken van een knop
     * worden alvast geladen en opgeslagen, de controllers van de views worden ook opgeslagen zodat deze kunnen worden
     * meegegeven aan elkaar.
     */
    public void initialize() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/ProjectInfoView.fxml"));
        projectInfoView = loader.load();
        InfoVbox.getChildren().add(projectInfoView);
        projectInfoController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("/CustomerListView.fxml"));
        customerListView = loader.load();
        ListVbox.getChildren().add(customerListView);
        customerListController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("/EditProjectView.fxml"));
        editProjectView = loader.load();
        editProjectController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("/AddCustomerView.fxml"));
        addCustomerView = loader.load();
        addCustomerController = loader.getController();

        customerListController.setProjectInfoController(projectInfoController);
        addCustomerController.setCustomerListController(customerListController);

    }

    /**
     * Als de knop + wordt ingedrukt moet de view worden geladen waarin een nieuwe klant kan worden gemaakt
     * als dezelfde knop dan nog eens wordt gedrukt wordt de tekst uit het tekstveld gebruikt om een klant object
     * aan te maken en te inserten in de database
     * @param mouseEvent
     * @throws IOException
     */
    @FXML
    public void AddCustomer(MouseEvent mouseEvent) throws IOException {

        if(geactiveerd){
            addCustomerController.newCustomer();
            AddCustomer.setText("+");
            AddCustomer.setFont(Font.font("Microsoft Tai Le", FontWeight.BOLD, 20));
            AddCustomer.setPrefWidth(46);
            geactiveerd = false;
            ListVbox.getChildren().clear();
            ListVbox.getChildren().add(customerListView);
        }
        else{
            AddCustomer.setPrefWidth(108);
            AddCustomer.setText("Opslaan");
            AddCustomer.setFont(Font.font("Eras Medium ITC", FontWeight.NORMAL, 18));
            geactiveerd = true;
            addCustomerController.emptyNewCustomerText();
            ListVbox.getChildren().clear();
            ListVbox.getChildren().add(addCustomerView);
        }


    }

    /**
     * Als de knop Pas aan wordt ingedrukt wordt de view voor editen project geladen, hierin kunnen data over het project
     * van de desbetreffende klant worden ingevuld. Wanneer alle velden zijn ingevuld en er nog eens op de knop wordt gedrukt,
     * komt de eerste view terug en wordt het project opgeslagen in een model en in de database.
     * @param mouseEvent
     * @throws IOException
     */
    @FXML
    public void EditProject (MouseEvent mouseEvent) throws IOException {
        if (editing){
            editProjectController.saveProject();
            projectInfoController.showProject(editProjectController.getCustomer());
            EditButton.setText("Pas aan");
            InfoVbox.getChildren().clear();
            InfoVbox.getChildren().add(projectInfoView);
            editing = false;
        }
        else{
            EditButton.setText("Opslaan");
            editProjectController.setCustomer(projectInfoController.getCustomer());
            editProjectController.setDefaults();
            InfoVbox.getChildren().clear();
            InfoVbox.getChildren().add(editProjectView);
            editing = true;
        }
    }

    /**
     * Als op de knop terug wordt gedrukt sluit de huidige view.
     * @param mouseEvent
     * @throws IOException
     */
    @FXML
    public void BackToHome(MouseEvent mouseEvent) throws IOException {
        Stage primaryStage = (Stage) root.getScene().getWindow();
        primaryStage.hide();
    }





}
