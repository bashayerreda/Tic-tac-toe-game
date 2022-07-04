package gamesplatform;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class HomePage extends BorderPane {

    protected final Pane pane;
    protected final ImageView imageView;
    protected final MediaView mediaView;
    protected final MediaView mediaView0;
    protected final MediaView mediaView1;
    protected final Text text;
    protected final ImageView imageView1;
    protected final ImageView imageView2;
    protected final ImageView imageView3;
    protected final Button button1;
    public HomePage() {

        pane = new Pane();
        imageView = new ImageView();
        mediaView = new MediaView();
        mediaView0 = new MediaView();
        mediaView1 = new MediaView();
        text = new Text();
        imageView1 = new ImageView();
        imageView2 = new ImageView();
        imageView3 = new ImageView();
        button1 = new Button();
        setId("MainView");
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setOpacity(0.86);
        setPrefHeight(600.0);
        setPrefWidth(850.0);
        setStyle("-fx-background-color: #191970;");
        setCursor(Cursor.CROSSHAIR);

        BorderPane.setAlignment(pane, javafx.geometry.Pos.CENTER);
        pane.setOpacity(0.7);
        pane.setPrefHeight(200.0);
        pane.setPrefWidth(200.0);

        imageView.setFitHeight(600.0);
        imageView.setFitWidth(850.0);
        try {
            FileInputStream f = new FileInputStream(new File("back.jpg"));
            imageView.setImage(new Image(f));
            f.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        button1.setPrefWidth(85);
        button1.setPrefHeight(70);
        button1.setLayoutX(765);
        button1.setLayoutY(0);
        button1.setOpacity(0);
        
        mediaView.setFitHeight(300);
        mediaView.setFitWidth(300);
        mediaView.setId("xoVideo");
        mediaView.setLayoutX(50);
        mediaView.setLayoutY(100);
        mediaView.setEffect(new DropShadow(50.0, Color.GOLDENROD));
        
            MediaPlayer mp = new MediaPlayer(new Media(new File("cc.mp4").toURI().toString()));
            mp.setAutoPlay(true);
            mp.setCycleCount(1000);
            mediaView.setMediaPlayer(mp);
            
            
            
//        mediaView0.setStyle("-fx-border-width: 200px;");    
        mediaView0.setFitHeight(300);
        mediaView0.setFitWidth(300);
        mediaView0.setId("SnakeVideo");
        mediaView0.setLayoutX(500);
        mediaView0.setLayoutY(100);
        mediaView0.setEffect(new DropShadow(50.0, Color.GOLDENROD));
        
        MediaPlayer mp1;
            File file = new File("rr.mp4");
            mp1 = new MediaPlayer(new Media(file.toURI().toString()));
            mp1.setAutoPlay(true);
            mp1.setCycleCount(1000);
            mediaView0.setMediaPlayer(mp1);
            
         
        mediaView1.setFitHeight(300);
        mediaView1.setFitWidth(300);
        mediaView1.setId("SnakeVideo");
        mediaView1.setLayoutX(275);
        mediaView1.setLayoutY(275);
        mediaView1.setEffect(new DropShadow(50.0, Color.GOLDENROD));
        
            MediaPlayer mp2;
            mp2 = new MediaPlayer(new Media(new File("ss.mp4").toURI().toString()));
            mp2.setAutoPlay(true);
            mp2.setCycleCount(1000);
            mediaView1.setMediaPlayer(mp2);
            
            
        imageView2.setFitHeight(70.0);
        imageView2.setFitWidth(85.0);
        imageView2.setLayoutX(765.0);
        
        try {
            FileInputStream f =  new FileInputStream(new File("close.png"));
            imageView2.setImage(new Image(f));
            f.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XOMenuBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        imageView3.setLayoutX(345.0);
        imageView3.setLayoutY(20);
        try {
            imageView3.setImage(new Image(new FileInputStream("re.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
        imageView3.setEffect(new DropShadow());
        
        
        setCenter(pane);

        pane.getChildren().add(imageView);
        pane.getChildren().add(mediaView);
        pane.getChildren().add(mediaView0);
        pane.getChildren().add(mediaView1);
        pane.getChildren().add(imageView2);
        pane.getChildren().add(text);
        pane.getChildren().add(imageView3);
        pane.getChildren().add(imageView1);
        pane.getChildren().add(button1);
    }
}
