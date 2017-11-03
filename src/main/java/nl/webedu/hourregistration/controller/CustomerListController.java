package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import nl.webedu.hourregistration.dao.ICustomerDAO;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.CustomerModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;


public class CustomerListController {

    ObservableList obsList = FXCollections.observableArrayList();

    private ICustomerDAO customerDAO;
    private List<CustomerModel> customers;
    private int index;
    private ProjectInfoController controller;


    @FXML
    private JFXListView<?> ListView;

    /**
     * laad de cusomerdao en laadt de namen van de klanten in de listview
     * @throws IOException
     */
    public void initialize() throws IOException {
        customerDAO = DatabaseManager.getInstance().getDaoFactory().getCustomerDAO();
        loadData();
    }

    /**
     * haalt een lijst van customers uit de database, zet de namen van de klanten in een observerlist en
     * laadt ze in de listview.
     * @throws IOException
     */
    public void loadData() throws IOException{
        obsList.removeAll();
        customers = customerDAO.selectAllCustomers();
        for(CustomerModel customer : customers){
            obsList.add(customer.getBusinessName());
        }

        ListView.getItems().addAll(obsList);

    }

    /**
     * als er op een klant in de list wordt gedrukt wordt in de project info view de informatie getoond over het project
     * van deze klant. de index van het geklikte item mag niet -1 zijn (er bestaat geen -1 plek in de customerlist), deze
     * -1 komt van het scrollvlak in de listview en is dus geen klant.
     * @param mouseEvent
     */
    public void customerSelect(MouseEvent mouseEvent) {
        index = ListView.getSelectionModel().getSelectedIndex();
        if(index != -1){
            controller.showProject(customers.get(index));
        }

    }

    /**
     * zet de projectinfocontroller en laat het project zien van de bovenste klant.
     * @param controller
     */
    public void setProjectInfoController(ProjectInfoController controller){
        this.controller = controller;
        controller.showProject(customers.get(0));

    }

    /**
     * zet de net aangemaakte klant in de customer- en observerlist (op de eerste plaats) en laadt de listview opnieuw met de
     * observerlist.
     * @param customer
     */
    public void addCustomerToList(CustomerModel customer){
        customers.add(0, customer);
        obsList.add(0, customer.getBusinessName());
        ListView.getItems().clear();
        ListView.getItems().addAll(obsList);
        ListView.refresh();

    }

}
