/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamesplatform;

import xogame.online.XO_Online;
import xogame.online.Host;
import xogame.online.Guest;
import xogame.database.GamesList;
import xogame.offline.XO_offlineGui;
import xogame.database.model.GameArchive;
import xogame.database.model.GameMove;
import xogame.database.model.GameRecord;
import xogame.database.GameMoveDao;
import xogame.database.GameArchiveDao;
import connect4game.Connect4App;
import snakegame.Snake;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import xogame.offline.XO_Offline;
import xogame.offline.XO_offlineAiGui;

/**
 *
 * @author yehia
 */
public class GamesPlatform extends Application {

    public Snake snakeGame;
    XO_offlineGui t;
    XO_offlineAiGui aiOffline;
    boolean openedbefor;
    GamesList rootList;
    Host s;
    Guest s1;
    GameArchiveDao gameArchiveDao = new GameArchiveDao();
    GameMoveDao gameMoveDao = new GameMoveDao();
    ArrayList<GameArchive> gameArchives;

    @Override
    public void start(Stage stage) throws Exception {
        HomePage root = new HomePage();
        XOMenuBase rootxo = new XOMenuBase();
        XO_Offline rootOff = new XO_Offline();
        XO_Online rootOn = new XO_Online();
        Connect4App rootConnect = new Connect4App();
        
        Opening rootOpening = new Opening();

        Scene sceneOpening = new Scene(rootOpening, 850, 600);
        Scene scene = new Scene(root, 850, 600);
        scene.setFill(Color.BLACK);
        Scene sceneXo = new Scene(rootxo, 850, 600);
        sceneXo.setFill(Color.BLACK);
        Scene sceneOff = new Scene(rootOff, 850, 600);
        sceneOff.setFill(Color.BLACK);
        Scene sceneOn = new Scene(rootOn, 850, 600);
        sceneOn.setFill(Color.BLACK);
        Scene sceneConnect = rootConnect.sceneConnect;
        sceneConnect.setFill(Color.BLACK);


        stage.setScene(sceneOpening);
        stage.initStyle(StageStyle.DECORATED);
        stage.show();

        FadeTransition ft10 = new FadeTransition(Duration.millis(5500), rootOpening);
        ft10.setFromValue(0.7);
        ft10.setToValue(1);
        ft10.setOnFinished(t -> {
            stage.setScene(scene);
        });

        ft10.play();

        Timeline timeline00 = new Timeline();
        KeyValue kv00 = new KeyValue(rootOpening.imageView.translateYProperty(), -20);
        KeyFrame kf00 = new KeyFrame(Duration.millis(350), kv00);
        timeline00.getKeyFrames().add(kf00);
        Timeline timeline01 = new Timeline();
        KeyValue kv01 = new KeyValue(rootOpening.imageView.translateYProperty(), 10);
        KeyFrame kf01 = new KeyFrame(Duration.millis(350), kv01);
        timeline01.getKeyFrames().add(kf01);

        Timeline timeline11 = new Timeline();
        KeyValue kv11 = new KeyValue(rootOpening.imageView0.translateYProperty(), -20);
        KeyFrame kf11 = new KeyFrame(Duration.millis(350), kv11);
        timeline11.getKeyFrames().add(kf11);
        Timeline timeline12 = new Timeline();
        KeyValue kv12 = new KeyValue(rootOpening.imageView0.translateYProperty(), 10);
        KeyFrame kf12 = new KeyFrame(Duration.millis(350), kv12);
        timeline12.getKeyFrames().add(kf12);

        rootOpening.imageView1.setTranslateX(800);
        Timeline timeline13 = new Timeline();
        KeyValue kv13 = new KeyValue(rootOpening.imageView1.translateXProperty(), -50);
        KeyFrame kf13 = new KeyFrame(Duration.millis(350), kv13);
        timeline13.getKeyFrames().add(kf13);

        SequentialTransition s11 = new SequentialTransition();
        s11.getChildren().addAll(timeline00, timeline01, timeline11, timeline12, timeline13);
        s11.play();

        // MIAN MENU  
        root.imageView2.setOnMouseClicked((MouseEvent event) -> {
            Platform.exit();
        });
        root.button1.setOnMouseClicked((MouseEvent event) -> {
            Platform.exit();
        });
        root.imageView2.setOnMouseEntered((MouseEvent event) -> {
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.imageView2.translateXProperty(), -10);
            KeyFrame kf = new KeyFrame(Duration.millis(50), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            KeyValue kv1 = new KeyValue(root.imageView2.translateXProperty(), 10);
            KeyFrame kf1 = new KeyFrame(Duration.millis(50), kv);
            timeline.getKeyFrames().add(kf1);
            timeline.play();

        });
        root.button1.setOnMouseEntered((MouseEvent event) -> {
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.imageView2.translateXProperty(), -5, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(50), kv);
            timeline.getKeyFrames().add(kf);

            Timeline timeline1 = new Timeline();
            KeyValue kv1 = new KeyValue(root.imageView2.translateXProperty(), 5);
            KeyFrame kf1 = new KeyFrame(Duration.millis(50), kv1);
            timeline1.getKeyFrames().add(kf1);

            SequentialTransition s = new SequentialTransition();
            s.getChildren().addAll(timeline, timeline1);
            s.setAutoReverse(true);
            s.setCycleCount(500);
            s.play();
            root.button1.setOnMouseExited((e) -> {
                s.stop();
            });

        });

