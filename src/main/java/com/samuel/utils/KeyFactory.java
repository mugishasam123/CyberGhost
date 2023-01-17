package com.samuel.utils;

import java.io.*;
import java.security.*;
import javax.net.ssl.*;

public class KeyFactory {

    private static X509KeyManager x509KeyManager = null;

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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return x509KeyManager;

    }

}
