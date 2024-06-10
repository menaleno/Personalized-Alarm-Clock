package com.example.big_homework;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class homework_text extends Application {
    public void main(){
        launch();
    }
    private double xOffset = 0;
    private double yOffset = 0;
    private int enterset = 0;
    private final double max_x =  Screen.getPrimary().getVisualBounds().getMaxX();
    private ContextMenu contextMenu  = new ContextMenu();
    public void start(Stage stage) throws Exception
    {
        //        Parent root = FXMLLoader.load(getClass().getResource("homework_qiu.fxml"));
//        root.setOnMousePressed(event -> {
//            xOffset = event.getSceneX();
//            yOffset = event.getSceneY();
//        });
//
//        root.setOnMouseDragged(event -> {
//            stage.setX(event.getScreenX() - xOffset);
//            stage.setY(event.getScreenY() - yOffset);
//        });
//

//        Platform.setImplicitExit(false);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homework_qiu.fxml"));
        Parent root_qiu = fxmlLoader.load();

        Stage stage_qiu = new Stage();
        Scene scene_qiu = new Scene(root_qiu);
        stage_qiu.setAlwaysOnTop(true);
        stage_qiu.setScene(scene_qiu);
        scene_qiu.setFill(Color.TRANSPARENT);
        stage_qiu.initStyle(StageStyle.TRANSPARENT);
        contextMenu.getItems().add(new MenuItem("close"));
        contextMenu.getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage_qiu.close();
            }
        });


        //球形拖拽
        root_qiu.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                xOffset = mouseEvent.getSceneX();
                yOffset = mouseEvent.getSceneY();
//                System.out.println(xOffset+","+yOffset);
            }
        });

        root_qiu.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage_qiu.setX(mouseEvent.getScreenX()-xOffset);
                stage_qiu.setY(mouseEvent.getScreenY()-yOffset);
            }
        });

        //设置球形挂件的收起
        root_qiu.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(stage_qiu.getX()<=0)
                {
                    enterset = 1;
                }
                else if (stage_qiu.getX()+200>=max_x)
                {
                    enterset = 2;
                }
                else if (stage_qiu.getY()<=0)
                {
                    enterset = 3;
                }
                else
                {
                    enterset = 0;
                }
            }
        });

        root_qiu.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (enterset == 1)
                {
                    stage_qiu.setX(-195);
                    if (stage_qiu.getY()<=0)
                    {
                        stage_qiu.setY(0);
                    }
                }
               else if (enterset == 2)
                {
                    stage_qiu.setX(max_x-5);
                    if (stage_qiu.getY()<=0)
                    {
                        stage_qiu.setY(0);
                    }
                }
                else if (enterset==3)
                {
                    stage_qiu.setY(-195);
                }
            }
        });

        //设置鼠标边界检测
        root_qiu.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (enterset == 1)
                {
                    stage_qiu.setX(-6);
                }
                else if (enterset == 2)
                {
                    stage_qiu.setX(max_x-194);
                }
                else if (enterset==3)
                {
                    stage_qiu.setY(-6);
                }
            }
        });

        contextMenu.setStyle("-fx-background-color:#473C8B");
        root_qiu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.SECONDARY) && mouseEvent.getClickCount()==1)
                {

                    contextMenu.show(root_qiu,mouseEvent.getScreenX(),mouseEvent.getScreenY());
                }
            }
        });



        Scene scene = new Scene(new AnchorPane());
        stage.setScene(scene);
        stage.show();
        stage_qiu.show();


    }
}
