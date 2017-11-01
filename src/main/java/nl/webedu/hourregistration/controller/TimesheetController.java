package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.helpers.Data;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.ProjectModel;
import nl.webedu.hourregistration.model.WorkdayModel;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.time.temporal.IsoFields;
import java.util.*;

public class TimesheetController {

    private static boolean loaded = false;
    private EmployeeModel activeEmployee;
    private Map<ProjectModel, HBox> row;


    @FXML private ResourceBundle resources;
    @FXML private URL location;
    private List<String> stringList = new ArrayList<>(5);
    private ObservableList observableList = FXCollections.observableArrayList();

    public JFXButton btnreturntoTimesheets;
    public JFXListView timesheetListview;
    public AnchorPane root;
    public HBox cellHBOX;
    public Label weekLabel;
    public JFXTimePicker
            timepicker1,
            timepicker2,
            timepicker3,
            timepicker4,
            timepicker5,
            timepicker6,
            timepicker7,
            timepicker8,
            timepicker9,
            timepicker10,
            timepicker11,
            timepicker12,
            timepicker13,
            timepicker14;

    public void initialize() {
        row = new HashMap<>();
        if (loaded) {
            return;
        } else {
            loaded = true;
//            setUpUserInterface();
        }

        weekLabel.setText("Week: " + String.valueOf(toWeekNumber()));

        assert timesheetListview != null : "fx:id=\"timesheetListview\" was not injected: check your FXML file 'CustomList.fxml'.";

        setListView();
    }

    private void setListView() {
        stringList.add("String 1");
        stringList.add("String 2");
        stringList.add("String 3");
        stringList.add("String 4");

        observableList.setAll(stringList);

        timesheetListview.setItems(observableList);

        timesheetListview.setCellFactory(
                new Callback<ListView<String>, ListCell<String>>() {
                    @Override
                    public ListCell<String> call(ListView<String> listView) {
                        return new ListViewCell();
                    }
                });
    }

    public void postConstructor(EmployeeModel sessionEmployee, int weekId) {
        this.activeEmployee = sessionEmployee;
        sessionEmployee.getWorksdaysByWeekNumber(weekId);
    }

    private int toWeekNumber() {
        return ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
    }

    public Date toDate(LocalTime lt) {
        if(lt != null) {
            Instant instant = lt.atDate(LocalDate.of(1900,1,1)).
                    atZone(ZoneId.systemDefault()).toInstant();
            Date time = Date.from(instant);
            return time;
        }

        return Optional.ofNullable(new Date())
                .filter(s -> s != null).orElse(new Date());
    }

//    public void setUpUserInterface() {
//
//        HBox box = null;
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TimesheetCellView.fxml"));
//        try {
//            box = loader.load();
//            cellHBOX.getChildren().addAll(box);
////            Map<String, Object> namespace = loader.getNamespace();
////
////            timepicker1 = (JFXTimePicker) namespace.get("timepicker1");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        TimePickerController controller = loader.getController();
//
//        timepicker1 = controller.getTimepicker1();
//        timepicker2 = controller.getTimepicker2();
//        timepicker3 = controller.getTimepicker3();
//        timepicker4 = controller.getTimepicker4();
//        timepicker5 = controller.getTimepicker5();
//        timepicker6 = controller.getTimepicker6();
//        timepicker7 = controller.getTimepicker7();
//        timepicker8 = controller.getTimepicker8();
//        timepicker9 = controller.getTimepicker9();
//        timepicker10 = controller.getTimepicker10();
//        timepicker11 = controller.getTimepicker11();
//        timepicker12 = controller.getTimepicker12();
//        timepicker13 = controller.getTimepicker13();
//        timepicker14 = controller.getTimepicker14();
//
//    }

    public void SAVEALL(ActionEvent actionEvent) {
        WorkdayModel mon, tues, wednes, thurs, fri, sat, sun;
        System.out.println("timepicker 1: " + timepicker1.toString());
        System.out.println("timepicker 1: " + timepicker1.getValue());

        mon = new WorkdayModel(
                new Date(),
                toDate(timepicker1.getValue()),
                toDate(timepicker2.getValue()),
                toWeekNumber(),
                "monday"
        );
        tues = new WorkdayModel(
                new Date(),
                toDate(timepicker3.getValue()),
                toDate(timepicker4.getValue()),
                toWeekNumber(),
                "tuesday"
        );
        wednes = new WorkdayModel(
                new Date(),
                toDate(timepicker5.getValue()),
                toDate(timepicker6.getValue()),
                toWeekNumber(),
                "wednesday"
        );
        thurs = new WorkdayModel(
                new Date(),
                toDate(timepicker7.getValue()),
                toDate(timepicker8.getValue()),
                toWeekNumber(),
                "thrusday"
        );
        fri = new WorkdayModel(
                new Date(),
                toDate(timepicker9.getValue()),
                toDate(timepicker10.getValue()),
                toWeekNumber(),
                "friday"
        );
        sat = new WorkdayModel(
                new Date(),
                toDate(timepicker11.getValue()),
                toDate(timepicker12.getValue()),
                toWeekNumber(),
                "saturday"
        );
        sun = new WorkdayModel(
                new Date(),
                toDate(timepicker13.getValue()),
                toDate(timepicker14.getValue()),
                toWeekNumber(),
                "sunday"
        );
        DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().insertWorkday(mon);
        DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().insertWorkday(tues);
        DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().insertWorkday(wednes);
        DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().insertWorkday(thurs);
        DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().insertWorkday(fri);
        DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().insertWorkday(sat);
        DatabaseManager.getInstance().getDaoFactory().getWorkdayDAO().insertWorkday(sun);
    }

    static class ListViewCell extends ListCell<String>
    {
        @Override
        public void updateItem(String string, boolean empty)
        {
            super.updateItem(string,empty);
            if(string != null)
            {
                Data data = new Data();
                data.setInfo(string);
                setGraphic(data.getBox());
            }
        }
    }
}


