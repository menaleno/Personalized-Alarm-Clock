package com.example.big_homework;

public class set {
    private String button_set;
    private String style_set;
    private String hour_set;
    private String minute_set;
    private String radio_set;
    private String file_set;
    private String describe_set;

    public String getButton_set() {
        return button_set;
    }

    public void setButton_set(String button_set) {
        this.button_set = button_set;
    }

    public String getStyle_set() {
        return style_set;
    }

    public void setStyle_set(String style_set) {
        this.style_set = style_set;
    }

    public String getHour_set() {
        return hour_set;
    }

    public void setHour_set(String hour_set) {
        this.hour_set = hour_set;
    }

    public String getMinute_set() {
        return minute_set;
    }

    public void setMinute_set(String minute_set) {
        this.minute_set = minute_set;
    }

    public String getRadio_set() {
        return radio_set;
    }

    public void setRadio_set(String radio_set) {
        this.radio_set = radio_set;
    }

    public String getFile_set() {
        return file_set;
    }

    public void setFile_set(String file_set) {
        this.file_set = file_set;
    }

    public String getDescribe_set() {
        return describe_set;
    }

    public void setDescribe_set(String describe_set) {
        this.describe_set = this.describe_set+describe_set;
    }
    public void add(String describe_set){ this.describe_set = this.describe_set+'\n'+describe_set;}
    public void clear(){this.describe_set = "";}
}
