package com.samuel.server;

import java.io.*;

import javax.net.ssl.*;

import com.samuel.utils.TrustFactory;
import com.samuel.utils.KeyFactory;

public class SimpleServer {

    private static final String[] cipher_suites = new String[] { "TLS_DHE_RSA_WITH_AES_128_GCM_SHA256" };

    public static void main(String[] args) throws IOException {

        try {

            System.setProperty("javax.net.debug", "all");
            X509KeyManager x509KeyManager = KeyFactory.keyManage("server/server-certificate.p12", "mugisha");
            X509TrustManager x509TrustManager = TrustFactory.trustManage("client/client-certificate.p12", "samuel");
            // set up the SSL Context
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(new KeyManager[] { x509KeyManager }, new TrustManager[] { x509TrustManager }, null);

            SSLServerSocketFactory serverSocketFactory = sslContext.getServerSocketFactory();
            SSLServerSocket serverSocket = (SSLServerSocket) serverSocketFactory.createServerSocket(5000);
            serverSocket.setNeedClientAuth(true);
            serverSocket.setEnabledProtocols(new String[] { "TLSv1.2" });
            //serverSocket.setEnabledCipherSuites(cipher_suites);

            boolean listening = true;

            while (listening) {
                SSLSocket socket = (SSLSocket) serverSocket.accept();
                new ServerThread(socket).start();
                ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