        root.mediaView1.setOnMouseClicked((MouseEvent event) -> {
            rootxo.setTranslateX(-425);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootxo.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

            FadeTransition ft = new FadeTransition(Duration.millis(2000), rootxo);
            ft.setFromValue(0.5);
            ft.setToValue(1);
            ft.play();

            stage.setScene(sceneXo);
        });
        root.mediaView1.setOnMouseEntered((MouseEvent event) -> {
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.mediaView1.translateXProperty(), -5, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(50), kv);
            timeline.getKeyFrames().add(kf);

            Timeline timeline1 = new Timeline();
            KeyValue kv1 = new KeyValue(root.mediaView1.translateXProperty(), 5);
            KeyFrame kf1 = new KeyFrame(Duration.millis(50), kv1);
            timeline1.getKeyFrames().add(kf1);

            SequentialTransition s = new SequentialTransition();
            s.getChildren().addAll(timeline, timeline1);
            s.setAutoReverse(true);
            s.setCycleCount(500);
            s.play();
            root.mediaView1.setOnMouseExited((e) -> {
                s.stop();
            });
        });

        root.mediaView.setOnMouseClicked((MouseEvent event) -> {

            FadeTransition ft = new FadeTransition(Duration.millis(1500), rootConnect.root);
            ft.setFromValue(0.5);
            ft.setToValue(1);
            ft.play();

            stage.setScene(sceneConnect);
        });
        root.mediaView.setOnMouseEntered((MouseEvent event) -> {
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.mediaView.translateYProperty(), -5, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(50), kv);
            timeline.getKeyFrames().add(kf);

            Timeline timeline1 = new Timeline();
            KeyValue kv1 = new KeyValue(root.mediaView.translateYProperty(), 5);
            KeyFrame kf1 = new KeyFrame(Duration.millis(50), kv1);
            timeline1.getKeyFrames().add(kf1);

            SequentialTransition s = new SequentialTransition();
            s.getChildren().addAll(timeline, timeline1);
            s.setAutoReverse(true);
            s.setCycleCount(500);
            s.play();
            root.mediaView.setOnMouseExited((e) -> {
                s.stop();
            });
        });

        root.mediaView0.setOnMouseEntered((MouseEvent event) -> {
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.mediaView0.translateYProperty(), -5, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(50), kv);
            timeline.getKeyFrames().add(kf);

            Timeline timeline1 = new Timeline();
            KeyValue kv1 = new KeyValue(root.mediaView0.translateYProperty(), 5);
            KeyFrame kf1 = new KeyFrame(Duration.millis(50), kv1);
            timeline1.getKeyFrames().add(kf1);

            SequentialTransition s = new SequentialTransition();
            s.getChildren().addAll(timeline, timeline1);
            s.setAutoReverse(true);
            s.setCycleCount(500);
            s.play();
            root.mediaView0.setOnMouseExited((e) -> {
                s.stop();
            });
        });
        root.mediaView0.setOnMouseClicked((MouseEvent event) -> {
            try {
                snakeGame = new Snake();
                snakeGame.init();
                snakeGame.start(stage);

            } catch (Exception ex) {
                Logger.getLogger(GamesPlatform.class.getName()).log(Level.SEVERE, null, ex);
            }
            snakeGame.ui.anchorPane.setTranslateX(-425);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(snakeGame.ui.anchorPane.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

            FadeTransition ft = new FadeTransition(Duration.millis(1500), snakeGame.ui.anchorPane);
            ft.setFromValue(0.5);
            ft.setToValue(1);
            ft.play();

//         Snake Game
            snakeGame.ui.button0.setOnAction((e) -> {
                Platform.exit();
            });

            snakeGame.ui.button0.setOnMouseEntered((e) -> {
                Timeline timeline2 = new Timeline();
                KeyValue kv2 = new KeyValue(snakeGame.ui.button0.translateXProperty(), -5, Interpolator.EASE_IN);
                KeyFrame kf2 = new KeyFrame(Duration.millis(50), kv2);
                timeline.getKeyFrames().add(kf);

                Timeline timeline1 = new Timeline();
                KeyValue kv1 = new KeyValue(snakeGame.ui.button0.translateXProperty(), 5);
                KeyFrame kf1 = new KeyFrame(Duration.millis(50), kv1);
                timeline1.getKeyFrames().add(kf1);

                SequentialTransition s = new SequentialTransition();
                s.getChildren().addAll(timeline, timeline1);
                s.setAutoReverse(true);
                s.setCycleCount(500);
                s.play();
                snakeGame.ui.button0.setOnMouseExited((ex) -> {
                    s.stop();
                });
            });

            snakeGame.ui.button.setOnMouseEntered((e) -> {
                Timeline timeline2 = new Timeline();
                KeyValue kv2 = new KeyValue(snakeGame.ui.button.translateXProperty(), -5, Interpolator.EASE_IN);
                KeyFrame kf2 = new KeyFrame(Duration.millis(50), kv2);
                timeline.getKeyFrames().add(kf);

                Timeline timeline1 = new Timeline();
                KeyValue kv1 = new KeyValue(snakeGame.ui.button.translateXProperty(), 5);
                KeyFrame kf1 = new KeyFrame(Duration.millis(50), kv1);
                timeline1.getKeyFrames().add(kf1);

                SequentialTransition s = new SequentialTransition();
                s.getChildren().addAll(timeline, timeline1);
                s.setAutoReverse(true);
                s.setCycleCount(500);
                s.play();
                snakeGame.ui.button0.setOnMouseExited((ex) -> {
                    s.stop();
                });
            });
            snakeGame.ui.button.setOnAction((e) -> {

                FadeTransition ft2 = new FadeTransition(Duration.millis(1500), root);
                ft2.setFromValue(0.5);
                ft2.setToValue(1);
                ft2.play();

                stage.setScene(scene);
                try {
                    snakeGame.stop();
                } catch (Exception ex) {
                    Logger.getLogger(GamesPlatform.class.getName()).log(Level.SEVERE, null, ex);
                }
                root.setTranslateX(425);
                Timeline timeline1 = new Timeline();
                KeyValue kv1 = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_BOTH);
                KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);
                timeline1.getKeyFrames().add(kf1);
                timeline1.play();

            });
            snakeGame.ui.button1.setOnAction((e) -> {

                snakeGame.stopsnake();
            });
            snakeGame.ui.button2.setOnAction((e) -> {
                snakeGame.startsnake();
            });

        });

        // XO MENU
        rootxo.butto.setOnMouseEntered((MouseEvent event) -> {
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootxo.imageView.translateXProperty(), -5, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(50), kv);
            timeline.getKeyFrames().add(kf);

            Timeline timeline1 = new Timeline();
            KeyValue kv1 = new KeyValue(rootxo.imageView.translateXProperty(), 5);
            KeyFrame kf1 = new KeyFrame(Duration.millis(50), kv1);
            timeline1.getKeyFrames().add(kf1);

            SequentialTransition s = new SequentialTransition();
            s.getChildren().addAll(timeline, timeline1);
            s.setAutoReverse(true);
            s.setCycleCount(500);
            s.play();

            rootxo.butto.setOnMouseExited((ex) -> {
                s.stop();
            });

        });

        rootxo.butto.setOnMouseClicked((MouseEvent event) -> {
            FadeTransition ft = new FadeTransition(Duration.millis(1500), root);
            ft.setFromValue(0.5);
            ft.setToValue(1);
            ft.play();

            root.setTranslateX(425);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

            stage.setScene(scene);
        });
        rootxo.imageView.setOnMouseClicked((MouseEvent event) -> {
            FadeTransition ft = new FadeTransition(Duration.millis(1500), root);
            ft.setFromValue(0.5);
            ft.setToValue(1);
            ft.play();

            root.setTranslateX(425);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

            stage.setScene(scene);
        });
        rootxo.button1.setOnMouseClicked((MouseEvent event) -> {
            Platform.exit();
        });

        rootxo.button1.setOnMouseEntered((MouseEvent event) -> {
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootxo.imageView0.translateXProperty(), -5, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(50), kv);
            timeline.getKeyFrames().add(kf);

            Timeline timeline1 = new Timeline();
            KeyValue kv1 = new KeyValue(rootxo.imageView0.translateXProperty(), 5);
            KeyFrame kf1 = new KeyFrame(Duration.millis(50), kv1);
            timeline1.getKeyFrames().add(kf1);

            SequentialTransition s = new SequentialTransition();
            s.getChildren().addAll(timeline, timeline1);
            s.setAutoReverse(true);
            s.setCycleCount(500);
            s.play();

            rootxo.button1.setOnMouseExited((ex) -> {
                s.stop();
            });
        });

        rootxo.imageView3.setOnMouseClicked((MouseEvent event) -> {
            FadeTransition ft = new FadeTransition(Duration.millis(1500), rootOff);
            ft.setFromValue(0.5);
            ft.setToValue(1);
            ft.play();

            rootOff.setTranslateX(-425);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootOff.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

            stage.setScene(sceneOff);
        });

        rootxo.button3.setOnMouseEntered((MouseEvent event) -> {
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootxo.imageView3.translateYProperty(), -5, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(50), kv);
            timeline.getKeyFrames().add(kf);

            Timeline timeline1 = new Timeline();
            KeyValue kv1 = new KeyValue(rootxo.imageView3.translateYProperty(), 5);
            KeyFrame kf1 = new KeyFrame(Duration.millis(50), kv1);
            timeline1.getKeyFrames().add(kf1);

            SequentialTransition s = new SequentialTransition();
            s.getChildren().addAll(timeline, timeline1);
            s.setAutoReverse(true);
            s.setCycleCount(500);
            s.play();
            rootxo.button3.setOnMouseExited((ex) -> {
                s.stop();
            });
        });
          
        //here
        rootxo.button.setOnMouseClicked((MouseEvent event) -> {
         GamesList rootList = new GamesList();
         Scene sceneList = new Scene(rootList, 850, 600);
        sceneList.setFill(Color.BLACK);
         rootList.button.setOnMouseClicked((MouseEvent eventS) -> {
            FadeTransition ft = new FadeTransition(Duration.millis(1500), rootxo);
            ft.setFromValue(0.5);
            ft.setToValue(1);
            ft.play();

            rootxo.setTranslateX(-425);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootxo.translateXProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

            stage.setScene(sceneXo);
        });
        rootList.button0.setOnMouseClicked((MouseEvent eventS) -> {
            Platform.exit();
        });
            FadeTransition ft = new FadeTransition(Duration.millis(1500), rootList);
            ft.setFromValue(0.5);
            ft.setToValue(1);
            ft.play();

            rootList.setTranslateY(-300);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootList.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

            stage.setScene(sceneList);

            try {
                gameArchives = new ArrayList<GameArchive>();
                gameArchiveDao.selectGameArchive(gameArchives);
                
                ObservableList<String> arr = FXCollections.observableArrayList();

                Iterator<GameArchive> itr = gameArchives.iterator();
                Iterator<GameArchive> itr1 = gameArchives.iterator();
                while (itr.hasNext()) {

//                        String st = String.valueOf(itr.next().getId());
                    String st = "Game ::  ID: ";
                    st += String.valueOf(itr1.next().id);
                    st += "    Date: ";
                    st += itr.next().getDate();
                    arr.add(st);
                    arr.iterator().next();

                }
                
                rootList.listView.setItems(arr);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
  // HistoryList
 

        rootList.listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                
                String st = rootList.listView.getSelectionModel().getSelectedItems() + "";
                String[] arr1 = st.split("[ ]");
                int intId;
                
                if (arr1.length > 3) {
                    intId = Integer.parseInt(arr1[4]);
//                System.out.println(intId);
                    GameRecord g = new GameRecord(intId);
                
                    try {
                        gameMoveDao.selectGameRecord(g);
                    } catch (Exception ex) {
                        Logger.getLogger(GamesPlatform.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    GameMove[] gmMoves = g.getGameMove();
                    int[] moves = new int[9];
                    char[] movesType = new char[9];

                    for (int i = 0; i < g.getI(); i++) {
//                    System.out.println(gmMoves[i]);
                        moves[i] = gmMoves[i].getCellNumber() - 1;
                        movesType[i] = gmMoves[i].getCellType();
                    }

                    XO_GUI loadedGame = new XO_GUI();
                    loadedGame.button11.setVisible(false);
                    loadedGame.textField.setVisible(false);
                    loadedGame.textField0.setVisible(false);
                    loadedGame.button10.setVisible(false);
                    loadedGame.circle.setVisible(false);
                    loadedGame.pane.setStyle("-fx-background-color: black;");
//                try {
//                    loadedGame.imageView1.setImage(new Image(new FileInputStream("bkbk.png")));
//                } catch (FileNotFoundException ex) {
//                    Logger.getLogger(GamesPlatform.class.getName()).log(Level.SEVERE, null, ex);
//                }
                    for (int i = 0; i < 9; i++) {
                        loadedGame.buttons[i].setVisible(false);

                    }
                    for (Button button : loadedGame.buttons) {
                        button.setMouseTransparent(true);
                    }
                    Scene sceneLoaded = new Scene(loadedGame, 850, 600);
                    sceneLoaded.getStylesheets().add(getClass().getResource("styles.css").toString());
                    stage.setScene(sceneLoaded);

                    Thread thLoaded1 = new Thread(() -> {

                        for (int i = 0; i < g.getI(); i++) {
                            try {
//                            System.out.println(moves[i]);
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(GamesPlatform.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            loadedGame.buttons[moves[i]].setVisible(true);

                        }
                    });

                    Thread thLoaded = new Thread(() -> {

                        for (int i = 0; i < g.getI(); i++) {
                            if (String.valueOf(movesType[i]).equals("X")) {
                                loadedGame.buttons[moves[i]].getStyleClass().add("defClass");

                                loadedGame.buttons[moves[i]].getStyleClass().add("Xclass");
                            } else {
                                loadedGame.buttons[moves[i]].getStyleClass().add("defClass");

                                loadedGame.buttons[moves[i]].getStyleClass().add("Oclass");
                            }

                            loadedGame.buttons[moves[i]].setText(String.valueOf(movesType[i]));
                        }
                        thLoaded1.start();
                    });
                    try {
                        Platform.runLater(thLoaded);
//                    thLoaded.start();
                    } catch (Exception e) {

                    } finally {
                    }

                    loadedGame.button0.setOnMouseClicked((e) -> {
                        thLoaded.stop();

                        Platform.exit();

                    });
                    loadedGame.button.setOnMouseClicked((e) -> {
                        thLoaded.stop();
                        stage.setScene(sceneList);

                    });
                }
                
            }
        });
        });

        rootxo.button3.setOnMouseClicked((MouseEvent event) -> {
            FadeTransition ft = new FadeTransition(Duration.millis(1500), rootOff);
            ft.setFromValue(0.5);
            ft.setToValue(1);
            ft.play();

            rootOff.setTranslateY(-300);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootOff.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

            stage.setScene(sceneOff);
        });
        rootxo.imageView2.setOnMouseClicked((MouseEvent event) -> {
            FadeTransition ft = new FadeTransition(Duration.millis(1500), rootOn);
            ft.setFromValue(0.5);
            ft.setToValue(1);
            ft.play();

            rootOn.setTranslateY(-300);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootOn.translateYProperty(), 0, Interpolator.EASE_BOTH);
            KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

            stage.setScene(sceneOn);
        });

        rootxo.button2.setOnMouseEntered((MouseEvent event) -> {
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootxo.imageView2.translateYProperty(), -5, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(50), kv);
            timeline.getKeyFrames().add(kf);

            Timeline timeline1 = new Timeline();
            KeyValue kv1 = new KeyValue(rootxo.imageView2.translateYProperty(), 5);
            KeyFrame kf1 = new KeyFrame(Duration.millis(50), kv1);
            timeline1.getKeyFrames().add(kf1);

            SequentialTransition s = new SequentialTransition();
            s.getChildren().addAll(timeline, timeline1);
            s.setAutoReverse(true);
            s.setCycleCount(500);
            s.play();
            rootxo.button2.setOnMouseExited((ex) -> {
                s.stop();
            });
        });

        rootxo.button2.setOnMouseClicked((MouseEvent event) -> {
            FadeTransition ft = new FadeTransition(Duration.millis(1500), rootOn);
            ft.setFromValue(0.5);
            ft.setToValue(1);
            ft.play();

            rootOn.setTranslateY(-300);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootOn.translateYProperty(), 0, Interpolator.EASE_BOTH);
            KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

            stage.setScene(sceneOn);
        });

        // XO OFFLINE
        rootOff.button0.setOnMouseClicked((MouseEvent event) -> {
            Platform.exit();
        });

        rootOff.button0.setOnMouseEntered((MouseEvent event) -> {
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootOff.imageView0.translateXProperty(), -5, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(50), kv);
            timeline.getKeyFrames().add(kf);

            Timeline timeline1 = new Timeline();
            KeyValue kv1 = new KeyValue(rootOff.imageView0.translateXProperty(), 5);
            KeyFrame kf1 = new KeyFrame(Duration.millis(50), kv1);
            timeline1.getKeyFrames().add(kf1);

            SequentialTransition s = new SequentialTransition();
            s.getChildren().addAll(timeline, timeline1);
            s.setAutoReverse(true);
            s.setCycleCount(500);
            s.play();
            rootOff.button0.setOnMouseExited((ex) -> {
                s.stop();
            });

        });
        rootOff.button.setOnMouseEntered((MouseEvent event) -> {
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootOff.imageView.translateXProperty(), -5, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(50), kv);
            timeline.getKeyFrames().add(kf);

            Timeline timeline1 = new Timeline();
            KeyValue kv1 = new KeyValue(rootOff.imageView.translateXProperty(), 5);
            KeyFrame kf1 = new KeyFrame(Duration.millis(50), kv1);
            timeline1.getKeyFrames().add(kf1);

            SequentialTransition s = new SequentialTransition();
            s.getChildren().addAll(timeline, timeline1);
            s.setAutoReverse(true);
            s.setCycleCount(500);
            s.play();
            rootOff.button.setOnMouseExited((ex) -> {
                s.stop();
            });
        });

        rootOff.button.setOnMouseClicked((MouseEvent event) -> {
            FadeTransition ft = new FadeTransition(Duration.millis(1500), rootxo);
            ft.setFromValue(0.5);
            ft.setToValue(1);
            ft.play();

            rootxo.setTranslateY(300);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootxo.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

            stage.setScene(sceneXo);
        });

        rootOff.button1.setOnMouseEntered((MouseEvent event) -> {
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootOff.imageView1.translateYProperty(), -5, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(50), kv);
            timeline.getKeyFrames().add(kf);

            Timeline timeline1 = new Timeline();
            KeyValue kv1 = new KeyValue(rootOff.imageView1.translateYProperty(), 5);
            KeyFrame kf1 = new KeyFrame(Duration.millis(50), kv1);
            timeline1.getKeyFrames().add(kf1);

            SequentialTransition s = new SequentialTransition();
            s.getChildren().addAll(timeline, timeline1);
            s.setAutoReverse(true);
            s.setCycleCount(500);
            s.play();
            rootOff.button1.setOnMouseExited((ex) -> {
                s.stop();
            });
        });

