package snakegame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
public class SankeUI extends BorderPane {

    public Pane anchorPane;
    public  final FlowPane snake;
    public  ArrayList<Circle> snakeArr;
    public  final ImageView imageView;
    public  final ImageView imageView0;
    public  final ImageView imageView1;
    public  final ImageView imageView2;
    public final Button button;
    public final Button button0;
    public final Button button1;
    public final Button button2;
    public TextField score;
    public SankeUI() {

        anchorPane = new Pane();
        imageView = new ImageView();
        imageView0 = new ImageView();
        imageView1 = new ImageView();
        imageView2 = new ImageView();
        button = new Button();
        button0 = new Button();
        button1 = new Button();
        button2 = new Button();
        score = new TextField();
        
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600);
        setPrefWidth(850);
        anchorPane.setPrefWidth(500);
        anchorPane.setPrefHeight(600);
        
        try {
            Background bk;
            File f = new File("yy.png");
//            System.out.println(f.exists());
            bk = new Background(new BackgroundImage(new Image(new FileInputStream(f)), BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
            anchorPane.setBackground(bk);
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SankeUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        imageView.setFitWidth(610);
        imageView.setFitHeight(600);
        imageView.setLayoutX(0);
        imageView.setLayoutY(0);
        try {
            imageView.setImage(new Image(new FileInputStream("61Pp0bZYNDL._AC_.jpg")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SankeUI.class.getName()).log(Level.SEVERE, null, ex);
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
        
        button1.setLayoutX(625);
        button1.setLayoutY(375);
        button1.setMnemonicParsing(false);
        button1.setOpacity(100);
        button1.setPrefHeight(50);
        button1.setPrefWidth(200);
        button1.setText("PAUSE");
        
        button2.setLayoutX(625);
        button2.setLayoutY(450);
        button2.setMnemonicParsing(false);
        button2.setOpacity(100);
        button2.setPrefHeight(50);
        button2.setPrefWidth(200);
        button2.setText("RESUME");
        
        score.setLayoutX(625);
        score.setLayoutY(150);
        score.setOpacity(100);
        score.setPrefHeight(50);
        score.setPrefWidth(200);
        score.setText("0000");
        score.setFont(new Font(30));
        score.setAlignment(Pos.CENTER);
        score.setEditable(false);
        
        imageView1.setFitHeight(61.0);
        imageView1.setFitWidth(71.0);
        try {
            //        imageView.setImage(new Image(getClass().getResource("../../../../../Downloads/icons8-back-arrow-64.png").toExternalForm()));
            imageView1.setImage(new Image(new FileInputStream("arrowB.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SankeUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        imageView0.setFitHeight(70.0);
        imageView0.setFitWidth(85.0);
        imageView0.setLayoutX(765.0);
        try {
            //        imageView0.setImage(new Image(getClass().getResource("../../../../../Downloads/icons8-macos-close-96.png").toExternalForm()));
            imageView0.setImage(new Image(new FileInputStream("close.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SankeUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        setCenter(anchorPane);
        
        
        snake = new FlowPane();
        snakeArr = new ArrayList<>();
//        
            snakeArr.add(new Circle(15));
        for (int i = 1; i < 400; i++) {
            snakeArr.add(new Circle(10));
        }
        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(imageView0);
        anchorPane.getChildren().add(imageView1);
        anchorPane.getChildren().add(imageView2);
        anchorPane.getChildren().add(button);
        anchorPane.getChildren().add(button0);
        anchorPane.getChildren().add(button1);
        anchorPane.getChildren().add(button2);
        anchorPane.getChildren().add(score);
        
//        anchorPane.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGREY, CornerRadii.EMPTY, Insets.EMPTY)));
//        anchorPane.getChildren().add(snake);
        
        

    }
    public void paint(){
        snakeArr.get(0).setCenterX(200);
        snakeArr.get(0).setCenterY(200);
        Color c[] = {Color.DARKBLUE , Color.FLORALWHITE};
        try {
            Image img = new Image(new FileInputStream("kisspng-kill-the-snake-game-snake-snake-2-snake-wifi-snakehead-5b112ebe8d59c4.898325521527852734579.png"));
            snakeArr.get(0).setFill(new ImagePattern(img));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SankeUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        anchorPane.getChildren().add(snakeArr.get(0));
        for (int i = 1; i < 15; i++) {
            snakeArr.get(i).setCenterX(200 + (10*i));
            snakeArr.get(i).setCenterY(200);
            snakeArr.get(i).setFill(c[ i%2  ]);
            anchorPane.getChildren().add(snakeArr.get(i));
        }
        for (int i = 15; i < snakeArr.size(); i++) {
            snakeArr.get(i).setCenterX(200 + (10*i));
            snakeArr.get(i).setCenterY(200);
            snakeArr.get(i).setFill(c[ i%2 ]);
            anchorPane.getChildren().add(snakeArr.get(i));
            snakeArr.get(i).setVisible(false);
        }
    }
    
}
