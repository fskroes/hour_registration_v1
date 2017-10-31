package nl.webedu.hourregistration.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import nl.webedu.hourregistration.model.CustomerModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ProjectInfoController {

    @FXML
    private Text /*ProjectName,*/ ProjectTime, StartDate, EndDate;

    @FXML private Label ProjectName;

//    private FXMLLoader loader;
//    private CustomerList controller;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public void initialize() throws IOException {
//        loader = new FXMLLoader(getClass().getResource("/CustomerList.fxml"));
//        controller = loader.getController();
    }

    public void showProject(int index, List<CustomerModel> customers){
        ProjectName.setText(customers.get(index).getProjectModel().getName());
        ProjectTime.setText(customers.get(index).getProjectModel().getId());
        StartDate.setText(sdf.format(customers.get(index).getProjectModel().getStartDate()));
        EndDate.setText(sdf.format(customers.get(index).getProjectModel().getEndDate()));
    }
}