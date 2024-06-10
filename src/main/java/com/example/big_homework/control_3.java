package com.example.big_homework;

import javafx.css.Size;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class control_3
{
    @FXML
    private AnchorPane anchorpane_qiu;
//    private ContextMenu contextMenu;

    public control_3()
    {
//        System.out.println("control3被构建");

    }
    @FXML
    public void initialize()
    {
//        System.out.println("初始化完毕");

    }
    public AnchorPane getAnchorpane_qiu() {
        return anchorpane_qiu;
    }

    public void setAnchorpane_qiu(AnchorPane anchorpane_qiu) {
        this.anchorpane_qiu = anchorpane_qiu;
    }
}
