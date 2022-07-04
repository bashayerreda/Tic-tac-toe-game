package xogame.online;

import gamesplatform.XOMenuBase;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class XO_Online extends BorderPane {

    public final Pane pane;
    public final MediaView mediaView;
    public final ImageView imageView;
    public final ImageView imageView0;
    public final Button button;
    public final Button button0;
    public final Line line;
    public final ImageView imageView1;
    public final ImageView imageView2;
    public final ImageView imageView3;
    public final Button button1;
    public final Button button2;
    public final ProgressBar progressBar;
    public final ProgressBar progressBar0;
    public final Text text;
    public final Text text0;

    public XO_Online() {

        pane = new Pane();
        mediaView = new MediaView();
        imageView = new ImageView();
        imageView0 = new ImageView();
        imageView3 = new ImageView();
        button = new Button();
        button0 = new Button();
        line = new Line();
        imageView1 = new ImageView();
        imageView2 = new ImageView();
        button1 = new Button();
        button2 = new Button();
        progressBar = new ProgressBar();
        progressBar0 = new ProgressBar();
        text = new Text();
        text0 = new Text();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(850.0);

        BorderPane.setAlignment(pane, javafx.geometry.Pos.CENTER);
        pane.setPrefHeight(200.0);
        pane.setPrefWidth(200.0);
        pane.setStyle("-fx-background-color: #032749;");

        imageView.setFitHeight(61.0);
        imageView.setFitWidth(71.0);
//        imageView.setImage(new Image(getClass().getResource("../../../../../Downloads/icons8-back-arrow-64.png").toExternalForm()));
        try {
            imageView.setImage(new Image(new FileInputStream("arrowB.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XOMenuBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        imageView0.setFitHeight(70.0);
        imageView0.setFitWidth(85.0);
        imageView0.setLayoutX(765.0);

        try {
            imageView0.setImage(new Image(new FileInputStream("close.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XOMenuBase.class.getName()).log(Level.SEVERE, null, ex);
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

//        line.setEndY(600.0);
//        line.setLayoutX(425.0);
//        line.setOpacity(0.58);
//        line.setStroke(javafx.scene.paint.Color.valueOf("#eeff00"));
//        line.setStrokeWidth(10.0);
        imageView1.setFitHeight(290.0);
        imageView1.setFitWidth(337.0);
        imageView1.setLayoutX(35.0);
        imageView1.setLayoutY(217.0);
        imageView1.setEffect(new Reflection(10, 0.5, 0.6, 0));
        try {
            imageView1.setImage(new Image(new FileInputStream("vmmmmmmmmmm.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XOMenuBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        imageView2.setFitHeight(281.0);
        imageView2.setFitWidth(337.0);
        imageView2.setLayoutX(471.0);
        imageView2.setLayoutY(226.0);
        imageView2.setEffect(new Reflection(10, 0.5, 0.6, 0));
        try {
            imageView2.setImage(new Image(new FileInputStream("vmmmmmmmmmm.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XOMenuBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        button1.setLayoutX(1.0);
        button1.setLayoutY(70.0);
        button1.setMnemonicParsing(false);
        button1.setOpacity(0.0);
        button1.setPrefHeight(485.0);
        button1.setPrefWidth(419.0);
        button1.setText("Button");

        button2.setLayoutX(430.0);
        button2.setLayoutY(70.0);
        button2.setMnemonicParsing(false);
        button2.setOpacity(0.0);
        button2.setPrefHeight(485.0);
        button2.setPrefWidth(419.0);
        button2.setText("Button");

        progressBar.setLayoutX(22.0);
        progressBar.setLayoutY(537.0);
        progressBar.setPrefHeight(18.0);
        progressBar.setPrefWidth(384.0);
        progressBar.setProgress(0.0);

        progressBar0.setLayoutX(447.0);
        progressBar0.setLayoutY(537.0);
        progressBar0.setPrefHeight(18.0);
        progressBar0.setPrefWidth(384.0);
        progressBar0.setProgress(0.0);

        imageView3.setFitHeight(600);
        imageView3.setFitWidth(850);
        try {
            imageView3.setImage(new Image(new FileInputStream("newrmg.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XOMenuBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        text.setLayoutX(47.0);
        text.setLayoutY(162.0);
        text.setStroke(javafx.scene.paint.Color.valueOf("#09d4d6"));
//        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(5.0);
        text.setText("CREATE GAME");
        text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text.setWrappingWidth(312.53668212890625);
        text.setFont(new Font(41.0));
        text.setCursor(Cursor.CROSSHAIR);

        text0.setLayoutX(483.0);
        text0.setLayoutY(143.0);
        text0.setStroke(javafx.scene.paint.Color.valueOf("#09d4d6"));
//        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(5.0);
        text0.setText("CONNECT TO A GAME");
        text0.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text0.setWrappingWidth(312.53668212890625);
        text0.setFont(new Font(41.0));
        text0.setCursor(Cursor.CROSSHAIR);
        setCenter(pane);
        setCursor(Cursor.CROSSHAIR);

        pane.getChildren().add(imageView3);
        pane.getChildren().add(imageView);
        pane.getChildren().add(imageView0);
        pane.getChildren().add(button);
        pane.getChildren().add(button0);
        pane.getChildren().add(line);
        pane.getChildren().add(imageView1);
        pane.getChildren().add(imageView2);
        pane.getChildren().add(button1);
        pane.getChildren().add(button2);
//        pane.getChildren().add(progressBar);
//        pane.getChildren().add(progressBar0);
        pane.getChildren().add(text);
        pane.getChildren().add(text0);

    }
}
