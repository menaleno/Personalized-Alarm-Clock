package com.example.big_homework;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class window_2
{
    private AnchorPane anchorPane_2;  //右侧主界面
    private Button button = new Button(); //左侧闹钟按钮
    private AnchorPane anchorPane_button = new AnchorPane();  //闹钟按钮布局
    private TextField textField = new TextField("新建闹钟");
    private mid_last_one last_one;
    private MediaPlayer  mediaPlayer;
    //下面两个为时间⏲所用
    private int hour = 0;
    private int minute = 0;

    private int radio = 0;
    private File file =null;

    window_2(mid_last_one a, Timer timer) throws Exception
    {
        last_one = a;
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("homework.fxml"));
        anchorPane_2 = (AnchorPane)fxmlLoader2.load();
        button.setPrefSize(170,50);
        button.setTextFill(Paint.valueOf("#000000"));
        button.setText("新建闹钟");
        textField.setVisible(false);
        textField.setLayoutX(17);
        textField.setLayoutY(11);
        textField.setPrefSize(130,25);
        anchorPane_button.setPrefSize(170,50);
        anchorPane_button.getChildren().addAll(button,textField);

        //确定按钮
        Button going = (Button) anchorPane_2.getChildren().get(12);
        going.setTooltip(new Tooltip());
        //文件选择按钮以及标签
        javafx.scene.control.Label file_path = (Label) anchorPane_2.getChildren().get(13);
        file_path.setUserData(file);
        Button file_select = (Button) anchorPane_2.getChildren().get(14);




//        下面为按钮设置
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (last_one.getLast_one()!=null)
                {
                    last_one.getLast_one().setVisible(false);
                    last_one.getButton().setOpacity(0.25);
                }
                anchorPane_2.setVisible(true);
                button.setOpacity(1);
                last_one.setLast_one(anchorPane_2);
                last_one.setButton(button);
                last_one.setTextField(textField);

            }
        });

        //下面为名字修改功能
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)&&mouseEvent.getClickCount()==2)
                {
                    textField.setVisible(true);
                }
            }
        });
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (!t1)
                {
                    button.setText(textField.getText());
                    textField.setVisible(false);
                }
            }
        });

        //下面的存储数据会在保存时用到!!!!!!!!!!!!!!!!!!!!!!!!!
        anchorPane_button.setUserData(anchorPane_2);
        button.setUserData(anchorPane_button);

        //下面为时间的设置
        ComboBox comboBox_time_style = (ComboBox) anchorPane_2.getChildren().get(3);
        comboBox_time_style.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if(t1.equals("倒计时"))
                {
                    going.getTooltip().setText("请注意，倒计时时间不能为0");
                    if (hour==0&&minute==0)
                    {
                        going.setDisable(true);
                    }
                    else
                    {
                        going.setDisable(false);
                    }
                }
                else
                {
                    going.setDisable(false);
                    going.getTooltip().setText("若所设置的时间已过，则默认为第二天的这一时间");
                }
            }
        });
        ComboBox comboBox_hour = (ComboBox) ((AnchorPane)anchorPane_2.getChildren().get(4)).getChildren().get(2);
        comboBox_hour.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                hour = Integer.parseInt(t1.toString());

                if(going.getTooltip().getText().equals("请注意，倒计时时间不能为0"))
                {
                    if (hour==0&&minute==0)
                    {
                        going.setDisable(true);
                    }
                    else
                    {
                        going.setDisable(false);
                    }
                }
            }
        });
        ComboBox comboBox_minute =  (ComboBox) ((AnchorPane)anchorPane_2.getChildren().get(4)).getChildren().get(3);
        comboBox_minute.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                minute = Integer.parseInt(t1.toString());
                if(going.getTooltip().getText().equals("请注意，倒计时时间不能为0"))
                {
                    if (hour==0&&minute==0)
                    {
                        going.setDisable(true);
                    }
                    else
                    {
                        going.setDisable(false);
                    }
                }
            }
        });


