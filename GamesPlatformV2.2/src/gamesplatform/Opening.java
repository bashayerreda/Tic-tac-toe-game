package gamesplatform;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Opening extends Pane {

    protected final ImageView imageView;
    protected final ImageView imageView0;
    protected final ImageView imageView1;

    public Opening() {

        imageView = new ImageView();
        imageView0 = new ImageView();
        imageView1 = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(850.0);

        imageView.setFitHeight(106.0);
        imageView.setFitWidth(106.0);
        imageView.setLayoutX(316.0);
        imageView.setLayoutY(194.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        try {
            imageView.setImage(new Image(new FileInputStream(new File("upright.png"))));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Opening.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        imageView0.setFitHeight(106.0);
        imageView0.setFitWidth(106.0);
        imageView0.setLayoutX(429.0);
        imageView0.setLayoutY(194.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        try {
            imageView0.setImage(new Image(new FileInputStream(new File("upleft.png"))));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Opening.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        imageView1.setFitHeight(106.0);
        imageView1.setFitWidth(300.0);
        imageView1.setLayoutX(255.0);
        imageView1.setLayoutY(310.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        try {
            imageView1.setImage(new Image(new FileInputStream(new File("under.png"))));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Opening.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getChildren().add(imageView);
        getChildren().add(imageView0);
        getChildren().add(imageView1);

    }
}
