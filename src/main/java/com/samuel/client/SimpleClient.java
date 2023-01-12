package main.java.com.samuel.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class SimpleClient {

    private static Socket  kkSocket;
    public static void main(String[] args){
        try {
             kkSocket = new Socket("127.0.0.1",8443);
             
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
           
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(kkSocket.getInputStream()));
                   
            BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));
                   
            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye."))
                    break;
                System.out.println("Request to the server:");
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}