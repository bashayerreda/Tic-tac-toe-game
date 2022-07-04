/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogame.online;

import gamesplatform.XO_GUI;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Guest extends Application {

    public XO_GUI gb;
    Thread thread;
    Thread th;
    boolean thExit = false;
    boolean threadExit = false;

    @Override
    public void init() throws Exception {
        thread = new Thread(()
                -> {
            while (!threadExit) {
                try {
                    gb.recievedString = gb.dis.readLine();
                    gb.flag = !gb.flag;

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        });
        th = new Thread(()
                -> {
            while (!threadExit) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
                Platform.runLater(() -> {
                    gb.applyChanges();
                    gb.checkForWinner();
                });
            }
        });

    }

    @Override
    public void start(Stage stage) throws Exception {

        gb = new XO_GUI();
        gb.getKeyLabel().setText("Enter Host's Key");
        gb.button11.setOnAction(ex -> {

            try {
                InetAddress host = InetAddress.getByName(gb.textField0.getText());
                gb.s = new Socket(host, 5005);
                gb.dis = new DataInputStream(gb.s.getInputStream());
                gb.ps = new PrintStream(gb.s.getOutputStream());
                gb.player = gb.dis.readLine();
                if (gb.player.contains("X")) {
                    gb.flag = false;
                } else {
                    gb.flag = true;
                }
                gb.circle.setFill(Color.GREEN);
                thread.start();
                th.start();
            } catch (ConnectException e) {
                gb.textField.setText("Wrong Ip Address");
                // e.printStackTrace();
            } catch (Exception exi) {
                exi.printStackTrace();
            }

        });

        Scene scene = new Scene(gb);
        try {
            scene.getStylesheets().add(getClass().getResource("styles.css").toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        // stage.show();

    }

    @Override
    public void stop() throws Exception {
        try {
            if (gb.ps != null) {
                gb.ps.println("Close" + gb.player);
            }
            thExit = true;
            threadExit = true;
            /*gb.ps.close();
            gb.dis.close();
            gb.s.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
