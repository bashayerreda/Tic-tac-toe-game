/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import snakegame.SankeUI;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class Snake extends Application {

    int key;
    int count = 15;
    int random10_50[] = new int[40];
    Image img;
    Thread th;
    Thread th1;
    Scene scene;
    public SankeUI ui;
    int sKey;
    int snakeScore;

    public void startsnake() {
        sKey = 99;
    }

    public void stopsnake() {
        sKey = 98;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        snakeScore = 0;
        sKey = 98;
        ui = new SankeUI();
        ui.paint();
        ui.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        scene = new Scene(ui, 850, 600);
        scene.setFill(Color.BLACK);
        key = 3;
        th1 = new Thread(() -> {

            scene.setOnKeyPressed((event) -> {
                KeyCode k = event.getCode();
                ui.snakeArr.get(0).setRotate(0);
                if (k.equals(KeyCode.W)) {
                    if (key != 2) {
                        ui.snakeArr.get(0).setRotate(90);
                        key = 4;
                        sKey = 99;
                    }
//                ui.snake.setOrientation(Orientation.VERTICAL);

                }
                if (k.equals(KeyCode.D)) {
                    if (key != 3) {
                        ui.snakeArr.get(0).setRotate(180);
                        key = 1;
                        sKey = 99;
                    }
//                ui.snake.setOrientation(Orientation.HORIZONTAL);

                }
                if (k.equals(KeyCode.A)) {
                    if (key != 1) {
                        ui.snakeArr.get(0).setRotate(0);
                        key = 3;
                        sKey = 99;
                    }
//                ui.snake.setOrientation(Orientation.HORIZONTAL);

                }
                if (k.equals(KeyCode.S)) {
                    if (key != 4) {
                        ui.snakeArr.get(0).setRotate(180);
                        key = 2;
                        sKey = 99;
                    }
//                ui.snake.setOrientation(Orientation.VERTICAL);

                }
            });
        });

        // from 10 to 40
        Circle xx[] = new Circle[50];
        for (int i = 10; i < 50; i++) {
            random10_50[i - 10] = i;
        }
        try {
            img = new Image(new FileInputStream("appl.png"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < 50; i++) {
            xx[i] = new Circle(20);
            xx[i].setFill(new ImagePattern(img));
//                        xx[i].setStroke(Color.BROWN);
            xx[i].setEffect(new DropShadow(+25d, 0d, +2d, Color.RED));

            ui.anchorPane.getChildren().add(xx[i]);

            xx[i].setCenterX((random10_50[(int) (Math.random() * 40)] * 10));
            xx[i].setCenterY((random10_50[(int) (Math.random() * 40)] * 10));
            xx[i].setVisible(false);
        }
        xx[(int) (Math.random() * 50)].setVisible(true);

        th = new Thread(() -> {
            while (true) {
                switch (sKey) {
                    case 99:
                        for (int i = 0; i < 50; i++) {

                            if (ui.snakeArr.get(0).intersects(xx[i].getCenterX(), xx[i].getCenterY(), 10, 10)) {
                                if (xx[i].isVisible()) {
                                    System.out.println("colliosion");
                                    ui.snakeArr.get(count).setVisible(true);
                                    ui.snakeArr.get(count + 1).setVisible(true);
                                    count += 2;
                                    snakeScore += 16;
                                    ui.score.setText(String.valueOf(snakeScore));
                                    xx[i].setVisible(false);
                                    xx[(int) (Math.random() * 50)].setVisible(true);
                                }
                            }
                        }

                        for (int i = 1; i < ui.snakeArr.size(); i++) {
                            if (ui.snakeArr.get(0).getCenterX() == ui.snakeArr.get(i).getCenterX() && ui.snakeArr.get(0).getCenterY() == ui.snakeArr.get(i).getCenterY()) {
                                if (ui.snakeArr.get(i).isVisible()) {
                                    try {

                                        System.out.println("innee collision , Game Over");
                                        Thread.sleep(10000);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                            }
                        }
                        switch (key) {
                            case 1: {
                                try {
                                    if (ui.snakeArr.get(0).getCenterX() < ui.anchorPane.getWidth() - 250) {
                                        Thread.sleep(75);
                                        for (int i = ui.snakeArr.size(); i > 1; i--) {
                                            ui.snakeArr.get(i - 1).setCenterX(ui.snakeArr.get(i - 2).getCenterX());
                                            ui.snakeArr.get(i - 1).setCenterY(ui.snakeArr.get(i - 2).getCenterY());
                                        }
                                        ui.snakeArr.get(0).setCenterX(ui.snakeArr.get(0).getCenterX() + 10);

                                    } else {
                                        System.out.println("Game Over");
                                        Thread.sleep(1000);
                                    }
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;

                            case 2:
                                try {
                                    if (ui.snakeArr.get(0).getCenterY() < ui.anchorPane.getHeight() - 10) {

                                        Thread.sleep(75);
                                        for (int i = ui.snakeArr.size(); i > 1; i--) {
                                            ui.snakeArr.get(i - 1).setCenterX(ui.snakeArr.get(i - 2).getCenterX());
                                            ui.snakeArr.get(i - 1).setCenterY(ui.snakeArr.get(i - 2).getCenterY());
                                        }
                                        ui.snakeArr.get(0).setCenterY(ui.snakeArr.get(0).getCenterY() + 10);

                                    } else {
                                        System.out.println("Game Over");
                                        Thread.sleep(1000);
                                    }
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                            case 3:
                                try {
                                    if (ui.snakeArr.get(0).getCenterX() > 10) {
                                        Thread.sleep(75);
                                        for (int i = ui.snakeArr.size(); i > 1; i--) {
                                            ui.snakeArr.get(i - 1).setCenterX(ui.snakeArr.get(i - 2).getCenterX());
                                            ui.snakeArr.get(i - 1).setCenterY(ui.snakeArr.get(i - 2).getCenterY());
                                        }
                                        ui.snakeArr.get(0).setCenterX(ui.snakeArr.get(0).getCenterX() - 10);

                                    } else {
                                        System.out.println("Game Over");
                                        Thread.sleep(1000);
                                    }
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                            case 4:
                                try {
                                    if (ui.snakeArr.get(0).getCenterY() > 10) {
                                        Thread.sleep(75);
                                        for (int i = ui.snakeArr.size(); i > 1; i--) {
                                            ui.snakeArr.get(i - 1).setCenterX(ui.snakeArr.get(i - 2).getCenterX());
                                            ui.snakeArr.get(i - 1).setCenterY(ui.snakeArr.get(i - 2).getCenterY());
                                        }
                                        ui.snakeArr.get(0).setCenterY(ui.snakeArr.get(0).getCenterY() - 10);

                                    } else {

                                        System.out.println("Game Over");
                                        Thread.sleep(1000);
                                    }
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                break;

                        }

                        break;
                    case 98: {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;

                }
            }

        });
        th.start();
        th1.start();

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        th.stop();
        th1.stop();
    }


    @Override
    public void init() throws Exception {
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }

}
