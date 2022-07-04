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
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Host extends Application {

    public XO_GUI gb;
    Thread thread;
    boolean thExit = false;
    boolean threadExit = false;

    Thread th;
    ServerSide server;
    Thread sThread;
    Stage stage;

    @Override
    public void init() throws Exception {
        server = new ServerSide();
        sThread = new Thread(()
                -> {
            while (true) {
                try {
                    server.run();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
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
            while (!thExit) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Platform.runLater(()
                        -> {
                    gb.applyChanges();
                    gb.checkForWinner();
                    if (GameHandler.clientsList.size() != 2) {
                        gb.circle.setFill(Color.RED);
                    } else {
                        gb.circle.setFill(Color.GREEN);
                    }
                });
            }
        });

    }

    @Override
    public void start(Stage stage) throws Exception {
        sThread.start();
        th.start();
        gb = new XO_GUI();
        gb.getKeyLabel().setText("Key");
        InetAddress host = InetAddress.getLocalHost();
        System.out.println(host.getHostAddress());
        gb.s = new Socket(host.getHostName(), 5005);

        try {
            gb.dis = new DataInputStream(gb.s.getInputStream());
            gb.ps = new PrintStream(gb.s.getOutputStream());
            gb.player = gb.dis.readLine();
            gb.button11.setVisible(false);
            gb.textField0.setEditable(false);

            gb.textField0.setText("" + host.getHostName());
            if (gb.player.contains("X")) {
                gb.flag = false;
            } else {
                gb.flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(gb);
        try {
            // scene.getStylesheets().add(getClass().getResource("./styles.css").toString());
            scene.getStylesheets().add(getClass().getResource("styles.css").toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        thread.start();

    }

    @Override
    public void stop() throws Exception {
        try {

            for (GameHandler e : ServerSide.ch) {
                e.exit = true;

            }
            //ServerSide.ch = null;
            gb.ps.println("Close" + gb.player);
            thExit = true;
            threadExit = true;
            server.serversocket.close();
            sThread.stop();
            /*gb.dis.close();
                gb.ps.close();
                gb.s.close();*/

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
