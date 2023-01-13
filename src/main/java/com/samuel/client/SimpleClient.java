package main.java.com.samuel.client;

import java.io.*;
import java.net.*;
import java.security.*;

import main.java.com.samuel.utils.TrustFactory;
import main.java.com.samuel.utils.KeyFactory;


public class SimpleClient {

   
    public static void main(String[] args){
        try {

            System.setProperty("javax.net.debug", "all");
            X509KeyManager x509KeyManager = KeyFactory.keyManage("client/client-certificate.p12","samuel");
            X509TrustManager x509TrustManager = TrustFactory.trustManage("server/server-certificate.p12","mugisha");
          

            // set up the SSL Context
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(new KeyManager[]{x509KeyManager}, new TrustManager[]{x509TrustManager}, null);

            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            SSLSocket kkSocket = (SSLSocket) socketFactory.createSocket("127.0.0.1", 8333);
            kkSocket.setEnabledProtocols(new String[]{"TLSv1.2"});

             
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