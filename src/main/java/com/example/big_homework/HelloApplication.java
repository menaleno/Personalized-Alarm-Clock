package com.example.big_homework;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Stream;

public class HelloApplication extends Application {


    private LocalDateTime ldt1 = LocalDateTime.now();
    public void start(Stage stage) throws IOException {
        ClassLoader  classLoader = HelloApplication.class.getClassLoader();
//        InputStream inputStream =classLoader.getResourceAsStream("data/data.txt");
        File file = new File("");

        String path = null;
        try {
            path = file.getCanonicalPath();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        path +="\\datas\\data.txt";
        File file1 = new File(path);
        System.out.println(file1.getAbsolutePath());
        if(!file1.exists()&&!file1.isDirectory())
        {
            System.out.println("不存在!");
            file1.mkdir();
        }
        else
        {
            System.out.println("已存在！");
        }


//
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        VBox vBox = fxmlLoader.load();
//        Button b1 = new Button("b1");
//        vBox.getChildren().add(b1);
//
//        Scene scene = new Scene(vBox);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//        LocalDateTime  localTime = LocalDateTime.now().withHour(15).withMinute(40);
//        if(localTime.isBefore(LocalDateTime.now()))
//        {
//            System.out.println(true);
//            System.out.println(localTime);
//            localTime = localTime.plusDays(1);
//            System.out.println("!!!!!!!!!!!!");
//            System.out.println(localTime);
//        }
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//
////                ldt1 = ldt1.plusHours(30);
////                ldt1 = ldt1.plusMinutes(30);
////                ldt1 = ldt1.plusDays(2);
////                System.out.println(ldt1);
////                System.out.println(localTime);
//            }
//        };
//
//        long delay = 2000;
//        Timer timer = new Timer(true);
//
//
//        System.out.println(ldt1);
//
////        timer.schedule(timerTask,delay);
//        b1.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                timerTask.run();
//            }
//        });
//        Stream.of(AudioSystem.getAudioFileTypes()).forEach(e -> {System.out.println(e.toString());});
//



    }

    public static void main(String[] args) {
        launch();
    }
}