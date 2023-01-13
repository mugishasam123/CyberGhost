package main.java.com.samuel.server;

import java.io.*;
import java.net.*;
import java.security.*;

import main.java.com.samuel.utils.TrustFactory;
import main.java.com.samuel.utils.KeyFactory;

public class SimpleServer {

    public static void main(String[] args) throws IOException {

        try {
            

            System.setProperty("javax.net.debug", "all");
            X509KeyManager x509KeyManager = KeyFactory.keyManage("server/server-certificate.p12","mugisha");
            X509TrustManager x509TrustManager = TrustFactory.trustManage("client/client-certificate.p12","samuel");
            // set up the SSL Context
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(new KeyManager[]{x509KeyManager}, new TrustManager[]{x509TrustManager}, null);

            SSLServerSocketFactory serverSocketFactory = sslContext.getServerSocketFactory();
            SSLServerSocket serverSocket = (SSLServerSocket) serverSocketFactory.createServerSocket(8333);
            serverSocket.setNeedClientAuth(true);
            serverSocket.setEnabledProtocols(new String[]{"TLSv1.2"});
            SSLSocket socket = (SSLSocket) serverSocket.accept();
            
            boolean listening = true;

            while (listening) {
               new ServerThread(socket);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
