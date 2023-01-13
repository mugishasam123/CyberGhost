package main.java.com.samuel.utils;

import java.io.*;
import java.security.*;
import javax.net.*;



public class KeyFactory {

    public static X509KeyManager keyManage(String certUrl, String pass) {

        try {

            // KeyManagerFactory ()
            
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            InputStream inputStream = ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(certUrl);
            keyStore.load(inputStream, pass.toCharArray());
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509", "SunJSSE");
            keyManagerFactory.init(keyStore, pass.toCharArray());
            X509KeyManager x509KeyManager = null;
            for (KeyManager keyManager : keyManagerFactory.getKeyManagers()) {
                if (keyManager instanceof X509KeyManager) {
                    x509KeyManager = (X509KeyManager) keyManager;
                    break;
                }
            }
            if (x509KeyManager == null)
                throw new NullPointerException();

            return x509KeyManager;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
