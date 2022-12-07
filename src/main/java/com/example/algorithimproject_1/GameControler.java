package com.example.algorithimproject_1;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.Objects;


public class GameControler {

    @FXML
    private Button BackB;

    @FXML
    private TextField DataTF;

    @FXML
    private Button StartB;

    @FXML
    void backOnAction(ActionEvent event) throws IOException {

        Stage s = (Stage) BackB.getScene().getWindow();
        s.close();

    }

    @FXML
    void startOnAction(ActionEvent event) {

        Stage s = (Stage) BackB.getScene().getWindow();

        if (DataTF.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setContentText("Please enter the number of coins");
            a.show();
        } else {
            try {
                int number = Integer.parseInt(DataTF.getText());
                playing(s,number);
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("This filed can only contain integers");
                a.show();
            }
        }



    }


    int x1 = 1100;
    int index = 0;

    private void playing(Stage stage, int number){

        AnchorPane root = new AnchorPane();

        Label player1L = new Label("Player 1");
        player1L.setId("lables");
        player1L.setLayoutY(180);
        player1L.setLayoutX(1180);
        Label player2L = new Label("Player 2");
        player2L.setId("lables");
        player2L.setLayoutY(540);
        player2L.setLayoutX(1180);


        Button startB = new Button("Start");
        startB.setLayoutX(565);
        startB.setLayoutY(630);
        startB.setPrefSize(150,30);

        System.out.println("Here");

        TextField[] textFields = new TextField[number];

        System.out.println("here");
        int x = 1100, y = 360;

        root.getChildren().addAll(player1L,player2L, startB);


        for (int i = 0; i < textFields.length; i++){

            textFields[i] = new TextField();
            textFields[i].setLayoutX(x);
            textFields[i].setLayoutY(y);
            //textFields[i].setEditable(false);
            textFields[i].setPrefSize(50,50);
            root.getChildren().add(textFields[i]);
            System.out.println("here");
            x -=55;
        }





        startB.setOnAction(e -> {
            boolean hasFailed = false;
            for (int i = 0; i < textFields.length; i++){
               if (textFields[i].getText().equals("")){
                   Alert a = new Alert(Alert.AlertType.ERROR);
                   a.setTitle("Error");
                   a.setContentText("You should fill all textFields to start, number " + (i+1) + " is empty");
                   a.show();
                   hasFailed = true;
                   break;
               } else {
                   try {
                       int num = Integer.parseInt(textFields[i].getText());
                       textFields[i].setEditable(false);
                   } catch (Exception error) {
                       Alert a = new Alert(Alert.AlertType.ERROR);
                       a.setTitle("Error");
                       a.setContentText("all textFields should contain only integers to start, number " + (i+1) + " is has something else");
                       a.show();
                       hasFailed = true;
                       break;
                   }
               }
            }

            if (!hasFailed) {

                int[] arr = new int[number];
                for (int i = 0; i < textFields.length; i++){
                    arr[i] = Integer.parseInt(textFields[i].getText());
                }




                int table[][] = new int[number][number];
                int gap, i, j, w, q, z;
                for (gap = 0; gap < number; ++gap) {
                    for (i = 0, j = gap; j < number; ++i, ++j) {

                        if((i + 2) <= j)
                            w = table[i + 2][j];
                        else
                            w = 0;
                        if((i + 1) <= (j - 1))
                            q = table[i + 1][j - 1];
                        else
                            q = 0;
                        if (i <= (j - 2))
                            z = table[i][j - 2];
                        else
                            z = 0;

                        table[i][j] = Math.max(arr[i] + Math.min(w, q), arr[j] + Math.min(q, z));

                    }
                }


                int n1=0;
                int n2=0;


                for (int o = table.length-1; o-1 >= 0; o-=2){
                    n1+=table[o][o];
                }
                for (int o = 0; o < table.length; o+=2){
                    n2+=table[o][o];
                }


                    System.out.println("here2");
                    for (int o = number-1; o >=0; o-=2){
                        TranslateTransition tr = new TranslateTransition();
                        System.out.println("here2");
                        tr.setDuration(Duration.seconds(2));
                        tr.setToY(170);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        tr.setNode(textFields[o]);
                        tr.play();
                    }

                    for (int o = number-2; o >=0; o-=2){
                        TranslateTransition tr = new TranslateTransition();
                        System.out.println("here3");
                        tr.setDuration(Duration.seconds(2));
                        tr.setToY(-170);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        tr.setNode(textFields[o]);
                        tr.play();
                    }


                System.out.println(table[0][number - 1]);

            }

        });





        Scene scene = new Scene(root, 1280,720);
        scene.getStylesheets().add( Objects.requireNonNull( getClass().getResource( "Style.css" ) ).toExternalForm() );
        stage.setScene(scene);
    }



}
