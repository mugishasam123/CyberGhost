package com.samuel.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.samuel.protocol.KnockKnockProtocol;
import com.samuel.utils.Decrypt;
import com.samuel.utils.Encrypt;

public class ServerThread extends Thread {

    private Socket socket = null;

    public ServerThread(Socket socket) {
        //super("ServerThread");
        this.socket = socket;
    }

    public void run() {
        
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
                    System.out.println("server connected...");
            String inputLine;
            String outputLine;

            KnockKnockProtocol kkp = new KnockKnockProtocol();

            outputLine = kkp.processInput(null);
            out.println(outputLine);
            System.out.println("server started" + Encrypt.encryptMessage(outputLine));
            while ((inputLine = in.readLine()) != null) {
                outputLine = kkp.processInput(Decrypt.decryptMessage(inputLine));
                out.println(Encrypt.encryptMessage(outputLine));
                if (inputLine.equalsIgnoreCase("bye"))
                    break;
            }
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
