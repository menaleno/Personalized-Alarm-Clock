package com.example.big_homework;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.*;
import javafx.scene.layout.*;

import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import javafx.stage.WindowEvent;

import javax.imageio.IIOException;
import javax.xml.xpath.XPath;
import java.awt.*;


import java.awt.event.ActionListener;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class homework extends Application {

    private ContextMenu contextMenu  = new ContextMenu();
    private mid_last_one last_one = new mid_last_one();
    private window_2 delete_one;
    private VBox vBox_left;
    private  Timer timer = new Timer();
    public void start(Stage stage) throws Exception
    {

        window_qiu qiu = new window_qiu(contextMenu);


//下面为主窗口设置！！！！！！！！！！！！！！！！！！
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("homework_1.fxml"));
        AnchorPane root1 = (AnchorPane) fxmlLoader1.load();
        Scene scene = new Scene(root1);
        root1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.SECONDARY)&&mouseEvent.getClickCount()==1)
                {
                    contextMenu.show(root1,mouseEvent.getScreenX(),mouseEvent.getScreenY());
                }
            }
        });






//下面为右键菜单设置！！！！！！！！！！！！！！！！！！！
        contextMenu.setStyle("-fx-background-color:#363636");
        MenuItem menuItem1 = new MenuItem("切换到挂件模式");
        menuItem1.setStyle("-fx-color:#000000");
        MenuItem menuItem2 = new MenuItem("最小化到托盘");
        menuItem2.setStyle("-fx-color:#000000");
        contextMenu.getItems().addAll(menuItem1,menuItem2,new MenuItem("close"));
        contextMenu.getItems().get(2).setStyle("-fx-color:#000000");
        contextMenu.getItems().get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(menuItem1.getText().equals("切换到挂件模式"))
                {
                    stage.close();
                }
                else
                {
                    qiu.getStage_qiu().close();
                }
                timer.cancel();
                saving(vBox_left);
                output();
                Platform.exit();
            }
        });
        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (menuItem1.getText().equals("切换到挂件模式"))
                        {
                            menuItem1.setText("切换回窗口界面");
                            qiu.getStage_qiu().show();
                            qiu.getStage_qiu().setX(qiu.getMax_x()-198);
                            qiu.getStage_qiu().setY(200);
                            stage.close();
                        }
                        else
                        {
                            menuItem1.setText("切换到挂件模式");
                            stage.show();
                            qiu.getStage_qiu().close();
                        }


                    }
                });
            }
        });

//这里新增关闭时转换为挂件的功能
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                menuItem1.setText("切换回窗口界面");
                qiu.getStage_qiu().show();
                qiu.getStage_qiu().setX(qiu.getMax_x()-198);
                qiu.getStage_qiu().setY(200);
                stage.close();
            }
        });

//下面为最小化到系统托盘的实现！！！！！！！！！！
        Platform.setImplicitExit(false);

        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SystemTray systemTray = SystemTray.getSystemTray();
                java.awt.Image j_image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/photos/狗头图标_小.png"));
                String tuopan = "闹钟托盘";
                PopupMenu  popupMenu = new PopupMenu();
                java.awt.MenuItem j_item_1 = new java.awt.MenuItem("return");
                java.awt.MenuItem j_item_2 = new java.awt.MenuItem("exit");
                popupMenu.add(j_item_1);
                popupMenu.add(j_item_2);
                TrayIcon trayIcon = new TrayIcon(j_image,tuopan,popupMenu);
                if (menuItem1.getText().equals("切换到挂件模式"))
                {
                    stage.close();
                }
                else
                {
                    menuItem1.setText("切换到挂件模式");
                    qiu.getStage_qiu().close();
                }

                //托盘添加新图标
                try {
                    systemTray.add(trayIcon);
                }
                catch (AWTException e)
                {
                    e.printStackTrace();
                }


                //托盘菜单返回按钮
                j_item_1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                stage.show();
                                systemTray.remove(trayIcon);
                            }
                        });
                    }
                });

                j_item_2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        systemTray.remove(trayIcon);
                        timer.cancel();
                        saving(vBox_left);
                        Platform.exit();
                    }
                });
            }
        });

