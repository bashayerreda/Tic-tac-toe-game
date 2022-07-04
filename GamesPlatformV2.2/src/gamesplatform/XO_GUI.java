package gamesplatform;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.Socket;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class XO_GUI extends BorderPane {

    protected final Pane pane;
    protected final MediaView mediaView;
    protected final ImageView imageView;
    protected final ImageView imageView0;
    protected final ImageView imageView1;
    protected final TextField keyLabel; 
    protected final Button button;
    protected final Button button0;
    protected final BorderPane borderPane;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final Button[] buttons = new Button[9];
    protected final Pane pane0;
    public final TextField textField;
    protected final Button button10;
    public final TextField textField0;
    public final Button button11;
    public final Circle circle;
    public boolean flag = true;
    public Socket s;
    public DataInputStream dis;
    public PrintStream ps;
    public String recievedString = "X";
    public String player = "X";
    public String winner = null;
    public int steps;

    public XO_GUI() {

        pane = new Pane();
        mediaView = new MediaView();
        imageView = new ImageView();
        imageView0 = new ImageView();
        imageView1 = new ImageView();
        keyLabel = new TextField();
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
        textField0 = new TextField();
        button11 = new Button();
        circle = new Circle();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(850.0);

        BorderPane.setAlignment(pane, javafx.geometry.Pos.CENTER);
        pane.setPrefHeight(200.0);
        pane.setPrefWidth(200.0);
        pane.setStyle("-fx-background-color: #5a6794;");

        mediaView.setFitHeight(250.0);
        mediaView.setFitWidth(250.0);
        mediaView.setLayoutX(100.0);
        mediaView.setLayoutY(150.0);
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
            Logger.getLogger(XO_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        imageView0.setFitHeight(70.0);
        imageView0.setFitWidth(85.0);
        imageView0.setLayoutX(765.0);
        try {
            imageView0.setImage(new Image(new FileInputStream("close.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XO_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        imageView1.setFitHeight(565.0);
        imageView1.setFitWidth(690.0);
        imageView1.setLayoutY(50);
        imageView1.setLayoutX(-50);

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
        button10.setOnAction((e) -> ps.println("Reset"));

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

        button10.setLayoutY(398.0);
        button10.setMnemonicParsing(false);
        button10.setPrefHeight(63.0);
        button10.setPrefWidth(251.0);
        button10.setText("Restart");
        button10.setStyle("-fx-font-family:'jokerman'; -fx-font-size:18px;  -fx-text-fill:cyan; -fx-background-color: #032749; -fx-border-color: cyan;");

        keyLabel.setLayoutY(160);
        keyLabel.setLayoutX(12.0);
        keyLabel.setPrefHeight(52.0);
        keyLabel.setPrefWidth(172.0);
        keyLabel.setStyle("-fx-font-family:'jokerman'; -fx-font-size:14px;  -fx-text-fill :cyan;-fx-background-color:#5a6794; ");
        keyLabel.setEditable(false);
        
        textField0.setLayoutX(12.0);
        textField0.setLayoutY(206.0);
        textField0.setPrefHeight(52.0);
        textField0.setPrefWidth(172.0);
        textField.setStyle("-fx-font-family:'jokerman'; -fx-font-size:16px;  -fx-text-fill :cyan;-fx-background-color:#5a6794; ");
        textField0.setStyle("-fx-font-family:'jokerman'; -fx-font-size:14px;  -fx-text-fill :cyan;-fx-background-color:#032749; ");
        
        button11.setLayoutY(267.0);
        button11.setPrefHeight(63.0);
        button11.setPrefWidth(251.0);
        button11.setMnemonicParsing(false);
        button11.setText("Enter");
        button11.setStyle("-fx-font-family:'jokerman'; -fx-font-size:18px;  -fx-text-fill:cyan; -fx-background-color: #5a6794; -fx-border-color: cyan;");

        circle.setFill(javafx.scene.paint.Color.valueOf("#ff1f1f"));
        circle.setLayoutX(215.0);
        circle.setLayoutY(232.0);
        circle.setRadius(21.0);
        circle.setStroke(javafx.scene.paint.Color.BLACK);
        circle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        BorderPane.setMargin(pane0, new Insets(10.0, 10.0, 10.0, 0.0));
        borderPane.setRight(pane0);
        setCenter(pane);
        setCursor(Cursor.CROSSHAIR);

        pane.getChildren().add(imageView1);
        pane.getChildren().add(imageView);
        pane.getChildren().add(imageView0);
        pane.getChildren().add(button);
        pane.getChildren().add(button0);
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
        pane0.getChildren().add(keyLabel);
        pane0.getChildren().add(textField0);
        pane0.getChildren().add(button11);
        pane0.getChildren().add(circle);
        pane.getChildren().add(borderPane);
        pane.getChildren().add(mediaView);
        textField.setEditable(false);

        buttons[0].setOnAction(e -> {
            if (flag == true) {
                if (player.equals("X")) {
                    ps.println("Xb");
                } else {
                    ps.println("Ob");
                }
            }

        });
        buttons[1].setOnAction(e -> {
            if (flag == true) {
                if (player.equals("X")) {
                    ps.println("Xb0");
                } else {
                    ps.println("Ob0");
                }
            }

        });
        buttons[2].setOnAction(e -> {
            if (flag == true) {
                if (player.equals("X")) {
                    ps.println("Xb1");
                } else {
                    ps.println("Ob1");
                }
            }

        });
        buttons[3].setOnAction(e -> {
            if (flag == true) {
                if (player.equals("X")) {
                    ps.println("Xb2");
                } else {
                    ps.println("Ob2");
                }
            }

        });
        buttons[4].setOnAction(e -> {
            if (flag == true) {
                if (player.equals("X")) {
                    ps.println("Xb3");
                } else {
                    ps.println("Ob3");
                }
            }

        });
        buttons[5].setOnAction(e -> {
            if (flag == true) {
                if (player.equals("X")) {
                    ps.println("Xb4");
                } else {
                    ps.println("Ob4");
                }
            }

        });
        buttons[6].setOnAction(e -> {
            if (flag == true) {
                if (player.equals("X")) {
                    ps.println("Xb5");
                } else {
                    ps.println("Ob5");
                }
            }

        });
        buttons[7].setOnAction(e -> {
            if (flag == true) {
                if (player.equals("X")) {
                    ps.println("Xb6");
                } else {
                    ps.println("Ob6");
                }
            }

        });
        buttons[8].setOnAction(e -> {
            if (flag == true) {
                if (player.equals("X")) {
                    ps.println("Xb7");
                } else {
                    ps.println("Ob7");
                }
            }

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
            ps.println("W" + winner);
        }
    }

    public void applyChanges() {
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
                case "WX":
                    mediaView.setVisible(true);
                    textField.setText("Winner is X");
                    for (int i = 0; i < 9; i++) {
                        buttons[i].setMouseTransparent(true);
                    }
                    winner = "X";
                    break;
                case "WO":
                    mediaView.setVisible(true);
                    textField.setText("Winner is O");

                    for (int i = 0; i < 9; i++) {
                        buttons[i].setMouseTransparent(true);
                    }
                    winner = "O";
                    break;
                case "Reset":
                    reset();
                    applyChanges();
                    break;
            }
            if (winner == null) {
                if (flag == true) {
                    textField.setText("You are " + player + " PLAY");
                } else {
                    if (player.equals("X")) {
                        textField.setText("O turn WAIT");
                    } else {
                        textField.setText("X turn WAIT");
                    }
                }
            } else {
                if (player.equals(winner)) {
                    textField.setText("You Won");
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
                    textField.setText("Winner is O");

                } else {
                    textField.setText("You Lose");
                    mediaView.setVisible(false);

                }
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
        textField.setText("");
        recievedString = ("");
        System.out.println(winner);
        System.out.println(player);

        if (player.equals("X")) {
            flag = true;
        } else {
            flag = false;
        }
        applyChanges();
    }
    public TextField getKeyLabel()
    {
        return keyLabel;
    }
}
