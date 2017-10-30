package nl.webedu.hourregistration.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CustomerController {


    private Boolean geactiveerd = false;

    @FXML
    private VBox   ListVbox, AddVbox, InfoVbox;
    FXMLLoader loader;
    Parent projectInfoView;
    Parent customerListView;
    ProjectInfoController projectInfoControllerController;
    CustomerListController customerListControllerController;


    public void initialize() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/ProjectInfoView.fxml"));
        projectInfoView = loader.load();
        InfoVbox.getChildren().add(projectInfoView);
        projectInfoControllerController = loader.getController();
        loader = new FXMLLoader(getClass().getResource("/CustomerList.fxml"));
        customerListView = loader.load();
        ListVbox.getChildren().add(customerListView);
        customerListControllerController = loader.getController();
        customerListControllerController.setController(projectInfoControllerController);

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
            ListVbox.getChildren().add(FXMLLoader.load(getClass().getResource("/AddCustomerView.fxml")));
        }


    }





}