//下面为闹钟响应方式设置
        RadioButton radio_music = (RadioButton) anchorPane_2.getChildren().get(6);
        radio_music.setUserData(radio);
        RadioButton radio_picture = (RadioButton) anchorPane_2.getChildren().get(7);
        RadioButton radio_mp = (RadioButton) anchorPane_2.getChildren().get(8);
        ToggleGroup toggleGroup = new ToggleGroup();
        radio_mp.setToggleGroup(toggleGroup);
        radio_music.setToggleGroup(toggleGroup);
        radio_picture.setToggleGroup(toggleGroup);
        radio_music.setSelected(true);


        radio_music.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                radio = 0;
                file_path.setText("请选择 mp3文件");
                radio_music.setUserData(radio);
            }
        });
        radio_picture.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                radio = 1;
                file_path.setText("请选择图片文件");
                radio_music.setUserData(radio);
            }
        });
        radio_mp.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                radio = 2;
                file_path.setText("请选择视频文件(mp4)");
                radio_music.setUserData(radio);
            }
        });



        //链接设置
        file_select.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage file_stage = new Stage();
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("文件选择");
                fileChooser.setInitialDirectory(new File("C:"+File.separator));
                if (radio==0)
                {
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("音频文件","*.mp3"));
                }
                else if (radio==1)
                {
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("图片文件","*.jpg","*.png","*.gif"));
                }
                else
                {
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("视频文件","*.mp4"));
                }
                file = fileChooser.showOpenDialog(file_stage);
                file_path.setUserData(file);
                if(file !=null) {
                    file_path.setText(file.getAbsolutePath());
                }
            }
        });





        going.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (file==null)
                {
                    file_path.setText("！！！！！请选择文件！！！！！1");
                }
                else
                {
                    //事件设置！！！！！！！！！！！！！！
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Stage stage_show = new Stage();
                                    URL url = getClass().getResource("/photos/狗头图标_小.png");
                                    stage_show.getIcons().add(new javafx.scene.image.Image(url.toExternalForm()));
                                    if (((TextArea) anchorPane_2.getChildren().get(10)).getText().equals(""))
                                    {
                                        stage_show.setTitle("叮叮叮，你的闹钟响喽！^V^");
                                    }
                                    else
                                    {
                                        stage_show.setTitle(((TextArea) anchorPane_2.getChildren().get(10)).getText());
                                    }
                                    AnchorPane anchorPane_show = new AnchorPane();
                                    if(radio==1)
                                    {
                                        try {
                                            ImageView imageView = new ImageView(new Image(file.toURI().toURL().toExternalForm(),800,600,true,true));
                                            anchorPane_show.getChildren().add(imageView);

                                            stage_show.setScene(new Scene(anchorPane_show));
                                            stage_show.show();
                                        }
                                        catch (MalformedURLException e)
                                        {
                                            e.printStackTrace();
                                        }
                                    }
                                    else
                                    if (radio==0)
                                    {
                                        Button button_start = new Button("开始");
                                        AnchorPane.setLeftAnchor(button_start,50.0);
                                        AnchorPane.setTopAnchor(button_start,50.0);
                                        Button button_stop = new Button("停止");
                                        AnchorPane.setLeftAnchor(button_stop,150.0);
                                        AnchorPane.setTopAnchor(button_stop,50.0);
                                        anchorPane_show.getChildren().addAll(button_start,button_stop);
                                        anchorPane_show.setPrefSize(240,124);
                                        stage_show.setScene(new Scene(anchorPane_show));
                                        mediaPlayer.setVolume(0.75);
                                        button_start.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {

                                                mediaPlayer.play();
                                            }

                                        });
                                        button_stop.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                mediaPlayer.pause();
                                            }
                                        });
                                        stage_show.show();
                                        mediaPlayer.setAutoPlay(true);
                                        stage_show.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                            @Override
                                            public void handle(WindowEvent event) {
                                                mediaPlayer.stop();
                                                mediaPlayer.dispose();
                                            }
                                        });
                                    }
                                    else
                                    {
                                        Button button_start = new Button("开始");
                                        AnchorPane.setLeftAnchor(button_start,50.0);
                                        AnchorPane.setTopAnchor(button_start,20.0);
                                        Button button_stop = new Button("停止");
                                        AnchorPane.setLeftAnchor(button_stop,150.0);
                                        AnchorPane.setTopAnchor(button_stop,20.0);
                                        anchorPane_show.getChildren().addAll(button_start,button_stop);
                                        anchorPane_show.setPrefSize(240,124);
                                        MediaView mediaView = new MediaView(mediaPlayer);
                                        mediaView.setFitWidth(1024);
                                        mediaView.setFitHeight(600);
                                        anchorPane_show.getChildren().add(mediaView);
                                        AnchorPane.setTopAnchor(mediaView,64.0);
                                        stage_show.setScene(new Scene(anchorPane_show));
                                        mediaPlayer.setVolume(0.75);
                                        button_start.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {

                                                mediaPlayer.play();
                                            }

                                        });
                                        button_stop.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                mediaPlayer.pause();
                                            }
                                        });
                                        stage_show.show();
                                        mediaPlayer.setAutoPlay(true);
                                        stage_show.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                            @Override
                                            public void handle(WindowEvent event) {
                                                mediaPlayer.stop();
                                                mediaPlayer.dispose();
                                            }
                                        });
                                    }
                                    going.setText("确定");
                                    comboBox_minute.setDisable(false);
                                    comboBox_hour.setDisable(false);
                                    comboBox_time_style.setDisable(false);
                                    radio_music.setDisable(false);
                                    radio_picture.setDisable(false);
                                    radio_mp.setDisable(false);
                                }
                            });

                        }
                    };




                    if (going.getText().equals("确定"))
                    {
                        going.setText("取消");
                        comboBox_minute.setDisable(true);
                        comboBox_hour.setDisable(true);
                        comboBox_time_style.setDisable(true);
                        radio_music.setDisable(true);
                        radio_picture.setDisable(true);
                        radio_mp.setDisable(true);

                        System.out.println("radio:"+radio);
                        if (radio !=1)
                        {
                            try
                            {
                                Media media = new Media(file.toURI().toURL().toExternalForm());
                                mediaPlayer = new MediaPlayer(media);
                            }
                            catch (MalformedURLException e) {
                                e.printStackTrace();
                            }

                        }

                        if(comboBox_time_style.getSelectionModel().getSelectedItem().equals("闹钟"))
                        {
                            System.out.println("时间格式: 闹钟");

                            LocalDateTime  localTime = LocalDateTime.now().withHour(hour).withMinute(minute).withSecond(0);
                            if(localTime.isBefore(LocalDateTime.now()))
                            {
                                localTime = localTime.plusDays(1);
                            }
                            Date date =Date.from(localTime.atZone(ZoneId.systemDefault()).toInstant());
                            System.out.println(date.toString());
                            timer.schedule(timerTask, date); //添加时区条件进行转换
                        }
                        else
                        {
                            System.out.println("时间格式: 倒计时");
                            LocalDateTime  localTime = LocalDateTime.now().plusHours(hour).plusMinutes(minute);
                            Date date =Date.from(localTime.atZone(ZoneId.systemDefault()).toInstant());
                            System.out.println("时间："+date.toString());
                            timer.schedule(timerTask,date); //添加时区条件进行转换


                        }

                    }
                    else
                    {
                        timerTask.cancel();
                        going.setText("确定");
                        comboBox_minute.setDisable(false);
                        comboBox_hour.setDisable(false);
                        comboBox_time_style.setDisable(false);
                        radio_music.setDisable(false);
                        radio_picture.setDisable(false);
                        radio_mp.setDisable(false);

                    }
                }

            }
        });





    }

    public AnchorPane getAnchorPane_2() {
        return anchorPane_2;
    }
    public AnchorPane getAnchorPane_button(){ return anchorPane_button;}
    public Button getButton(){ return button;}
    public TextField getTextField(){ return textField;}

    public void sets(set setting)
    {
        button.setText(setting.getButton_set());
        textField.setText(setting.getButton_set());
        ComboBox comboBox_time_style = (ComboBox) anchorPane_2.getChildren().get(3);
        comboBox_time_style.getSelectionModel().select(setting.getStyle_set());
        ComboBox comboBox_hour = (ComboBox) ((AnchorPane)anchorPane_2.getChildren().get(4)).getChildren().get(2);
        comboBox_hour.getSelectionModel().select(setting.getHour_set());
        ComboBox comboBox_minute = (ComboBox) ((AnchorPane)anchorPane_2.getChildren().get(4)).getChildren().get(3);
        comboBox_hour.getSelectionModel().select(setting.getMinute_set());
        RadioButton radio_music = (RadioButton) anchorPane_2.getChildren().get(6);
        radio_music.setUserData(radio);
        RadioButton radio_picture = (RadioButton) anchorPane_2.getChildren().get(7);
        RadioButton radio_mp = (RadioButton) anchorPane_2.getChildren().get(8);
        javafx.scene.control.Label file_path = (Label) anchorPane_2.getChildren().get(13);
        if (setting.getRadio_set().equals("0"))
        {
            radio=0;
            radio_music.setSelected(true);
            file_path.setText("请选择 mp3文件");
        }
        else if(setting.getRadio_set().equals("1"))
        {
            radio = 1;
            radio_picture.setSelected(true);
            file_path.setText("请选择图片");
        }
        else
        {
            radio = 2;
            radio_mp.setSelected(true);
            file_path.setText("请选择视频文件(mp4)");
        }

        if (setting.getFile_set().equals("null"))
        {
            file=null;
        }
        else
        {
            file = new File(setting.getFile_set());
            file_path.setText(file.getAbsolutePath());
        }
        TextArea textArea = (TextArea) anchorPane_2.getChildren().get(10);
        textArea.setText(setting.getDescribe_set());
    }
}
