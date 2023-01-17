package com.samuel.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.samuel.protocol.KnockKnockProtocol;

public class ServerThread extends Thread {

    private Socket socket = null;

    public ServerThread(Socket socket) {
        //super("ServerThread");
        this.socket = socket;
    }

    public void run() {

        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

            String inputLine;
            String outputLine;

            KnockKnockProtocol kkp = new KnockKnockProtocol();

            outputLine = kkp.processInput(null);
            out.println(outputLine);
            System.out.println("server started" + outputLine);
            while ((inputLine = in.readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                if (inputLine.equalsIgnoreCase("bye"))
                    break;
            }
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
