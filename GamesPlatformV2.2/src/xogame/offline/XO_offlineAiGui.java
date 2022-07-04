package xogame.offline;

import xogame.database.model.GameArchive;
import xogame.database.model.GameMove;
import xogame.database.model.GameRecord;
import xogame.database.GameMoveDao;
import xogame.database.GameArchiveDao;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class XO_offlineAiGui extends BorderPane {

    protected final Pane pane;
    protected final MediaView mediaView;
    protected final ImageView imageView;
    protected final ImageView imageView0;
    public final Button button;
    public final Button button0;
    protected final BorderPane borderPane;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final Button button11;
    public final Button[] buttons = new Button[9];
    protected final Pane pane0;
    protected final TextField textField;
    protected final Button button10;
    private Ai comp;
    private char [][] board;
    boolean flag = true;
    public String recievedString = "X";
    public String player = "X";
    public String winner = null;
    public int steps;
    public int counter;
    GameArchiveDao gameArchiveDao = new GameArchiveDao();
    GameMoveDao gameMoveDao = new GameMoveDao();
    ArrayList<GameArchive> gameArchives;
    GameRecord gameRecord;

    public XO_offlineAiGui() {
        board = new char[3][3];
        initBoard();
        counter = 0;
        comp = new Ai();
        gameRecord = new GameRecord();
        pane = new Pane();
        mediaView = new MediaView();
        imageView = new ImageView();
        imageView0 = new ImageView();
        button = new Button();
        button0 = new Button();
        borderPane = new BorderPane();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();

        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button();
        }
        for (int i = 0; i < 9; i++) {
            buttons[i].getStyleClass().add("defClass");
        }
        pane0 = new Pane();
        textField = new TextField();
        button10 = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(850.0);
        button11 = new Button();

        BorderPane.setAlignment(pane, javafx.geometry.Pos.CENTER);
        pane.setPrefHeight(200.0);
        pane.setPrefWidth(200.0);
        pane.setStyle("-fx-background-color: #5a6794;");

        mediaView.setFitHeight(250.0);
        mediaView.setFitWidth(250.0);
        mediaView.setLayoutX(25.0);
        mediaView.setLayoutY(100.0);
        MediaPlayer mp2;
        mp2 = new MediaPlayer(new Media(new File("clap.mp4").toURI().toString()));
        mp2.setAutoPlay(true);
        mp2.setCycleCount(10000);
        mediaView.setMediaPlayer(mp2);
        mediaView.setVisible(false);// <--------------------------------------------------------------------------
        // Set it false then True after Player Win <-------------------------------------------------------------

        imageView.setFitHeight(61.0);
        imageView.setFitWidth(71.0);
        try {
            imageView.setImage(new Image(new FileInputStream("arrowB.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XO_offlineAiGui.class.getName()).log(Level.SEVERE, null, ex);
        }

        imageView0.setFitHeight(70.0);
        imageView0.setFitWidth(85.0);
        imageView0.setLayoutX(765.0);
        try {
            imageView0.setImage(new Image(new FileInputStream("close.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XO_offlineAiGui.class.getName()).log(Level.SEVERE, null, ex);
        }

        button.setMnemonicParsing(false);
        button.setOpacity(0.0);
        button.setPrefHeight(61.0);
        button.setPrefWidth(84.0);
        button.setText("Button");

        button0.setLayoutX(770.0);
        button0.setMnemonicParsing(false);
        button0.setOpacity(0.0);
        button0.setPrefHeight(66.0);
        button0.setPrefWidth(79.0);
        button0.setText("Button");

        borderPane.setLayoutX(2.0);
        borderPane.setLayoutY(65.0);
        borderPane.setPrefHeight(535.0);
        borderPane.setPrefWidth(850.0);

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        gridPane.setStyle("-fx-padding: 10;"
                + "-fx-border-width: 4;"
                + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: cyan;");
        gridPane.setGridLinesVisible(true);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(100.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(100.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(30.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(30.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        for (int i = 0; i < 9; i++) {
            buttons[i].setMnemonicParsing(false);
            buttons[i].setPrefHeight(207.0);
            buttons[i].setPrefWidth(284.0);

        }
        button10.setOnAction((e) -> {
            recievedString = "Reset";
            applyChanges();
        });

        GridPane.setColumnIndex(buttons[1], 2);

        GridPane.setColumnIndex(buttons[2], 1);

        GridPane.setRowIndex(buttons[3], 1);

        GridPane.setColumnIndex(buttons[4], 1);

        GridPane.setRowIndex(buttons[4], 1);

        GridPane.setColumnIndex(buttons[5], 2);
        GridPane.setRowIndex(buttons[5], 1);

        GridPane.setRowIndex(buttons[6], 2);

        GridPane.setColumnIndex(buttons[7], 1);
        GridPane.setRowIndex(buttons[7], 2);

        GridPane.setColumnIndex(buttons[8], 2);
        GridPane.setRowIndex(buttons[8], 2);

        BorderPane.setMargin(gridPane, new Insets(10.0));
        borderPane.setCenter(gridPane);

        BorderPane.setAlignment(pane0, javafx.geometry.Pos.CENTER);
        pane0.setPrefHeight(492.0);
        pane0.setPrefWidth(250.0);

        textField.setLayoutY(15.0);
        textField.setPrefHeight(136.0);
        textField.setPrefWidth(250.0);
        textField.setStyle("-fx-font-family:'jokerman'; -fx-font-size:36px;  -fx-text-fill:cyan; -fx-background-color: #5a6794; ");

        button11.setLayoutY(320.0);
        button11.setMnemonicParsing(false);
        button11.setPrefHeight(63.0);
        button11.setPrefWidth(251.0);
        button11.setStyle("-fx-font-family:'jokerman'; -fx-font-size:18px;  -fx-text-fill:cyan; -fx-background-color: #5a6794; -fx-border-color: cyan;");

        button10.setLayoutY(398.0);
        button10.setMnemonicParsing(false);
        button10.setPrefHeight(63.0);
        button10.setPrefWidth(251.0);
        button10.setText("Restart");
        button10.setStyle("-fx-font-family:'jokerman'; -fx-font-size:18px;  -fx-text-fill:cyan; -fx-background-color:#5a6794; -fx-border-color: cyan;");
        BorderPane.setMargin(pane0, new Insets(10.0, 10.0, 10.0, 0.0));
        borderPane.setRight(pane0);

//        gridPane.setBorder(new Border(new BorderStroke(Color.AQUAMARINE , BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.FULL)));
        setCenter(pane);
        setCursor(Cursor.CROSSHAIR);

        pane.getChildren().add(imageView);
        pane.getChildren().add(imageView0);
        pane.getChildren().add(button);
        pane.getChildren().add(button0);
        pane0.getChildren().add(button11);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);

        for (int i = 0; i < 9; i++) {
            gridPane.getChildren().add(buttons[i]);
        }

        pane0.getChildren().add(textField);
        pane0.getChildren().add(button10);
        pane.getChildren().add(borderPane);
        pane.getChildren().add(mediaView);
        button11.setText("Save");
        button11.setOnAction(e -> {
            try {
                int idGame = gameArchiveDao.insertGameArchive();
                gameRecord.setId(idGame);
                gameMoveDao.insertGameMove(gameRecord);
            } catch (Exception ex) {
                Logger.getLogger(XO_offlineAiGui.class.getName()).log(Level.SEVERE, null, ex);
            }
            button11.setMouseTransparent(true);

        });

        textField.setEditable(false);
        applyChanges();
        buttons[0].setOnAction(e -> {
            if (player.equals("X")) {
                recievedString = "Xb";
                gameRecord.addGameMove(new GameMove(1, counter, 'X'));
                board[0][0] = 'x';
            } 
//            else {
//                recievedString = "Ob";
//                gameRecord.addGameMove(new GameMove(1, counter, 'O'));
//            }
            counter++;

            applyChanges();
            changePlayer();
            compMove();
            checkForWinner();
          // changePlayer();
        });
        buttons[1].setOnAction(e -> {
            if (player.equals("X")) {
                recievedString = "Xb0";
                gameRecord.addGameMove(new GameMove(2, counter, 'X'));
                board[0][2] = 'x';
            } 
//            else {
//                recievedString = "Ob0";
//                gameRecord.addGameMove(new GameMove(2, counter, 'O'));
//            }
            counter++;
            applyChanges();
            changePlayer();
            compMove();
            checkForWinner();


        });
        buttons[2].setOnAction(e -> {
            if (player.equals("X")) {
                recievedString = "Xb1";
                gameRecord.addGameMove(new GameMove(3, counter, 'X'));
                board[0][1] = 'x';
            } 
//            else {
//                recievedString = "Ob1";
//                gameRecord.addGameMove(new GameMove(3, counter, 'O'));
//            }
            counter++;
            applyChanges();
            changePlayer();
             compMove();
            checkForWinner();
        });
        buttons[3].setOnAction(e -> {
            if (player.equals("X")) {
                gameRecord.addGameMove(new GameMove(4, counter, 'X'));
                recievedString = "Xb2";
                board[1][0] = 'x';
            } 
//            else {
//                gameRecord.addGameMove(new GameMove(4, counter, 'O'));
//                recievedString = "Ob2";
//            }
            counter++;
            applyChanges();
            changePlayer();
             compMove();
            checkForWinner();
  

        });
        buttons[4].setOnAction(e -> {
            if (player.equals("X")) {
                gameRecord.addGameMove(new GameMove(5, counter, 'X'));
                recievedString = "Xb3";
                board[1][1] = 'x';
            } 
//            else {
//                gameRecord.addGameMove(new GameMove(5, counter, 'O'));
//                recievedString = "Ob3";
//            }
            counter++;
            applyChanges();
            changePlayer();
            compMove();
            checkForWinner();
            

        });
        buttons[5].setOnAction(e -> {

            if (player.equals("X")) {
                gameRecord.addGameMove(new GameMove(6, counter, 'X'));
                recievedString = "Xb4";
                board[1][2] = 'x';
            } 
//            else {
//                gameRecord.addGameMove(new GameMove(6, counter, 'O'));
//                recievedString = "Ob4";
//            }
            counter++;
            applyChanges();
            changePlayer();
            compMove();
            checkForWinner();
            

        });
        buttons[6].setOnAction(e -> {
            if (player.equals("X")) {
                gameRecord.addGameMove(new GameMove(7, counter, 'X'));
                recievedString = "Xb5";
                board[2][0] = 'x';
            } 
//            else {
//                gameRecord.addGameMove(new GameMove(7, counter, 'O'));
//                recievedString = "Ob5";
//            }
            counter++;
            applyChanges();
            changePlayer();
            compMove();
            checkForWinner();
    

        });
        buttons[7].setOnAction(e -> {
            if (player.equals("X")) {
                recievedString = "Xb6";
                gameRecord.addGameMove(new GameMove(8, counter, 'X'));
                board[2][1] = 'x';
            } 
//            else {
//                recievedString = "Ob6";
//                gameRecord.addGameMove(new GameMove(8, counter, 'O'));
//            }
            counter++;
            applyChanges();
            changePlayer();
            compMove();
            checkForWinner();

        });
        buttons[8].setOnAction(e -> {
            if (player.equals("X")) {
                gameRecord.addGameMove(new GameMove(9, counter, 'X'));
                recievedString = "Xb7";
                board[2][2] = 'x';
            } 
//            else {
//                gameRecord.addGameMove(new GameMove(9, counter, 'O'));
//                recievedString = "Ob7";
//
//            }
            counter++;
            applyChanges();
            changePlayer();
            compMove();
            checkForWinner();
             
            

        });

    }

    public void checkForWinner() {
        if ((buttons[0].getText().equals(buttons[2].getText())) && (buttons[0].getText().equals(buttons[1].getText())) && (buttons[0].getText().equals(player))) {
            winner = player;
        }
        if ((buttons[3].getText().equals(buttons[4].getText())) && (buttons[3].getText().equals(buttons[5].getText())) && (buttons[3].getText().equals(player))) {
            winner = player;
        }
        if ((buttons[6].getText().equals(buttons[7].getText())) && (buttons[6].getText().equals(buttons[8].getText())) && (buttons[6].getText().equals(player))) {
            winner = player;
        }
        if ((buttons[0].getText().equals(buttons[3].getText())) && (buttons[0].getText().equals(buttons[6].getText())) && (buttons[0].getText().equals(player))) {
            winner = player;
        }
        if ((buttons[2].getText().equals(buttons[4].getText())) && (buttons[2].getText().equals(buttons[7].getText())) && (buttons[2].getText().equals(player))) {
            winner = player;
        }
        if ((buttons[1].getText().equals(buttons[5].getText())) && (buttons[1].getText().equals(buttons[8].getText())) && (buttons[1].getText().equals(player))) {
            winner = player;
        }
        if ((buttons[0].getText().equals(buttons[4].getText())) && (buttons[0].getText().equals(buttons[8].getText())) && (buttons[0].getText().equals(player))) {
            winner = player;
        }
        if ((buttons[1].getText().equals(buttons[4].getText())) && (buttons[1].getText().equals(buttons[6].getText())) && (buttons[1].getText().equals(player))) {
            winner = player;
        }
        if (winner != null) {
            if (winner.equals("X")) {
                mediaView.setVisible(true);
                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(mediaView.translateXProperty(), 250, Interpolator.EASE_IN);
                KeyFrame kf = new KeyFrame(Duration.millis(2500), kv);
                timeline.getKeyFrames().add(kf);

                Timeline timeline1 = new Timeline();
                KeyValue kv1 = new KeyValue(mediaView.translateYProperty(), 230, Interpolator.EASE_IN);
                KeyFrame kf1 = new KeyFrame(Duration.millis(2500), kv1);
                timeline1.getKeyFrames().add(kf1);

                SequentialTransition s = new SequentialTransition();
                s.getChildren().addAll(timeline, timeline1);
                s.setAutoReverse(true);
                s.setCycleCount(20);
                s.play();
                textField.setText("Winner is X");
            } else {
//                mediaView.setVisible(true);
//                Timeline timeline = new Timeline();
//                KeyValue kv = new KeyValue(mediaView.translateXProperty(), 250, Interpolator.EASE_IN);
//                KeyFrame kf = new KeyFrame(Duration.millis(2500), kv);
//                timeline.getKeyFrames().add(kf);
//
//                Timeline timeline1 = new Timeline();
//                KeyValue kv1 = new KeyValue(mediaView.translateYProperty(), 230, Interpolator.EASE_IN);
//                KeyFrame kf1 = new KeyFrame(Duration.millis(2500), kv1);
//                timeline1.getKeyFrames().add(kf1);
//
//                SequentialTransition s = new SequentialTransition();
//                s.getChildren().addAll(timeline, timeline1);
//                s.setAutoReverse(true);
//                s.setCycleCount(20);
//                s.play();
                textField.setText("Winner is O");
            }
            for (int i = 0; i < 9; i++) {
                buttons[i].setMouseTransparent(true);
            }

        }
    }

    public void applyChanges() {
        printBoard();
        if (recievedString != null) {
            switch (recievedString) {
                case "Xb":
                    buttons[0].getStyleClass().removeAll("defClass", "Oclass", "Xclass");
                    buttons[0].setText("X");
                    buttons[0].getStyleClass().add("Xclass");
                    buttons[0].setMouseTransparent(true);
                    break;
                case "Ob":
                    buttons[0].getStyleClass().removeAll("defClass", "Oclass", "Xclass");
                    buttons[0].setText("O");
                    buttons[0].getStyleClass().add("Oclass");
                    buttons[0].setMouseTransparent(true);
                    break;
                case "Xb0":
                    buttons[1].getStyleClass().removeAll("defClass", "Oclass", "Xclass");
                    buttons[1].setText("X");
                    buttons[1].getStyleClass().add("Xclass");
                    buttons[1].setMouseTransparent(true);
                    break;
                case "Ob0":
                    buttons[1].getStyleClass().removeAll("defClass", "Oclass", "Xclass");
                    buttons[1].setText("O");
                    buttons[1].getStyleClass().add("Oclass");
                    buttons[1].setMouseTransparent(true);
                    break;
                case "Xb1":
                    buttons[2].getStyleClass().removeAll("defClass", "Oclass", "Xclass");
                    buttons[2].setText("X");
                    buttons[2].getStyleClass().add("Xclass");
                    buttons[2].setMouseTransparent(true);
                    break;
                case "Ob1":
                    buttons[2].getStyleClass().removeAll("defClass", "Oclass", "Xclass");
                    buttons[2].setText("O");
                    buttons[2].getStyleClass().add("Oclass");
                    buttons[2].setMouseTransparent(true);
                    break;
                case "Xb2":
                    buttons[3].getStyleClass().removeAll("defClass", "Oclass", "Xclass");
                    buttons[3].setText("X");
                    buttons[3].getStyleClass().add("Xclass");
                    buttons[3].setMouseTransparent(true);
                    break;
                case "Ob2":
                    buttons[3].getStyleClass().removeAll("defClass", "Oclass", "Xclass");
                    buttons[3].setText("O");
                    buttons[3].getStyleClass().add("Oclass");
                    buttons[3].setMouseTransparent(true);
                    break;
                case "Xb3":
                    buttons[4].getStyleClass().removeAll("defClass", "Oclass", "Xclass");
                    buttons[4].setText("X");
                    buttons[4].getStyleClass().add("Xclass");
                    buttons[4].setMouseTransparent(true);
                    break;
                case "Ob3":
                    buttons[4].getStyleClass().removeAll("defClass", "Oclass", "Xclass");
                    buttons[4].setText("O");
                    buttons[4].getStyleClass().add("Oclass");
                    buttons[4].setMouseTransparent(true);
                    break;
                case "Xb4":
                    buttons[5].getStyleClass().removeAll("defClass", "Oclass", "Xclass");
                    buttons[5].setText("X");
                    buttons[5].getStyleClass().add("Xclass");
                    buttons[5].setMouseTransparent(true);
                    break;
                case "Ob4":
                    buttons[5].getStyleClass().removeAll("defClass", "Oclass", "Xclass");
                    buttons[5].setText("O");
                    buttons[5].getStyleClass().add("Oclass");
                    buttons[5].setMouseTransparent(true);
                    break;
                case "Xb5":
                    buttons[6].getStyleClass().removeAll("defClass", "Oclass", "Xclass");
                    buttons[6].setText("X");
                    buttons[6].getStyleClass().add("Xclass");
                    buttons[6].setMouseTransparent(true);
                    break;
                case "Ob5":
                    buttons[6].getStyleClass().removeAll("defClass", "Oclass", "Xclass");
                    buttons[6].setText("O");
                    buttons[6].getStyleClass().add("Oclass");
                    buttons[6].setMouseTransparent(true);
                    break;
                case "Xb6":
                    buttons[7].getStyleClass().removeAll("defClass", "Oclass", "Xclass");
                    buttons[7].setText("X");
                    buttons[7].getStyleClass().add("Xclass");
                    buttons[7].setMouseTransparent(true);
                    break;
                case "Ob6":
                    buttons[7].getStyleClass().removeAll("defClass", "Oclass", "Xclass");
                    buttons[7].setText("O");
                    buttons[7].getStyleClass().add("Oclass");
                    buttons[7].setMouseTransparent(true);
                    break;
                case "Xb7":
                    buttons[8].getStyleClass().removeAll("defClass", "Oclass", "Xclass");
                    buttons[8].setText("X");
                    buttons[8].getStyleClass().add("Xclass");
                    buttons[8].setMouseTransparent(true);
                    break;
                case "Ob7":
                    buttons[8].getStyleClass().removeAll("defClass", "Oclass", "Xclass");
                    buttons[8].setText("O");
                    buttons[8].getStyleClass().add("Oclass");
                    buttons[8].setMouseTransparent(true);
                    break;
                case "Reset":
                    reset();
                    recievedString = null;
                    applyChanges();
                    break;
            }
            if (winner == null) {
                textField.setText( "X  TURN");
            } else {
                textField.setText(player + " Won");
            }

        }
    }

    public void reset() {
        mediaView.setVisible(false);
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].getStyleClass().removeAll("defClass", "Oclass", "Xclass");
            buttons[i].getStyleClass().add("defClass");
            buttons[i].setMouseTransparent(false);
        }
        winner = null;
        initBoard();
        counter = 0;
        textField.setText("");
        recievedString = null;
        player = "X";
        applyChanges();
        button11.setMouseTransparent(false);
        gameRecord = new GameRecord();
    }

    public void initBoard()
    {
        for(int i = 0 ; i < 3;i++)
        {
                for(int j  = 0 ; j < 3 ;  j++)
                {
                    board[i][j] = '_';
                }
        }
     }
     public void printBoard()
    {
        for(int i = 0 ; i < 3;i++)
        {
                for(int j  = 0 ; j < 3 ;  j++)
                {
                    System.out.print("" +board[i][j]);
                }
                System.out.println("");
        }
     }
    

    public  void compMove()
    {
        int val = 0;
        Ai.Move bestMove=new Ai. Move() ;
        bestMove = comp.findBestMove(board);
        switch(bestMove.row)
        {
            case 0:
                switch(bestMove.col)
                {
                    case 0:
                        recievedString = "Ob";
                        val = 1;
                        break;
                     case 1:
                        recievedString = "Ob1";
                        val = 3;
                        break;
                      case 2:
                        recievedString = "Ob0";
                        val = 2;
                        break;
                      
                }
                break;
                 case 1:
                switch(bestMove.col)
                {
                    case 0:
                        recievedString = "Ob2";
                        val = 4;
                        break;
                     case 1:
                        recievedString = "Ob3";
                        val = 5;
                        break;
                      case 2:
                        recievedString = "Ob4";
                        val = 6;
                        break;
                      
                }
                break;
                  case 2:
                switch(bestMove.col)
                {
                    case 0:
                        recievedString = "Ob5";
                        val = 7;
                        break;
                     case 1:
                        recievedString = "Ob6";
                        val = 8;
                        break;
                      case 2:
                        recievedString = "Ob7";
                        val = 9;
                        break;
                      
                }
                break;
        }
       
        if(counter < 9)
        {
                gameRecord.addGameMove(new GameMove(val, counter, 'O'));   
                board[bestMove.row][bestMove.col] = 'o';
                applyChanges();
                checkForWinner();
                changePlayer();
        }
         counter++;
    }
      public void changePlayer() {
        if (player.equals("X")) {
            player = "O";
        } else {
            player = "X";
        }
      }

}
