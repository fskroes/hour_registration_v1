package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTimePicker;
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
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.WorkdayModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.IsoFields;
import java.util.Date;

public class TimesheetController {

    public JFXButton btnSave;
    public JFXButton btnreturntoTimesheets;
    public JFXListView timesheetListview;
    public AnchorPane root;

    JFXTimePicker timePicker1 ;
    JFXTimePicker timePicker2 ;
    JFXTimePicker timePicker3 ;
    JFXTimePicker timePicker4 ;
    JFXTimePicker timePicker5 ;
    JFXTimePicker timePicker6 ;
    JFXTimePicker timePicker7 ;
    JFXTimePicker timePicker8 ;
    JFXTimePicker timePicker9 ;
    JFXTimePicker timePicker10;
    JFXTimePicker timePicker11;
    JFXTimePicker timePicker12;
    JFXTimePicker timePicker13;
    JFXTimePicker timePicker14;


    public void initialize() {
        setUpUserInterface();

        btnSave.setOnAction(e -> {
            WorkdayModel model =
                    new WorkdayModel(
                            new Date(),
                            toDate(timePicker1.getValue()),
                            toDate(timePicker2.getValue()),
                            toWeekNumber(),
                            new SimpleDateFormat("EEEE")
                    );
            DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().insertWorkday(model);
        });


        btnreturntoTimesheets.setOnAction(event -> {
            Stage primaryStage = (Stage) root.getScene().getWindow();
            primaryStage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TimesheetsView.fxml"));

            Parent parent = null;
            try {
                parent = loader.load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            assert parent != null;

            Scene scene = new Scene(parent, 1200, 800);
            primaryStage.setScene(scene);
            primaryStage.show();
        });

    }

    private int toWeekNumber() {
        return ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
    }

    public Date toDate(LocalTime lt){
        Instant instant = lt.atDate(LocalDate.of(1900,1,1)).
                atZone(ZoneId.systemDefault()).toInstant();
        Date time = Date.from(instant);
        return  time;
    }

    public void setUpUserInterface() {
        timePicker1 = new JFXTimePicker();
        timePicker2 = new JFXTimePicker();
        timePicker3 = new JFXTimePicker();
        timePicker4 = new JFXTimePicker();
        timePicker5 = new JFXTimePicker();
        timePicker6 = new JFXTimePicker();
        timePicker7 = new JFXTimePicker();
        timePicker8 = new JFXTimePicker();
        timePicker9 = new JFXTimePicker();
        timePicker10 = new JFXTimePicker();
        timePicker11 = new JFXTimePicker();
        timePicker12 = new JFXTimePicker();
        timePicker13 = new JFXTimePicker();
        timePicker14 = new JFXTimePicker();

        for (int i = 0 ; i < 3 ; i++) {
            HBox itemWrapper = new HBox(1);
            itemWrapper.setFillHeight(true);
            itemWrapper.setAlignment(Pos.BASELINE_RIGHT);

            if (i == 0) {
                VBox ProjectWrapper = new VBox();
                ProjectWrapper.setPadding(new Insets(12));
                ProjectWrapper.setSpacing(1D);
                ProjectWrapper.setAlignment(Pos.CENTER);
                ProjectWrapper.setStyle("-fx-border-color: crimson; -fx-border-width: 1px;");

                Label lblProject = new Label("{ProjectName}");
                lblProject.setFont(Font.font("System", 16));
                Label lblStatus = new Label("Status");
                lblStatus.setFont(Font.font("System", 10));
                ProjectWrapper.getChildren().add(lblProject);
                ProjectWrapper.getChildren().add(lblStatus);

                itemWrapper.getChildren().add(ProjectWrapper);

                VBox timeWorked1 = new VBox();
                timeWorked1.setPadding(new Insets(10));
                timeWorked1.setSpacing(1D);
                timeWorked1.setAlignment(Pos.CENTER);
                timeWorked1.setStyle("-fx-border-color: deepskyblue; -fx-border-width: 1px;");

                timePicker1.setIs24HourView(true);
                timePicker2.setIs24HourView(true);
                timeWorked1.getChildren().add(timePicker1);
                timeWorked1.getChildren().add(timePicker2);
                itemWrapper.getChildren().add(timeWorked1);


                VBox timeWorked2 = new VBox();
                timeWorked1.setPadding(new Insets(10));
                timeWorked1.setSpacing(1D);
                timeWorked1.setAlignment(Pos.CENTER);
                timeWorked2.setStyle("-fx-border-color: deepskyblue; -fx-border-width: 1px;");
                timePicker3.setIs24HourView(true);
                timePicker4.setIs24HourView(true);
                timeWorked2.getChildren().add(timePicker3);
                timeWorked2.getChildren().add(timePicker4);
                itemWrapper.getChildren().add(timeWorked2);

                VBox timeWorked3 = new VBox();
                timeWorked1.setPadding(new Insets(10));
                timeWorked1.setSpacing(1D);
                timeWorked1.setAlignment(Pos.CENTER);
                timeWorked3.setStyle("-fx-border-color: deepskyblue; -fx-border-width: 1px;");
                timePicker5.setIs24HourView(true);
                timePicker6.setIs24HourView(true);
                timeWorked3.getChildren().add(timePicker5);
                timeWorked3.getChildren().add(timePicker6);
                itemWrapper.getChildren().add(timeWorked3);

                VBox timeWorked4 = new VBox();
                timeWorked1.setPadding(new Insets(10));
                timeWorked1.setSpacing(1D);
                timeWorked1.setAlignment(Pos.CENTER);
                timeWorked4.setStyle("-fx-border-color: deepskyblue; -fx-border-width: 1px;");
                timePicker7.setIs24HourView(true);
                timePicker8.setIs24HourView(true);
                timeWorked4.getChildren().add(timePicker7);
                timeWorked4.getChildren().add(timePicker8);
                itemWrapper.getChildren().add(timeWorked4);

                VBox timeWorked5 = new VBox();
                timeWorked1.setPadding(new Insets(10));
                timeWorked1.setSpacing(1D);
                timeWorked1.setAlignment(Pos.CENTER);
                timeWorked5.setStyle("-fx-border-color: deepskyblue; -fx-border-width: 1px;");
                timePicker9.setIs24HourView(true);
                timePicker10.setIs24HourView(true);
                timeWorked5.getChildren().add(timePicker9);
                timeWorked5.getChildren().add(timePicker10);
                itemWrapper.getChildren().add(timeWorked5);

                VBox timeWorked6 = new VBox();
                timeWorked1.setPadding(new Insets(10));
                timeWorked1.setSpacing(1D);
                timeWorked1.setAlignment(Pos.CENTER);
                timeWorked6.setStyle("-fx-border-color: deepskyblue; -fx-border-width: 1px;");
                timePicker11.setIs24HourView(true);
                timePicker12.setIs24HourView(true);
                timeWorked6.getChildren().add(timePicker11);
                timeWorked6.getChildren().add(timePicker12);
                itemWrapper.getChildren().add(timeWorked6);

                VBox timeWorked7 = new VBox();
                timeWorked1.setPadding(new Insets(10));
                timeWorked1.setSpacing(1D);
                timeWorked1.setAlignment(Pos.CENTER);
                timeWorked7.setStyle("-fx-border-color: deepskyblue; -fx-border-width: 1px;");
                timePicker13.setIs24HourView(true);
                timePicker14.setIs24HourView(true);
                timeWorked7.getChildren().add(timePicker13);
                timeWorked7.getChildren().add(timePicker14);
                itemWrapper.getChildren().add(timeWorked7);
            }
            timesheetListview.getItems().add(itemWrapper);
        }
    }
 }


