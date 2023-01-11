package main.java.com.samuel.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import main.java.com.samuel.protocol.KnockKnockProtocol;

public class SimpleServer {


    private static ServerSocket serverSocket ;
    public static void main(String[] args) {

      

        try {
             serverSocket = new ServerSocket(8443);
            Socket socketClient = serverSocket.accept();

            PrintWriter out = new PrintWriter(socketClient.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            String inputLine;
            String outputLine;
           
            KnockKnockProtocol kkp = new KnockKnockProtocol();
            
            outputLine = kkp.processInput(null);
            out.println(outputLine);
            System.out.println("server started"+outputLine);
            while ((inputLine = in.readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                if (inputLine.equalsIgnoreCase("bye"))
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
