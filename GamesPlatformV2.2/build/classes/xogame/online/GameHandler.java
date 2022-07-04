/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogame.online;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public class GameHandler extends Thread{
    Socket s;
    DataInputStream inputStream ;
    PrintStream outputStream;
    boolean exit = false;
    static ArrayList<GameHandler> clientsList = new ArrayList<GameHandler>();
    public GameHandler(Socket socket) 
    {
        s = socket;
            if(clientsList.size() < 2)
            {
                        try{
                        inputStream = new DataInputStream(socket.getInputStream());
                        outputStream = new PrintStream(socket.getOutputStream());
                        clientsList.add(this);
                        start();
                        }catch(IOException iex)
                        {
                            iex.printStackTrace();
                        }
             }
    }
    public void run()
    {
                if(clientsList.size() == 1)
                {
                    outputStream.println("X");
                }
                else if(clientsList.size() == 2)
                {
                    outputStream.println("O");
                }
                String msg = null;
                while(!exit)
                {
                    try{
                                    
                                    msg = inputStream.readLine();     
                                   if(msg.contains("Close"))
                                   {
                                        if(msg.contains("X"))
                                        {
                                            clientsList.get(0).inputStream.close();
                                            clientsList.get(0).outputStream.close();
                                            clientsList.get(0).s.close();
                                            GameHandler c  = clientsList.get(0);
                                            clientsList.remove(0);
                                            c.stop();
                                            

                                        }
                                        else
                                        {
                                            if(clientsList.get(1) != null)
                                            {
                                                clientsList.get(1).inputStream.close();
                                                clientsList.get(1).outputStream.close();
                                                clientsList.get(1).s.close();
                                                GameHandler c  = clientsList.get(1);
                                                clientsList.remove(1);
                                                 c.stop();
                                            }
                                            else
                                            {
                                                clientsList.get(0).inputStream.close();
                                                clientsList.get(0).outputStream.close();
                                                clientsList.get(0).s.close();
                                                clientsList.get(0).stop();
                                                clientsList.remove(0);
      
                                            
                                            }

                                        }

                                   }
                                   else
                                   {
                                            for(GameHandler client : clientsList)
                                            {
                                                client.outputStream.println(msg);
                                            }
                                  }
                    }
                       
                    
                    catch(IOException readError)
                    {
                        readError.printStackTrace();
                    }

                }
                
    }

  
}
