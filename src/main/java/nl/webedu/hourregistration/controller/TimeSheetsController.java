package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import nl.webedu.hourregistration.database.DatabaseManager;
import nl.webedu.hourregistration.enumeration.Role;
import nl.webedu.hourregistration.model.EmployeeModel;
import nl.webedu.hourregistration.model.WorkdayModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class TimeSheetsController {

    public JFXButton manageEmployeesButton;
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
    @FXML
    public JFXComboBox cmEmployees;
    @FXML
    public JFXComboBox cmFromWeek;
    @FXML
    public JFXComboBox cmUntilWeek;

    @FXML
    public JFXButton manageCustomersButton;
    @FXML
    public JFXButton manageProjectsButton;
    @FXML
    public void initialize() {
        Calendar cal = Calendar.getInstance();
        int weeknr = cal.get(Calendar.WEEK_OF_YEAR) - 1;
        for (int i = 1; i < 53; i++) {
            cmFromWeek.getItems().add(i);
            cmUntilWeek.getItems().add(i);
        }
        cmFromWeek.getSelectionModel().selectFirst();
        cmUntilWeek.getSelectionModel().select(weeknr);
    }

    private void setupUserInterface(EmployeeModel employee) {
        for (int i = (int) cmFromWeek.getValue(); i <= (int) cmUntilWeek.getValue(); i++) {

            long elapsedHours = 0;

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMM yyyy");

            Calendar startDate = Calendar.getInstance();
            startDate.set(Calendar.WEEK_OF_YEAR, i);
            startDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

            Calendar endDate = Calendar.getInstance();
            endDate.set(Calendar.WEEK_OF_YEAR, i);
            endDate.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);

            System.out.println(sdf.format(startDate.getTime()) + " - " + sdf.format(endDate.getTime()));

            List<WorkdayModel> workdays = employee.getWorksdaysByWeekNumber(i);
            for (WorkdayModel workday : workdays) {
                Long different =+ workday.getStartTime().getTime() - workday.getEndTime().getTime();
                elapsedHours = different / hoursInMilli;
            }
            timesheetEntry(sdf.format(startDate.getTime()), sdf.format(endDate.getTime()), elapsedHours, 0);
        }

    }

    private void timesheetEntry(String startDate, String endDate, double TotalHours, double OverTime) {
        HBox itemWrapper = new HBox(48);
        itemWrapper.setFillHeight(true);

        VBox dateWrapper = new VBox();
        dateWrapper.setPadding(new Insets(12));
        dateWrapper.setSpacing(5D);
        dateWrapper.setAlignment(Pos.CENTER);
//        dateWrapper.setStyle("-fx-border-color: crimson; -fx-border-width: 1px;");

        Label lblDateToDate = new Label(startDate + " - " + endDate);
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
        Label lblHoursWorked = new Label( Double.toString(TotalHours));
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
        Label lblHoursOvertime = new Label(Double.toString(OverTime));
        lblHoursOvertime.setFont(Font.font("System", 10));
        overtimeWorked.getChildren().add(lblOvertime);
        overtimeWorked.getChildren().add(lblHoursOvertime);

        //

        JFXButton btnTimeSheet = new JFXButton("View timesheet");
        btnTimeSheet.setStyle("-fx-border-color: #4285F4; -fx-border-width: 1px;");
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

        manageEmployeesButton.setOnAction(event -> {
            Stage primaryStage = (Stage) root.getScene().getWindow();
            primaryStage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/RollenView.fxml"));

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

    private void refreshTimesheets(EmployeeModel employee) {

    }

    public void setSessionEmployee(EmployeeModel sessionEmployee) {
        this.sessionEmployee = sessionEmployee;
        roleProperties();
        this.sessionEmployee.getWorkdays();
        setupUserInterface(this.sessionEmployee);
    }

    private void roleProperties() {
        if (!sessionEmployee.getRole().equals(Role.ADMIN)) {
            cmEmployees.setVisible(false);
        } else {
            List<EmployeeModel> employeeModels = DatabaseManager.getInstance().getDaoFactory().getEmployeeDAO().selectAllEmployees();
            for (EmployeeModel employeeModel : employeeModels) {
                if (employeeModel.getId().equals(sessionEmployee.getId())) {
                    sessionEmployee = employeeModel;
                }
            }
            ObservableList<EmployeeModel> employeeObLst = FXCollections.observableArrayList(employeeModels);
            cmEmployees.setItems(employeeObLst);
            cmEmployees.getSelectionModel().select(sessionEmployee);
        }
    }

    public void onItemChange(ActionEvent actionEvent) {

    }

    @FXML
    public void onFromChange(ActionEvent actionEvent) {
        lvTimeSheets.getItems().clear();
        setupUserInterface((EmployeeModel) cmEmployees.getSelectionModel().getSelectedItem());
    }

    public void onUntilChange(ActionEvent actionEvent) {
    }
}
