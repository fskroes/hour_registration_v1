package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import nl.webedu.hourregistration.dao.ICustomerDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.CustomerModel;

import javax.sound.sampled.Line;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CustomerController {


    private boolean geactiveerd = false;
    private boolean editing = false;

    @FXML
    private VBox   ListVbox, AddVbox, InfoVbox;
    @FXML
    private JFXButton EditButton, AddCustomer;

    FXMLLoader loader;

    Parent projectInfoView;
    Parent customerListView;
    Parent editProjectView;
    Parent addCustomerView;

    ProjectInfo projectInfoController;
    CustomerList customerListController;
    EditProject editProjectController;
    AddCustomer addCustomerController;


    public void initialize() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/ProjectInfo.fxml"));
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





}
