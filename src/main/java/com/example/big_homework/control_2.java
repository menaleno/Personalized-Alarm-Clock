package com.example.big_homework;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class control_2
{
    @FXML
    private ComboBox<String> time_style;
    @FXML
    private ComboBox<String> time_hour;
    @FXML
    private ComboBox<String> time_min;


    @FXML
    public void initialize()
    {
        time_style.getItems().addAll("闹钟","倒计时");
        for(int i=0;i<24;i++)
        {
            time_hour.getItems().add(String.valueOf(i));
        }


        for (int i=0;i<60;i++)
        {
            time_min.getItems().add(String.valueOf(i));
        }
        time_min.getSelectionModel().select("0");
        time_hour.getSelectionModel().select("0");
        time_style.getSelectionModel().select("闹钟");

        time_hour.setVisibleRowCount(12);
        time_min.setVisibleRowCount(10);

    }
}
