package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.WorkdayModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.IsoFields;
import java.util.Date;

public class TimesheetController {

    private static boolean loaded = false;

    public JFXButton btnSave;
    public JFXButton btnreturntoTimesheets;
    public JFXListView timesheetListview;
    public AnchorPane root;
    public HBox cellHBOX;
    public Label weekLabel;
    public JFXTimePicker timepicker1;
    public JFXTimePicker timepicker2;
    public JFXTimePicker timepicker3;
    public JFXTimePicker timepicker4;
    public JFXTimePicker timepicker5;
    public JFXTimePicker timepicker6;
    public JFXTimePicker timepicker7;
    public JFXTimePicker timepicker8;
    public JFXTimePicker timepicker9;
    public JFXTimePicker timepicker10;
    public JFXTimePicker timepicker11;
    public JFXTimePicker timepicker12;
    public JFXTimePicker timepicker13;
    public JFXTimePicker timepicker14;

    public void initialize() {
        if (loaded) {
            return;
        } else {
            loaded = true;
            setUpUserInterface();
        }

        weekLabel.setText("Week: " + String.valueOf(toWeekNumber()));

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

            loaded = false;

            Scene scene = new Scene(parent, 1200, 800);
            primaryStage.setScene(scene);
            primaryStage.show();
        });

    }

    private int toWeekNumber() {
        return ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
    }

    public Date toDate(LocalTime lt) {
        Instant instant = lt.atDate(LocalDate.of(1900,1,1)).
                atZone(ZoneId.systemDefault()).toInstant();
        Date time = Date.from(instant);
        return  time;
    }

    public void setUpUserInterface() {
        try {
            HBox newCell = FXMLLoader.load(getClass().getResource("/TimesheetCellView.fxml"));
            for (Node n: newCell.getChildren()) {
                System.out.println("n : " + n.getId());
            }
            cellHBOX.getChildren().add(newCell);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SAVEALL(ActionEvent actionEvent) {
        WorkdayModel mon, tues, wednes, thurs, fri, sat, sun;
        mon = new WorkdayModel(
                new Date(),
                toDate(timepicker1.getValue()),
                toDate(timepicker2.getValue()),
                toWeekNumber(),
                new SimpleDateFormat("EEEE")
        );
        tues = new WorkdayModel(
                new Date(),
                toDate(timepicker3.getValue()),
                toDate(timepicker4.getValue()),
                toWeekNumber(),
                new SimpleDateFormat("EEEE")
        );
        wednes = new WorkdayModel(
                new Date(),
                toDate(timepicker5.getValue()),
                toDate(timepicker6.getValue()),
                toWeekNumber(),
                new SimpleDateFormat("EEEE")
        );
        thurs = new WorkdayModel(
                new Date(),
                toDate(timepicker7.getValue()),
                toDate(timepicker8.getValue()),
                toWeekNumber(),
                new SimpleDateFormat("EEEE")
        );
        fri = new WorkdayModel(
                new Date(),
                toDate(timepicker9.getValue()),
                toDate(timepicker10.getValue()),
                toWeekNumber(),
                new SimpleDateFormat("EEEE")
        );
        sat = new WorkdayModel(
                new Date(),
                toDate(timepicker11.getValue()),
                toDate(timepicker12.getValue()),
                toWeekNumber(),
                new SimpleDateFormat("EEEE")
        );
        sun = new WorkdayModel(
                new Date(),
                toDate(timepicker13.getValue()),
                toDate(timepicker14.getValue()),
                toWeekNumber(),
                new SimpleDateFormat("EEEE")
        );
        DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().insertWorkday(mon);
        DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().insertWorkday(tues);
        DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().insertWorkday(wednes);
        DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().insertWorkday(thurs);
        DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().insertWorkday(fri);
        DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().insertWorkday(sat);
        DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().insertWorkday(sun);
    }
}


