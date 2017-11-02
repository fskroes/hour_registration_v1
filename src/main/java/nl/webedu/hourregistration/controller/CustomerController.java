package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.webedu.hourregistration.dao.ICustomerDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.CustomerModel;

import javax.sound.sampled.Line;
import java.io.IOException;

public class CustomerController {


    private boolean geactiveerd = false;
    private boolean editing = false;

    @FXML
    private AnchorPane root;
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
    CustomerList customerListController;
    EditProject editProjectController;
    AddCustomer addCustomerController;


    public void initialize() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/ProjectInfoView.fxml"));
        projectInfoView = loader.load();
        InfoVbox.getChildren().add(projectInfoView);
        projectInfoController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("/CustomerList.fxml"));
        customerListView = loader.load();
        ListVbox.getChildren().add(customerListView);
        customerListController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("/EditProject.fxml"));
        editProjectView = loader.load();
        editProjectController = loader.getController();

        loader = new FXMLLoader(getClass().getResource("/AddCustomer.fxml"));
        addCustomerView = loader.load();
        addCustomerController = loader.getController();

        customerListController.setProjectInfoController(projectInfoController);

    }

    @FXML
    public void AddCustomer(MouseEvent mouseEvent) throws IOException {

        if(geactiveerd){
            addCustomerController.newCustomer();
            customerListController.addCustomerToList(addCustomerController.getCustomer());
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

    public void toPreviousView (ActionEvent actionEvent) {
        Stage primaryStage = (Stage) root.getScene().getWindow();
        primaryStage.hide();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/TimesheetsView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert parent != null;
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
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
    @FXML
    public void BackToHome(MouseEvent mouseEvent) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/TimesheetsView.fxml"));
    }





}
