package gamesplatform;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;

public class XOMenuBase extends BorderPane {

    protected final Pane pane;
    protected final MediaView mediaView;
    protected final Button button;
    protected final Button butto;
    protected final Button button1;
    protected final Button button2;
    protected final Button button3;
    protected final ImageView imageView;
    protected final ImageView imageView0;
    protected final ImageView imageView1;
    protected final ImageView imageView2;
    protected final ImageView imageView3;

    public XOMenuBase() {

        pane = new Pane();
        mediaView = new MediaView();
        button = new Button();
        button1 = new Button();
        button2 = new Button();
        button3 = new Button();
        imageView = new ImageView();
        imageView0 = new ImageView();
        imageView1 = new ImageView();
        imageView2 = new ImageView();
        imageView3 = new ImageView();
        butto = new Button();
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(850.0);
        setCursor(Cursor.CROSSHAIR);

        BorderPane.setAlignment(pane, javafx.geometry.Pos.CENTER);
        pane.setPrefHeight(200.0);
        pane.setPrefWidth(200.0);
        pane.setStyle("-fx-background-color: #032749;");

        mediaView.setFitHeight(600.0);
        mediaView.setFitWidth(850.0);
        // you will find the pic in the image folder bur it through exeption if its path is absolute
        File file = new File("xbb.mp4");
        MediaPlayer mp = new MediaPlayer(new Media(file.toURI().toString()));
        mp.setAutoPlay(true);
        mp.setCycleCount(10);
        mediaView.setMediaPlayer(mp);
        
        
        button.setLayoutX(275);
        button.setLayoutY(400.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(200);
        button.setPrefWidth(300);
        button.setStyle("-fx-background-color: #032749;");
        button.setText("HISTORY");
        button.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        button.setTextFill(javafx.scene.paint.Color.valueOf("#eeff00"));
        button.setFont(new Font("Bradley Hand ITC", 32.0));
        button.setOpacity(0);
        
        imageView.setFitHeight(61.0);
        imageView.setFitWidth(71.0);
        
        butto.setPrefWidth(71);
        butto.setPrefHeight(61);
        butto.setLayoutX(0);
        butto.setLayoutY(0);
        butto.setOpacity(0);
        
        // you will find the pic in the image folder bur it through exeption if its path is absolute
        try {
            
            imageView.setImage(new Image(new FileInputStream("arrowB.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XOMenuBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        imageView0.setFitHeight(70.0);
        imageView0.setFitWidth(85.0);
        imageView0.setLayoutX(765.0);
        
        button1.setPrefWidth(85);
        button1.setPrefHeight(70);
        button1.setLayoutX(765);
        button1.setLayoutY(0);
        button1.setOpacity(0);
        try {
            imageView0.setImage(new Image(new FileInputStream(new File("close.png"))));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XOMenuBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        imageView1.setLayoutX(360);
        imageView1.setLayoutY(450);
        imageView1.setFitWidth(120);
        imageView1.setFitHeight(120);
        
        try {
            imageView1.setImage(new Image(new FileInputStream("icons8-time-machine-64.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XOMenuBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        imageView2.setFitHeight(200.0);
        imageView2.setFitWidth(200.0);
        imageView2.setLayoutX(607.0);
        imageView2.setLayoutY(202.0);
//        imageView2.setImage(new Image(getClass().getResource("../../../../../Downloads/vsvsvsv.png").toExternalForm()));
        try {
            imageView2.setImage(new Image(new FileInputStream(new File("vsvsvsv.png"))));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XOMenuBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        button2.setPrefWidth(200);
        button2.setPrefHeight(200);
        button2.setLayoutX(607);
        button2.setLayoutY(202);
        button2.setOpacity(0);
        
        imageView3.setFitHeight(200.0);
        imageView3.setFitWidth(200.0);
        imageView3.setLayoutX(36.0);
        imageView3.setLayoutY(202.0);
//        imageView3.setImage(new Image(getClass().getResource("../../../../../Downloads/vsvs.png").toExternalForm()));
        try {
            imageView3.setImage(new Image(new FileInputStream(new File("vsvs.png"))));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XOMenuBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        button3.setPrefWidth(200);
        button3.setPrefHeight(200);
        button3.setLayoutX(36);
        button3.setLayoutY(202);
        button3.setOpacity(0);
        
        setCenter(pane);

        pane.getChildren().add(mediaView);
        pane.getChildren().add(button);
        pane.getChildren().add(butto);
        pane.getChildren().add(button1);
        pane.getChildren().add(button2);
        pane.getChildren().add(button3);
        pane.getChildren().add(imageView);
        pane.getChildren().add(imageView0);
        pane.getChildren().add(imageView1);
        pane.getChildren().add(imageView2);
        pane.getChildren().add(imageView3);

    }
}
