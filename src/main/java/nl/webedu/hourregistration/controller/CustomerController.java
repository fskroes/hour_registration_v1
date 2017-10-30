package nl.webedu.hourregistration.controller;

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
import javafx.scene.text.Text;
import nl.webedu.hourregistration.dao.ICustomerDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.CustomerModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CustomerController {


    private Boolean geactiveerd = false;

    @FXML
    private VBox   ListVbox, AddVbox, InfoVbox;
    FXMLLoader loader;
    Parent projectInfoView;
    Parent customerListView;
    ProjectInfo projectInfoController;
    CustomerList customerListController;


    public void initialize() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/ProjectInfo.fxml"));
        projectInfoView = loader.load();
        InfoVbox.getChildren().add(projectInfoView);
        projectInfoController = loader.getController();
        loader = new FXMLLoader(getClass().getResource("/CustomerList.fxml"));
        customerListView = loader.load();
        ListVbox.getChildren().add(customerListView);
        customerListController = loader.getController();
        customerListController.setController(projectInfoController);

    }

    @FXML
    public void AddCustomer(MouseEvent mouseEvent) throws IOException {

        if(geactiveerd){
            geactiveerd = false;
            ListVbox.getChildren().clear();
            ListVbox.getChildren().add(customerListView);
        }
        else{
            geactiveerd = true;
            ListVbox.getChildren().clear();
            ListVbox.getChildren().add(FXMLLoader.load(getClass().getResource("/AddCustomer.fxml")));
        }


    }





}
