package com.example.algorithimproject_1;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.ArrayList;
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
                if (number%2 != 0){
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setContentText("Number should be even.");
                    a.show();
                } else  {
                    playing(s,number);
                }

            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setContentText("This filed can only contain integers");
                a.show();
            }
        }



    }


    private void playing(Stage stage, int number){

        AnchorPane root = new AnchorPane();

        Label player1L = new Label("Player 1");
        player1L.setId("lables");
        player1L.setLayoutY(440);
        player1L.setLayoutX(1140);

        Button expandTable = new Button("Expand table");
        expandTable.setLayoutX(440);
        expandTable.setLayoutY(269);
        expandTable.setPrefSize(127,46);



        Label player2L = new Label("Player 2");
        player2L.setId("lables");
        player2L.setLayoutY(680);
        player2L.setLayoutX(1140);

        Button startB = new Button("Start");
        startB.setLayoutX(244);
        startB.setLayoutY(269);
        startB.setPrefSize(127,46);

        Button BackB = new Button("Restart");
        BackB.setLayoutX(456);
        BackB.setLayoutY(269);
        BackB.setPrefSize(127,46);

        Label EnterDataL = new Label("Enter Data manually");
        EnterDataL.setId("lables");
        EnterDataL.setLayoutY(128);
        EnterDataL.setLayoutX(14);
        EnterDataL.setPrefSize(217,30);

        TextField dataTF = new TextField();
        dataTF.setId("textFieldData");
        dataTF.setLayoutY(128);
        dataTF.setLayoutX(244);
        dataTF.setPrefSize(336,31);

        Label resultL = new Label("Result");
        resultL.setId("lables");
        resultL.setLayoutY(174);
        resultL.setLayoutX(14);
        resultL.setPrefSize(217,30);

        TextField resultTF = new TextField();
        resultTF.setId("textFieldData");
        resultTF.setLayoutY(173);
        resultTF.setLayoutX(244);
        resultTF.setPrefSize(169,31);
        resultTF.setEditable(false);

        Label coinsL = new Label("Coins to get the result");
        coinsL.setId("lables");
        coinsL.setLayoutY(221);
        coinsL.setLayoutX(14);
        coinsL.setPrefSize(217,30);

        TextField coinsTF = new TextField();
        coinsTF.setId("textFieldData");
        coinsTF.setLayoutY(220);
        coinsTF.setLayoutX(244);
        coinsTF.setPrefSize(169,31);
        coinsTF.setEditable(false);


        GridPane tableView = new GridPane();
        tableView.setGridLinesVisible(true);
        //tableView.setLayoutY(14);
        //tableView.setLayoutX(835);
        //tableView.setPrefSize(410,410);
        tableView.setId("grid");

        ScrollPane sp = new ScrollPane();
        sp.setLayoutY(14);
        sp.setLayoutX(835);
        sp.setPrefSize(410,410);

        sp.setContent(tableView);



        BackB.setOnAction(e -> {
            FXMLLoader gameScene = new FXMLLoader(HelloApplication.class.getResource("Game.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(gameScene.load(), 1280, 720);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Stage s = (Stage) BackB.getScene().getWindow();
            s.setScene(scene);
        });

        //System.out.println("Here");

        TextField[] textFields = new TextField[number];

        //System.out.println("here");
        int x = 1100, y = 560;

        root.getChildren().addAll(player1L,player2L, startB, BackB, EnterDataL, dataTF, resultL, resultTF, coinsL, coinsTF, sp);


        for (int i = 0; i < textFields.length; i++){

            textFields[i] = new TextField();
            textFields[i].setLayoutX(x);
            textFields[i].setLayoutY(y);
            //textFields[i].setEditable(false);
            textFields[i].setPrefSize(30,30);
            root.getChildren().add(textFields[i]);
            //System.out.println("here");
            x -=40;

        }


        startB.setOnAction(e -> {
            int[] arr = new int[textFields.length];
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
                       arr[i] = num;
                   } catch (Exception error) {
                       Alert a = new Alert(Alert.AlertType.ERROR);
                       a.setTitle("Error");
                       a.setContentText("all textFields should contain only integers to start, number " + (i+1) + " is has something else");
                       System.out.println(error.getMessage());
                       a.show();
                       hasFailed = true;
                       break;
                   }
               }
            }

            if (!hasFailed) {
                findOptimalSolution(arr, stage, tableView, resultTF, coinsTF);
            }

        });


        Scene scene = new Scene(root, 1280,720);
        scene.getStylesheets().add( Objects.requireNonNull( getClass().getResource( "Style.css" ) ).toExternalForm() );
        stage.setScene(scene);
    }

    private static void findOptimalSolution(int[] arr, Stage stage, GridPane ta, TextField result, TextField coins) {

        PlayerSpot[][] table = new PlayerSpot[arr.length][arr.length];

        int gap = 1;




        for(int i = 0, j = 0; (i <= table.length - 1 && j >= 0) ; ){ // 4, 15, 3, 7, 8, 9
            if ((i == 0 && j == 0) || (i == arr.length -1 && j == arr.length-1 )){ // this solves the edge cases where i has no previous or i has no bottom number

                table[i][j] = new PlayerSpot(arr[i],0);
                table[i][j].getFirst().addToRoot(arr[i]);
                table[i][j].getSecond().addToRoot(0);

            } else if (i-j < 0 || i == j){

                table[i][j] = new PlayerSpot(arr[i],0);
                table[i][j].getFirst().addToRoot(arr[i]);
                table[i][j].getSecond().addToRoot(0);

            } else {
                if (i-2 < 0 || (i-2) < j){
                    System.out.println("here " + i + " " + j);


                    table[i][j] = new PlayerSpot(Math.max(table[i-1][j].getFirst().getSum(), table[i][j+1].getFirst().getSum()),Math.min(table[i-1][j].getFirst().getSum(), table[i][j+1].getFirst().getSum()));
                    table[i][j].getFirst().addToRoot(Math.max(table[i-1][j].getFirst().getSum(), table[i][j+1].getFirst().getSum()));
                    table[i][j].getSecond().addToRoot(Math.min(table[i-1][j].getFirst().getSum(), table[i][j+1].getFirst().getSum()));
                } else {
                    int playerOne;
                    int playerTwo;
                    ArrayList<Integer> playerOneNumbers = new ArrayList<>();
                    ArrayList<Integer> playerTwoNumbers = new ArrayList<>();
                    if (arr[i] + table[i-1][j].getSecond().getSum() > arr[j] + table[i][j+1].getSecond().getSum()){
                        playerOne = arr[i] + table[i-1][j].getSecond().getSum();
                        playerTwo = table[i-1][j].getFirst().getSum();
                        playerOneNumbers.add(arr[i]);
                        for (int t = 0; t < table[i-1][j].getSecond().getRoot().size(); t++){
                            playerOneNumbers.add(table[i-1][j].getSecond().getRoot().get(t));
                        }
                        for (int t = 0; t < table[i-1][j].getFirst().getRoot().size(); t++){
                            playerTwoNumbers.add(table[i-1][j].getFirst().getRoot().get(t));
                        }

                    } else {
                        playerOne = arr[j] + table[i][j+1].getSecond().getSum();
                        playerTwo = table[i][j+1].getFirst().getSum();
                        playerOneNumbers.add(arr[j]);
                        for (int t = 0; t < table[i][j+1].getSecond().getRoot().size(); t++){
                            playerOneNumbers.add(table[i][j+1].getSecond().getRoot().get(t));
                        }
                        for (int t = 0; t < table[i][j+1].getFirst().getRoot().size(); t++){
                            playerTwoNumbers.add(table[i][j+1].getFirst().getRoot().get(t));
                        }
                    }
                    table[i][j] = new PlayerSpot(playerOne,playerTwo);
                    table[i][j].getFirst().setRoot(playerOneNumbers);
                    table[i][j].getSecond().setRoot(playerTwoNumbers);
                }
            }
            if (i == table.length - 1 ){
                i = gap;
                gap++;
                j = 0;
            } else {
                i++;
                j++;
            }

        }



        int units = 0;
        int number = table[arr.length - 1][0].getFirst().getSum();


        while (number > 1) {
            units++;
            number = number/10;
        }


        int rowCount = table.length;
        int columnCount = table.length;

        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(1000d / rowCount);
        rc.setPrefHeight(50);

        for (int i = 0; i < rowCount; i++) {
            ta.getRowConstraints().add(rc);
        }

        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(1000d / columnCount);
        cc.setPrefWidth(50);

        for (int i = 0; i < columnCount; i++) {
            ta.getColumnConstraints().add(cc);
        }





        for (int i = 0; i < table.length; i++){
            for (int j = 0; j < table[i].length; j++){
                if (table[i][j] == null) {
                    Label l1 = new Label("0");
                    l1.setId("lablesGrid");
                    l1.setMaxWidth(Double.MAX_VALUE);
                    l1.setAlignment(Pos.CENTER_RIGHT);
                    ta.add(l1, i, j);
                } else {
                    Label l1 = new Label(table[i][j].getFirst().getSum() + "");
                    l1.setId("lablesGrid");
                    l1.setMaxWidth(Double.MAX_VALUE);
                    l1.setAlignment(Pos.CENTER_RIGHT);
                    ta.add(l1, i, j);
                }
            }
        }

        //System.out.print("\n\n\n\n");
        //System.out.println(table[arr.length - 1][ 0 ].getFirst().getSum());
        //System.out.print("\n\n\n\n");

        String coinsY = "";


        //System.out.println(table[arr.length-1][0].getFirst().getRoot().size());

        for (int i = 0; i < table[arr.length-1][0].getFirst().getRoot().size(); i++){
            coinsY += table[arr.length-1][0].getFirst().getRoot().get(i) + " ";
            //System.out.println(table[arr.length-1][0].getFirst().getRoot().get(i) + " ");
        }

        //System.out.println(coinsY);
        coins.setText(coinsY);
        result.setText(table[arr.length-1][0].getFirst().getSum() + "");

    }



}
