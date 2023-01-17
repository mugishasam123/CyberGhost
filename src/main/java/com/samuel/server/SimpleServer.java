package com.samuel.server;

import java.io.*;
import java.net.*;




public class SimpleServer {
    
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket ;
        try {
            
           serverSocket = new ServerSocket(5000);
         
        
            boolean listening = true;

            while (listening) {
               new ServerThread( serverSocket.accept()).start();;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
