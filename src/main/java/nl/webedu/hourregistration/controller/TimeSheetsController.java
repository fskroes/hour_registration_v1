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
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeSheetsController {

    public JFXButton manageEmployeesButton;
    private EmployeeModel sessionEmployee;
    private EmployeeModel activeEmployee;

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
    public JFXButton manageCustomersButton;
    @FXML
    public JFXButton manageProjectsButton;
    @FXML
    public JFXComboBox cmFromWeek;
    @FXML
    public JFXComboBox cmUntilWeek;
    @FXML
    public Label labelNaam;

    @FXML
    public void initialize() {
        Calendar cal = Calendar.getInstance();
        int weeknr = cal.get(Calendar.WEEK_OF_YEAR) - 1;
        for (int i = 1; i < 53; i++) {
            cmFromWeek.getItems().add(i);
            cmUntilWeek.getItems().add(i);
        }
        cmFromWeek.getSelectionModel().select(weeknr - 10);
        cmUntilWeek.getSelectionModel().select(weeknr);

        manageEmployeesButton.setOnAction(event -> {
            Stage primaryStage = new Stage();
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
    }

    private void setupUserInterface(EmployeeModel employee) {
        for (int i = (int) cmFromWeek.getValue(); i <= (int) cmUntilWeek.getValue(); i++) {
            long elapsedHours = 0;
            for (WorkdayModel workday : activeEmployee.getWorksdaysByWeekNumber(i)) {
                long dayDifference = ChronoUnit.HOURS.between(workday.getStartTime(), workday.getEndTime());
                elapsedHours =+ dayDifference;
            }

            Calendar startDate = Calendar.getInstance();
            startDate.set(Calendar.WEEK_OF_YEAR, i);
            startDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

            Calendar endDate = Calendar.getInstance();
            endDate.set(Calendar.WEEK_OF_YEAR, i);
            endDate.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);

            timesheetEntry(i, startDate.getTime(), endDate.getTime(), elapsedHours, 0);
        }
        labelNaam.setText("Overzicht van: " + activeEmployee.getLastname()+ ", " + activeEmployee.getFirstname());
    }

    /**
     * Creating a timesheet or week, that opens a timesheet
     * @param weekId
     * @param startDate
     * @param endDate
     * @param TotalHours
     * @param OverTime
     */
    private void timesheetEntry(int weekId, Date startDate, Date endDate, double TotalHours, double OverTime) {
        HBox itemWrapper = new HBox(48);
        itemWrapper.setFillHeight(true);
        itemWrapper.setAlignment(Pos.CENTER_LEFT);

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMM yyyy");

        VBox dateWrapper = new VBox();
        dateWrapper.setPadding(new Insets(12));
        dateWrapper.setSpacing(5D);
        dateWrapper.setAlignment(Pos.CENTER);

        Label lblDateToDate = new Label(sdf.format(startDate) + " - " + sdf.format(endDate));
        lblDateToDate.setFont(Font.font("System", 16));
        Label lblStatus = new Label("Status");
        lblStatus.setFont(Font.font("System", 10));
        dateWrapper.getChildren().add(lblDateToDate);
        dateWrapper.getChildren().add(lblStatus);

        VBox timeWorked = new VBox();
        timeWorked.setPadding(new Insets(12));
        timeWorked.setSpacing(5D);
        timeWorked.setAlignment(Pos.CENTER);

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

        Label lblOvertime = new Label("Overtime");
        lblOvertime.setFont(Font.font("System", 16));
        Label lblHoursOvertime = new Label(Double.toString(OverTime));
        lblHoursOvertime.setFont(Font.font("System", 10));
        overtimeWorked.getChildren().add(lblOvertime);
        overtimeWorked.getChildren().add(lblHoursOvertime);

        JFXButton btnTimeSheet = new JFXButton("View timesheet");
        btnTimeSheet.setStyle("-fx-border-color: #4285F4; -fx-border-width: 1px;");
        btnTimeSheet.setAlignment(Pos.CENTER);
        btnTimeSheet.setOnAction(event -> {
            Stage timesheet = new Stage();
            timesheet.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TimesheetView.fxml"));

            Parent parent = null;
            try {
                parent = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert parent != null;

            TimesheetController controller = loader.getController();
            controller.postConstructor(sessionEmployee, activeEmployee, weekId);

            Scene scene = new Scene(parent, 1200, 800);
            timesheet.setScene(scene);
            timesheet.show();
        });



        itemWrapper.getChildren().add(dateWrapper);
        itemWrapper.getChildren().add(timeWorked);
        itemWrapper.getChildren().add(overtimeWorked);
        itemWrapper.getChildren().add(btnTimeSheet);

        lvTimeSheets.getItems().add(itemWrapper);
    }

    private void refreshTimesheets(EmployeeModel employee) {

    }

    /**
     * Manually called after FXML is loaded, because FXML used empty contructor
     * @param sessionEmployee
     */
    public void postConstructor(EmployeeModel sessionEmployee) {
        this.sessionEmployee = sessionEmployee;
        roleProperties();
        setupUserInterface(this.activeEmployee);
    }

    /**
     * Determine what role the loggedin user has and fill the combobox
     */
    private void roleProperties() {
        if (!sessionEmployee.getRole().equals(Role.ADMIN)) {
            cmEmployees.setVisible(false);
        } else {
            List<EmployeeModel> employeeModels = DatabaseManager.getInstance().getDaoFactory().getEmployeeDAO().getAllEmployees();
            for (EmployeeModel employeeModel : employeeModels) {
                if (employeeModel.getId().equals(sessionEmployee.getId())) {
                    sessionEmployee = employeeModel;
                    activeEmployee = employeeModel;
                }
            }
            ObservableList<EmployeeModel> employeeObLst = FXCollections.observableArrayList(employeeModels);
            cmEmployees.setItems(employeeObLst);
            cmEmployees.getSelectionModel().select(sessionEmployee);
        }
    }

    public void onManageCustomers (ActionEvent actionEvent) {
        Stage primaryStage = new Stage();
        primaryStage.hide();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/CustomerView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert parent != null;
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void onManageProjects (ActionEvent actionEvent) {
        Stage primaryStage = new Stage();
        primaryStage.hide();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/ProjectView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert parent != null;
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void onManageContracts (ActionEvent actionEvent) {
        Stage primaryStage = new Stage();
        primaryStage.hide();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/ContractView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert parent != null;
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void onFromChange(ActionEvent actionEvent) {
        lvTimeSheets.getItems().clear();
        setupUserInterface(activeEmployee);
    }

    public void onUntilChange(ActionEvent actionEvent) {
        lvTimeSheets.getItems().clear();
        setupUserInterface(activeEmployee);
    }

    public void onItemChange(ActionEvent actionEvent) {
        activeEmployee = (EmployeeModel) cmEmployees.getSelectionModel().getSelectedItem();
        lvTimeSheets.getItems().clear();
        setupUserInterface(activeEmployee);
    }
}
