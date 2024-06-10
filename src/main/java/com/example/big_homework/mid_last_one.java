package com.example.big_homework;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class mid_last_one {
    private AnchorPane last_one;
    private Button button;
    private TextField textField;
    mid_last_one(){ last_one = null;button = null;textField = null;}
    public AnchorPane getLast_one(){ return last_one;}
    public void setLast_one(AnchorPane a){ last_one = a;}

    public Button getButton() {
        return button;
    }

    public TextField getTextField() {
        return textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