//下面是面板的设计！！！！！！！！！！！！！
        BorderPane borderPane_1 = (BorderPane) root1.getChildren().get(1);
        MenuBar menuBar = (MenuBar) borderPane_1.getTop();

        Menu bar_menu =menuBar.getMenus().get(0);
        MenuItem bar_item = bar_menu.getItems().get(0);
        bar_item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timer.cancel();
                saving(vBox_left);
                stage.close();
                Platform.exit();
            }
        });
        bar_menu = menuBar.getMenus().get(1);
        bar_item = bar_menu.getItems().get(0);
        bar_item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage help = new Stage();
                help.setTitle("帮助文档");
                TextArea textArea = new TextArea("##############\n" +
                        "本程序提供闹钟服务\n" +
                        "1.点击“添加”按钮，新建闹钟，双机名字可以修改，修改完毕按下Enter键确认\n" +
                        "2.点击\"删除\"按钮，删除当前闹钟（按钮亮着的那一个）\n" +
                        "3.闹钟分两种模式，即“闹钟”模式和“倒计时”模式，\n" +
                        "  闹钟模式：设置时间为 **时**分，若当天的时间已过，则为第二天该时间\n" +
                        "  倒计时模式：时间设置为 **时**分，从闹钟确定时开始倒计时（注意，倒计时时间不能为0，即0时0分，否则无法开始）\n" +
                        "4.选择闹钟形式，有音频，图片，以及视频三种模式\n" +
                        "5.选择模式之后，点击选择文件进行闹钟文件选取，这里音频支持mp3格式，图片支持png、jpg、gif格式，视频支持mp4格式\n" +
                        "*6.你可以在备注栏里写下备注，在闹钟运行时会有提示。\n" +
                        "7.点击确定，闹钟开始工作\n" +
                        "\n" +
                        "8.闹钟提供屏幕边缘的隐藏功能，将主窗口关闭后，会切换至副窗口模式，将其拖至屏幕边缘，会自动收起，类似于加速球效果\n" +
                        "9.若你不想使用主窗口或副窗口，可以右键点击最小化至托盘，程序会收到托盘中，若想返回，只需对托盘图标点击return 即可\n" +
                        "10.本程序会自动保存，用户直线关闭即可，下次打开时会读取上次的内容\n" +
                        "！！！11.请注意，若想关闭，只需右键点击 close 退出即可\n" +
                        "\n" +
                        "祝您使用愉快！\n" +
                        "###############");
                help.setScene(new Scene(textArea));
                URL url = getClass().getResource("/photos/狗头图标_小.png");
                help.getIcons().add(new javafx.scene.image.Image(url.toExternalForm()));
                help.show();
            }
        });

        AnchorPane anchorPane_center = (AnchorPane)borderPane_1.getCenter();
        AnchorPane anchorPane_left = (AnchorPane) borderPane_1.getLeft();
        javafx.scene.control.ScrollPane scrollPane = (javafx.scene.control.ScrollPane) anchorPane_left.getChildren().get(2);
        vBox_left = (VBox) scrollPane.getContent();
        javafx.scene.control.Button add_button = (javafx.scene.control.Button) anchorPane_left.getChildren().get(0);
        javafx.scene.control.Button del_button = (javafx.scene.control.Button) anchorPane_left.getChildren().get(1);
        scrollPane.setStyle("-fx-font-size: 18px;");
        BackgroundFill bgf = new BackgroundFill(Paint.valueOf("#473C8B"),new CornerRadii(10),new Insets(10,10,10,10));//第一个参数为颜色,第二个参数为倒角,第三个为内敛
        Background bg = new Background(bgf);
        scrollPane.setBackground(bg);
        add_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    delete_one = new window_2(last_one,timer);
                    vBox_left.getChildren().add(delete_one.getAnchorPane_button());
                    if(last_one.getLast_one()!=null)
                    {
                        last_one.getLast_one().setVisible(false);
                        last_one.getButton().setOpacity(0.25);
                    }
                    anchorPane_center.getChildren().add(delete_one.getAnchorPane_2());
                    last_one.setLast_one(delete_one.getAnchorPane_2());
                    last_one.setButton(delete_one.getButton());
                    last_one.setTextField(delete_one.getTextField());

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });

        del_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(last_one.getLast_one()!=null)
                {
                    anchorPane_center.getChildren().remove(last_one.getLast_one());
                    vBox_left.getChildren().remove(last_one.getButton().getUserData());
                }
            }
        });






