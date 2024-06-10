package com.example.big_homework;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class window_qiu {
    private final double max_x =  Screen.getPrimary().getVisualBounds().getMaxX();
    private double xOffset = 0;
    private double yOffset = 0;
    private int enterset = 2;
    private final Stage stage_qiu = new Stage();
    private ContextMenu contextMenu ;
    window_qiu(ContextMenu contextMenu1) throws Exception
    {
        URL url = getClass().getResource("/photos/狗头图标_小.png");
        stage_qiu.getIcons().add(new javafx.scene.image.Image(url.toExternalForm()));
        contextMenu = contextMenu1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homework_qiu.fxml"));

        Parent root_qiu = fxmlLoader.load();
        Scene scene_qiu = new Scene(root_qiu);
        scene_qiu.setFill(Color.TRANSPARENT);
        stage_qiu.setScene(scene_qiu);
        stage_qiu.initStyle(StageStyle.TRANSPARENT);
        stage_qiu.setAlwaysOnTop(true);

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


        root_qiu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.SECONDARY) && mouseEvent.getClickCount()==1)
                {
                    contextMenu.show(root_qiu,mouseEvent.getScreenX(),mouseEvent.getScreenY());
                }
            }
        });
    }

    public Stage getStage_qiu() {
        return stage_qiu;
    }

    public double getMax_x() {
        return max_x;
    }
}
