package xogame.database;

import gamesplatform.XOMenuBase;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GamesList extends Pane {

    public final ListView listView;
    public final ImageView imageView;
    public final ImageView imageView0;
    public final Button button;
    public final Button button0;
    ObservableList<String> arr;

    public GamesList() {

        imageView = new ImageView();
        imageView0 = new ImageView();
        button = new Button();
        button0 = new Button();
        listView = new ListView();

        setStyle("-fx-background-color: #032749;");
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(850.0);

        imageView.setFitHeight(61.0);
        imageView.setFitWidth(71.0);
        try {
            imageView.setImage(new Image(new FileInputStream("arrowB.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XOMenuBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        imageView0.setFitHeight(70.0);
        imageView0.setFitWidth(85.0);
        imageView0.setLayoutX(765.0);
//        imageView0.setImage(new Image(getClass().getResource("../../../../../Downloads/icons8-macos-close-96.png").toExternalForm()));
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

        listView.setLayoutY(80.0);
        listView.setPrefHeight(520.0);
        listView.setPrefWidth(850.0);

        getChildren().add(listView);
        getChildren().add(imageView);
        getChildren().add(imageView0);
        getChildren().add(button);
        getChildren().add(button0);
    }
}
