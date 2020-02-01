package com.company;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main  extends Application {

    private volatile boolean control;

    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("Starting...");
        control = false;
    }

    private Text time = new Text();

    private Thread timer = new Thread(() -> {
        SimpleDateFormat sdt = new SimpleDateFormat("hh:mm:ss");
        while (!control){
            try {
                Thread.sleep(1000);
                final String realTime = sdt.format(new Date());

                Platform.runLater(() -> time.setText(realTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    @Override
    public void start(Stage primaryStage){
        BorderPane root = new BorderPane();
        root.setCenter(time);
        primaryStage.setScene(new Scene(root, 250, 100));
        primaryStage.initStyle(StageStyle.UTILITY);

        timer.start();
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("Stopping...");
        control = true;
    }
}