//      ENTER A MULTIPLAYER OFFLINE ROOM :
        rootOff.button2.setOnMouseClicked((MouseEvent event) -> {
            try {
                aiOffline = new XO_offlineAiGui();
                Scene XO_OfflineScene = new Scene(aiOffline, 850, 650);
                XO_OfflineScene.setFill(Color.BLACK);
                XO_OfflineScene.getStylesheets().add(getClass().getResource("styles.css").toString());
                FadeTransition ft = new FadeTransition(Duration.millis(1500), aiOffline);
                ft.setFromValue(0.5);
                ft.setToValue(1);
                ft.play();

                FadeTransition[] ftt = new FadeTransition[9];
                int j = 1;

                for (int i = 0; i < 9; i++) {
                    ftt[i] = new FadeTransition(Duration.millis(1000 * j), aiOffline.buttons[i]);
                    j++;
                    ftt[i].setFromValue(0);
                    ftt[i].setToValue(1);
                    ftt[i].play();
                }

                aiOffline.setTranslateY(-300);
                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(aiOffline.translateYProperty(), 0, Interpolator.EASE_IN);
                KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
                timeline.getKeyFrames().add(kf);
                timeline.play();

                stage.setScene(XO_OfflineScene);
            } catch (Exception ex) {
                Logger.getLogger(GamesPlatform.class.getName()).log(Level.SEVERE, null, ex);
            }
            // offline multiplayer
            aiOffline.button.setOnAction((e) -> {

                FadeTransition ft = new FadeTransition(Duration.millis(1500), rootOff);
                ft.setFromValue(0.5);
                ft.setToValue(1);
                ft.play();

                rootOff.setTranslateY(-300);
                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(rootOff.translateYProperty(), 0, Interpolator.EASE_IN);
                KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
                timeline.getKeyFrames().add(kf);
                timeline.play();

                stage.setScene(sceneOff);
            });
            aiOffline.button0.setOnAction((e) -> {

                Platform.exit();
            });

        });