//快捷键设置！！！！！！！！！！
        KeyCombination kcb2 = new KeyCodeCombination(KeyCode.ENTER);
        scene.getAccelerators().put(kcb2, new Runnable() {
            @Override
            public void run() {

                if(last_one.getTextField()!=null&&last_one.getTextField().isFocused())
                {
                    last_one.getButton().setText(last_one.getTextField().getText());
                    last_one.getTextField().setVisible(false);
                }

            }
        });


        inputting(anchorPane_center);

        URL url = getClass().getResource("/photos/狗头图标_小.png");
        stage.getIcons().add(new javafx.scene.image.Image(url.toExternalForm()));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();


    }


    public void saving(VBox vBox)
    {

        File data = new File(System.getProperty("user.dir")+"\\data.txt") ;
        PrintWriter printWriter =null;
        AnchorPane button_pane;
        AnchorPane right_pane;
        try {
            printWriter = new PrintWriter(data);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        for (int i=0;i<vBox.getChildren().size();i++)
        {

            button_pane = (AnchorPane) vBox.getChildren().get(i);
            right_pane = (AnchorPane) button_pane.getUserData();

            printWriter.println(((javafx.scene.control.Button) button_pane.getChildren().get(0)).getText());
            printWriter.println(((ComboBox)right_pane.getChildren().get(3)).getSelectionModel().getSelectedItem());
            printWriter.println(((ComboBox) ((AnchorPane)right_pane.getChildren().get(4)).getChildren().get(2)).getSelectionModel().getSelectedItem());
            printWriter.println(((ComboBox) ((AnchorPane)right_pane.getChildren().get(4)).getChildren().get(3)).getSelectionModel().getSelectedItem());
            printWriter.println(((RadioButton) right_pane.getChildren().get(6)).getUserData());
            printWriter.println((File)((Label) right_pane.getChildren().get(13)).getUserData());
            printWriter.println(((TextArea) right_pane.getChildren().get(10)).getText());
            printWriter.println("haha_this_the_end");

        }
        printWriter.close();
    }
    private void output()
    {
        File file = new File(System.getProperty("user.dir")+"\\data.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine())
            {
                System.out.println(scanner.nextLine());
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

//
//    private String help_get()
//    {
//        File file = new File(getClass().getResourceAsStream("help.txt").toString());
//        Scanner scanner = null;
//        String get = "";
//        try {
//            scanner = new Scanner(file);
//            if (scanner.hasNextLine())
//            {
//                get = get+scanner.nextLine();
//            }
//            while (scanner.hasNextLine())
//            {
//                get = get+'\n'+scanner.nextLine();
//            }
//        }
//        catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//        return get;
//    }


    private void inputting(AnchorPane anchorPane_center)
    {
        File dir = new File("");
        String path = null;
        try {
            path = dir.getCanonicalPath();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        path += "\\data.txt";
        File file = new File(path);
        if (file.exists())
        {
            Scanner scanner;
            String getting;
            set sets = new set();
            try {
                scanner = new Scanner(file);
                while (scanner.hasNextLine())
                {
                    sets.setButton_set(scanner.nextLine());
                    sets.setStyle_set(scanner.nextLine());
                    sets.setHour_set(scanner.nextLine());
                    sets.setMinute_set(scanner.nextLine());
                    sets.setRadio_set(scanner.nextLine());
                    sets.setFile_set(scanner.nextLine());
                    getting = scanner.nextLine();
                    sets.clear();
                    sets.setDescribe_set(getting);
                    getting = scanner.nextLine();
                    while (!getting.equals("haha_this_the_end"))
                    {
                        sets.add(getting);
                        getting = scanner.nextLine();
                    }

                    try {
                        delete_one = new window_2(last_one,timer);
                        delete_one.sets(sets);
                        vBox_left.getChildren().add(delete_one.getAnchorPane_button());
                        if(last_one.getLast_one()!=null)
                        {
                            last_one.getLast_one().setVisible(false);
                            last_one.getButton().setOpacity(0.25);
                        }
                        anchorPane_center.getChildren().add(delete_one.getAnchorPane_2());
                        last_one.setLast_one(delete_one.getAnchorPane_2());
                        last_one.setButton(delete_one.getButton());
                        last_one.setTextField(delete_one.getTextField());

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                scanner.close();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        else {
            try {
                file.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
