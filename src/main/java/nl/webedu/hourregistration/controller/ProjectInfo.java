package nl.webedu.hourregistration.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import nl.webedu.hourregistration.model.CustomerModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ProjectInfo {

    @FXML
    private Text ProjectName, ProjectTime, StartDate, EndDate;

//    private FXMLLoader loader;
//    private CustomerList controller;

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public void initialize() throws IOException {
//        loader = new FXMLLoader(getClass().getResource("/CustomerList.fxml"));
//        controller = loader.getController();
    }

    public void showProject(int index, List<CustomerModel> customers){
        System.out.println("hier komt hij ook");
        System.out.println(sdf.format(customers.get(index).getProjectModel().getStartDate()));
        ProjectName.setText(customers.get(index).getProjectModel().getName());
        ProjectTime.setText(customers.get(index).getProjectModel().getId());
        StartDate.setText(sdf.format(customers.get(index).getProjectModel().getStartDate()));
        EndDate.setText(sdf.format(customers.get(index).getProjectModel().getEndDate()));
    }
}