//      ENTER A MULTIPLAYER OFFLINE ROOM :
        rootOff.button1.setOnMouseClicked((MouseEvent event) -> {
            try {
                t = new XO_offlineGui();
                Scene XO_OfflineScene = new Scene(t, 850, 650);
                XO_OfflineScene.setFill(Color.BLACK);
                XO_OfflineScene.getStylesheets().add(getClass().getResource("styles.css").toString());
                FadeTransition ft = new FadeTransition(Duration.millis(1500), t);
                ft.setFromValue(0.5);
                ft.setToValue(1);
                ft.play();

                FadeTransition[] ftt = new FadeTransition[9];
                int j = 1;

                for (int i = 0; i < 9; i++) {
                    ftt[i] = new FadeTransition(Duration.millis(1000 * j), t.buttons[i]);
                    j++;
                    ftt[i].setFromValue(0);
                    ftt[i].setToValue(1);
                    ftt[i].play();
                }

                t.setTranslateY(-300);
                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(t.translateYProperty(), 0, Interpolator.EASE_IN);
                KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
                timeline.getKeyFrames().add(kf);
                timeline.play();

                stage.setScene(XO_OfflineScene);
            } catch (Exception ex) {
                Logger.getLogger(GamesPlatform.class.getName()).log(Level.SEVERE, null, ex);
            }
            // offline multiplayer
            t.button.setOnAction((e) -> {

                FadeTransition ft = new FadeTransition(Duration.millis(1500), rootOff);
                ft.setFromValue(0.5);
                ft.setToValue(1);
                ft.play();

                rootOff.setTranslateY(-300);
                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(rootOff.translateYProperty(), 0, Interpolator.EASE_IN);
                KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
                timeline.getKeyFrames().add(kf);
                timeline.play();

                stage.setScene(sceneOff);
            });
            t.button0.setOnAction((e) -> {

                Platform.exit();
            });

        });

        // XO ONLINE
        rootOn.button0.setOnMouseEntered((MouseEvent event) -> {
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootOn.imageView0.translateXProperty(), -5, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(50), kv);
            timeline.getKeyFrames().add(kf);

            Timeline timeline1 = new Timeline();
            KeyValue kv1 = new KeyValue(rootOn.imageView0.translateXProperty(), 5);
            KeyFrame kf1 = new KeyFrame(Duration.millis(50), kv1);
            timeline1.getKeyFrames().add(kf1);

            SequentialTransition s = new SequentialTransition();
            s.getChildren().addAll(timeline, timeline1);
            s.setAutoReverse(true);
            s.setCycleCount(500);
            s.play();
            rootOn.button0.setOnMouseExited((ex) -> {
                s.stop();
            });
        });

        rootOn.button0.setOnMouseClicked((MouseEvent event) -> {
            Platform.exit();
        });

        rootOn.button.setOnMouseEntered((MouseEvent event) -> {
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootOn.imageView.translateXProperty(), -5, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(50), kv);
            timeline.getKeyFrames().add(kf);

            Timeline timeline1 = new Timeline();
            KeyValue kv1 = new KeyValue(rootOn.imageView.translateXProperty(), 5);
            KeyFrame kf1 = new KeyFrame(Duration.millis(50), kv1);
            timeline1.getKeyFrames().add(kf1);

            SequentialTransition s = new SequentialTransition();
            s.getChildren().addAll(timeline, timeline1);
            s.setAutoReverse(true);
            s.setCycleCount(500);
            s.play();
            rootOn.button.setOnMouseExited((ex) -> {
                s.stop();
            });
        });

        rootOn.button.setOnMouseClicked((MouseEvent event) -> {
            FadeTransition ft = new FadeTransition(Duration.millis(1500), rootxo);
            ft.setFromValue(0.5);
            ft.setToValue(1);
            ft.play();

            rootxo.setTranslateY(300);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootxo.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

            stage.setScene(sceneXo);

        });

        rootOn.button1.setOnMouseEntered((MouseEvent event) -> {
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootOn.imageView1.translateYProperty(), -5, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(50), kv);
            timeline.getKeyFrames().add(kf);

            Timeline timeline1 = new Timeline();
            KeyValue kv1 = new KeyValue(rootOn.imageView1.translateYProperty(), 5);
            KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);
            timeline1.getKeyFrames().add(kf1);

            SequentialTransition s = new SequentialTransition();
            s.getChildren().addAll(timeline, timeline1);
            s.setAutoReverse(true);
            s.setCycleCount(500);
            s.play();
            rootOn.button1.setOnMouseExited((ex) -> {
                s.stop();
            });
        });

        rootOn.button1.setOnMouseClicked((MouseEvent event) -> {

            try {
                s = new Host();

                s.init();
                s.start(stage);
            } catch (Exception e) {
            }
            s.gb.button0.setOnAction((e) -> {

                Platform.exit();

            });
            s.gb.button.setOnAction((e) -> {
                FadeTransition ft = new FadeTransition(Duration.millis(1500), rootOn);
                ft.setFromValue(0.5);
                ft.setToValue(1);
                ft.play();

                rootOn.setTranslateY(300);
                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(rootOn.translateYProperty(), 0, Interpolator.EASE_IN);
                KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
                timeline.getKeyFrames().add(kf);
                timeline.play();

                stage.setScene(sceneOn);
                try {
                    if (s != null) {
                        s.stop();

                    }
                    if (s1 != null) {
                        s1.stop();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            });

            FadeTransition ft = new FadeTransition(Duration.millis(1500), s.gb);
            ft.setFromValue(0.5);
            ft.setToValue(1);
            ft.play();

            s.gb.setTranslateY(-300);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(s.gb.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

            FadeTransition[] ftt = new FadeTransition[9];
            int j = 1;

            for (int i = 0; i < 9; i++) {
                ftt[i] = new FadeTransition(Duration.millis(1000 * j), s.gb.buttons[i]);
                j++;
                ftt[i].setFromValue(0);
                ftt[i].setToValue(1);
                ftt[i].play();
            }

        });

        rootOn.button2.setOnMouseEntered((MouseEvent event) -> {
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(rootOn.imageView2.translateYProperty(), -5, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(50), kv);
            timeline.getKeyFrames().add(kf);

            Timeline timeline1 = new Timeline();
            KeyValue kv1 = new KeyValue(rootOn.imageView2.translateYProperty(), 5);
            KeyFrame kf1 = new KeyFrame(Duration.millis(50), kv1);
            timeline1.getKeyFrames().add(kf1);

            SequentialTransition s = new SequentialTransition();
            s.getChildren().addAll(timeline, timeline1);
            s.setAutoReverse(true);
            s.setCycleCount(500);
            s.play();
            rootOn.button2.setOnMouseExited((ex) -> {
                s.stop();
            });
        });

        rootOn.button2.setOnMouseClicked((MouseEvent event) -> {

            try {
                s1 = new Guest();
                s1.init();
                s1.start(stage);
            } catch (Exception e) {
            }
            s1.gb.button0.setOnAction((e) -> {

                Platform.exit();

            });
            s1.gb.button.setOnAction((e) -> {

                FadeTransition ft = new FadeTransition(Duration.millis(1500), rootOn);
                ft.setFromValue(0.5);
                ft.setToValue(1);
                ft.play();

                rootOn.setTranslateY(300);
                Timeline timeline = new Timeline();
                KeyValue kv = new KeyValue(rootOn.translateYProperty(), 0, Interpolator.EASE_IN);
                KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
                timeline.getKeyFrames().add(kf);
                timeline.play();

                stage.setScene(sceneOn);
                try {
                    if (s != null) {
                        s.stop();

                    }
                    if (s1 != null) {
                        s1.stop();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            });

            FadeTransition ft = new FadeTransition(Duration.millis(1500), s1.gb);
            ft.setFromValue(0.5);
            ft.setToValue(1);
            ft.play();

            s1.gb.setTranslateY(-300);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(s1.gb.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

            FadeTransition[] ftt = new FadeTransition[9];
            int j = 1;
            for (int i = 0; i < 9; i++) {
                ftt[i] = new FadeTransition(Duration.millis(1000 * j), s1.gb.buttons[i]);
                j++;
                ftt[i].setFromValue(0);
                ftt[i].setToValue(1);
                ftt[i].play();
            }

            /*stage.setScene(sceneOnClient);*/
        });

      

        //connect 4
        rootConnect.imageView.setOnMouseClicked((MouseEvent event) -> {
            FadeTransition ft = new FadeTransition(Duration.millis(1500), root);
            ft.setFromValue(0.5);
            ft.setToValue(1);
            ft.play();

            root.setTranslateY(-300);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

            stage.setScene(scene);

        });
        rootConnect.imageView0.setOnMouseClicked((MouseEvent event) -> {
            Platform.exit();
        });

    }

    @Override
    public void stop() throws Exception {
        if (snakeGame != null) {
            snakeGame.stop();
        }
        try {
            if (s != null) {
                s.stop();

            }
            if (s1 != null) {
                s1.stop();
            }

        } catch (Exception e) {

        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}
