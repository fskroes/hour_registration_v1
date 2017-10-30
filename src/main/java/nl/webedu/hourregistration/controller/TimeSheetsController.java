package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.webedu.hourregistration.enumeration.Role;
import nl.webedu.hourregistration.model.EmployeeModel;

import java.io.IOException;

public class TimeSheetsController {

    private EmployeeModel sessionEmployee;

    @FXML
    public AnchorPane root;
    @FXML
    public HBox header;
    @FXML
    public VBox content;
    @FXML
    public VBox timeSheetConainer;
    @FXML
    public JFXListView lvTimeSheets;

    public void initialize() {
        setupUserInterface();
    }

    private void setupUserInterface() {
        HBox itemWrapper = new HBox(48);
        itemWrapper.setFillHeight(true);

        VBox dateWrapper = new VBox();
        dateWrapper.setPadding(new Insets(12));
        dateWrapper.setSpacing(5D);
        dateWrapper.setAlignment(Pos.CENTER);
//        dateWrapper.setStyle("-fx-border-color: crimson; -fx-border-width: 1px;");

        Label lblDateToDate = new Label("Datum - Datum");
        lblDateToDate.setFont(Font.font("System", 16));
        Label lblStatus = new Label("Status");
        lblStatus.setFont(Font.font("System", 10));
        dateWrapper.getChildren().add(lblDateToDate);
        dateWrapper.getChildren().add(lblStatus);

        VBox timeWorked = new VBox();
        timeWorked.setPadding(new Insets(12));
        timeWorked.setSpacing(5D);
        timeWorked.setAlignment(Pos.CENTER);
//        timeWorked.setStyle("-fx-border-color: deepskyblue; -fx-border-width: 1px;");

        Label lblTotalTime = new Label("Total time");
        lblTotalTime.setFont(Font.font("System", 16));
        Label lblHoursWorked = new Label("5 Hrs");
        lblHoursWorked.setFont(Font.font("System", 10));
        timeWorked.getChildren().add(lblTotalTime);
        timeWorked.getChildren().add(lblHoursWorked);

        VBox overtimeWorked = new VBox();
        overtimeWorked.setPadding(new Insets(12));
        overtimeWorked.setSpacing(5D);
        overtimeWorked.setAlignment(Pos.CENTER);
//        overtimeWorked.setStyle("-fx-border-color: lawngreen; -fx-border-width: 1px;");

        Label lblOvertime = new Label("Overtime");
        lblOvertime.setFont(Font.font("System", 16));
        Label lblHoursOvertime = new Label("2 Hrs");
        lblHoursOvertime.setFont(Font.font("System", 10));
        overtimeWorked.getChildren().add(lblOvertime);
        overtimeWorked.getChildren().add(lblHoursOvertime);

        //

        JFXButton btnTimeSheet = new JFXButton("View timesheet");
        btnTimeSheet.setOnAction(event -> {
            Stage primaryStage = (Stage) root.getScene().getWindow();
            primaryStage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TimesheetView.fxml"));

            Parent parent = null;
            try {
                parent = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert parent != null;

            //MainController controller = loader.getController();

            Scene scene = new Scene(parent, 1200, 800);
            primaryStage.setScene(scene);
            primaryStage.show();
        });

        itemWrapper.getChildren().add(dateWrapper);
        itemWrapper.getChildren().add(timeWorked);
        itemWrapper.getChildren().add(overtimeWorked);
        itemWrapper.getChildren().add(btnTimeSheet);

        lvTimeSheets.getItems().add(itemWrapper);
    }
}
