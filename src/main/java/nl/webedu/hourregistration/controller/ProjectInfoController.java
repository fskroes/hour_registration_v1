package nl.webedu.hourregistration.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import nl.webedu.hourregistration.model.CustomerModel;

import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class ProjectInfoController {

    @FXML
    private Text ProjectName, ProjectTime, StartDate, EndDate;
    private CustomerModel customer;
    private EditProjectController controller;

//    private FXMLLoader loader;
//    private CustomerList controller;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * zorgt ervoor dat de view wordt geladen.
     */
    public void initialize() {
    }

    /**
     * dit vult de textvelden van de projectinfo view met de gegevens van het project van de aangeklikte klant.
     * het kijkt of de project gegevens er al zijn, zo niet dan zet het de textvelden naar -
     * @param customer
     */
    public void showProject(CustomerModel customer){
        this.customer = customer;
        ProjectName.setText(customer.getProjectModel().getName());
//        ProjectTime.setText(customer.getProjectModel().getId());

        if (customer.getProjectModel().getStartDate()!=null) {
            StartDate.setText(sdf.format(customer.getProjectModel().getStartDate()));
        }
        else{
            StartDate.setText("-");
        }
        if (customer.getProjectModel().getEndDate()!=null) {
            EndDate.setText(sdf.format(customer.getProjectModel().getEndDate()));
        }
        else {
            EndDate.setText("-");
        }

    }

    /**
     * dit get de customer voor wanneer het project moet worden aangepast. zo wordt dus altijd het project aangepast
     * van de customer die op dat moment geselecteerd is.
     * @return
     */
    public CustomerModel getCustomer (){
        return customer;
    }
}
