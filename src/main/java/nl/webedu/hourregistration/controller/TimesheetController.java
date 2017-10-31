package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.model.WorkdayModel;

import java.io.IOException;
import java.time.*;
import java.time.temporal.IsoFields;
import java.util.Date;
import java.util.Optional;

public class TimesheetController {

    private static boolean loaded = false;

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
    public JFXTreeTableView treeTableView;
    public Label itemCount;
    public JFXTreeTableCell treeTableCell;

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
        if(lt != null) {
            Instant instant = lt.atDate(LocalDate.of(1900,1,1)).
                    atZone(ZoneId.systemDefault()).toInstant();
            Date time = Date.from(instant);
            return time;
        }

        return Optional.ofNullable(new Date())
                .filter(s -> s != null).orElse(new Date());
    }

    public void setUpUserInterface() {


        JFXTreeTableColumn<User, String> mondayColumn = new JFXTreeTableColumn<>("Monday");
        JFXTreeTableColumn<User, String> tuesdayColumn = new JFXTreeTableColumn<>("Tuesday");
        JFXTreeTableColumn<User, String> wednesdayColumn = new JFXTreeTableColumn<>("Wednesday");
        JFXTreeTableColumn<User, String> thursdayColumn = new JFXTreeTableColumn<>("Thursday");
        JFXTreeTableColumn<User, String> fridayColumn = new JFXTreeTableColumn<>("Friday");
        JFXTreeTableColumn<User, String> saturdayColumn = new JFXTreeTableColumn<>("Saturday");
        JFXTreeTableColumn<User, String> sundayColumn = new JFXTreeTableColumn<>("Sunday");

        mondayColumn.setPrefWidth(150);
        mondayColumn.setCellValueFactory(param -> {
            if (mondayColumn.validateValue(param)) return param.getValue().getValue().age;
            else return mondayColumn.getComputedValue(param);
        });


        mondayColumn.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
        mondayColumn.setOnEditCommit(t -> t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().age.setValue(t.getNewValue()));

        // data
        ObservableList<User> users = FXCollections.observableArrayList();
        users.add(new User("Computer Department", "23","CD 1"));
        users.add(new User("Sales Department", "22","Employee 1"));
        users.add(new User("Sales Department", "22","Employee 2"));
        users.add(new User("Sales Department", "25","Employee 4"));
        users.add(new User("Sales Department", "25","Employee 5"));
        users.add(new User("IT Department", "42","ID 2"));
        users.add(new User("HR Department", "22","HR 1"));
        users.add(new User("HR Department", "22","HR 2"));

        for(int i = 0 ; i< 40000; i++){
            users.add(new User("HR Department", i%10+"","HR 2" + i));
        }
        for(int i = 0 ; i< 40000; i++){
            users.add(new User("Computer Department", i%20+"","CD 2" + i));
        }

        for(int i = 0 ; i< 40000; i++){
            users.add(new User("IT Department", i%5+"","HR 2" + i));
        }


        // build tree
        TreeItem<User> root = new RecursiveTreeItem<User>(users, RecursiveTreeObject::getChildren);

        treeTableView = new JFXTreeTableView(root);
        treeTableView.setShowRoot(false);
        treeTableView.setEditable(true);
        treeTableView.getColumns().setAll(mondayColumn, tuesdayColumn, wednesdayColumn, thursdayColumn, fridayColumn, saturdayColumn, sundayColumn);

        JFXTextField filterField = new JFXTextField();
        filterField.textProperty().addListener((observable, old, newValue) -> {
            System.out.println("observable + old + newValue " + "POTATO");
        });

        itemCount.textProperty().bind(Bindings.createStringBinding(() -> treeTableView.getCurrentItemsCount() + "", treeTableView.currentItemsCountProperty()));





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

    }

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
}

class User extends RecursiveTreeObject<User> {
    StringProperty userName;
    StringProperty age;
    StringProperty department;

    public User(String department, String age, String userName) {
        this.department = new SimpleStringProperty(department) ;
        this.userName = new SimpleStringProperty(userName);
        this.age = new SimpleStringProperty(age);
    }
}


