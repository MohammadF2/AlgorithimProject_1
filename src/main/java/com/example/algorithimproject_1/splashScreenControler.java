package com.example.algorithimproject_1;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class splashScreenControler {

    @FXML
    private Button ExitB;

    @FXML
    private Button StartB;

    @FXML
    void ExitOnAction(ActionEvent event) {
        Stage s = (Stage) ExitB.getScene().getWindow();
        s.close();
    }

    @FXML
    void StartOnAction(ActionEvent event) throws IOException {
        FXMLLoader gameScene = new FXMLLoader(HelloApplication.class.getResource("Game.fxml"));
        Scene scene = new Scene(gameScene.load(), 1280, 720);
        Stage s = (Stage) ExitB.getScene().getWindow();
        s.setScene(scene);

    }

}
