package com.samuel.client;

import java.io.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.*;

import com.samuel.utils.TrustFactory;
import com.samuel.utils.Decrypt;
import com.samuel.utils.Encrypt;
import com.samuel.utils.KeyFactory;

public class SimpleClient {

    private static final String[] cipher_suites = new String[] { "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256" };

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {
        try {

            System.setProperty("javax.net.debug", "all");
            X509KeyManager x509KeyManager = KeyFactory.keyManage("client/client-certificate.p12", "samuel");
            X509TrustManager x509TrustManager = TrustFactory.trustManage("server/server-certificate.p12", "mugisha");

            // set up the SSL Context
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(new KeyManager[] { x509KeyManager }, new TrustManager[] { x509TrustManager }, null);

            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            SSLSocket kkSocket = (SSLSocket) socketFactory.createSocket("127.0.0.1", 5000);
            kkSocket.setEnabledProtocols(new String[] { "TLSv1.2" });
            //kkSocket.setEnabledCipherSuites(cipher_suites);

            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(kkSocket.getInputStream()));

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String fromServer;
            String fromUser;

            System.out.println("client connected...");
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + Decrypt.decryptMessage(fromServer));
                if (fromServer.equals("Bye."))
                    break;
                System.out.println("Request to the server:");
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(Encrypt.encryptMessage(fromUser));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}