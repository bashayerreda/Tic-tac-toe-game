package xogame.online;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import xogame.online.GameHandler;

public class ServerSide {
    ServerSocket serversocket ;
    static ArrayList<GameHandler> ch = new ArrayList<GameHandler>();
    boolean exit  = false;
    
    
    public ServerSide()
    {
        try
        {
                serversocket = new ServerSocket(5005);
            
          }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }
    public void run() throws IOException
    {
                    
                  Socket socket;
                  socket = serversocket.accept();
                  ch.add(new GameHandler(socket));

    }
}
