package nl.webedu.hourregistration.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TimesheetController {

    @FXML
    private AnchorPane root;
    @FXML
    public VBox ContentContainer;

    @FXML
    public void initialize() {
        DatePicker datePicker = new DatePicker();

        ContentContainer.getChildren().add(datePicker);
        JFXDatePicker datePickerFX = new JFXDatePicker();

        ContentContainer.getChildren().add(datePickerFX);
        datePickerFX.setPromptText("pick a date");
        JFXTimePicker blueDatePicker = new JFXTimePicker();
        blueDatePicker.setDefaultColor(Color.valueOf("#3f51b5"));
        blueDatePicker.setOverLay(true);
        ContentContainer.getChildren().add(blueDatePicker);

    }

}
