package main.java.com.samuel.utils;

import java.io.*;
import java.security.*;
import javax.net.*;

public class TrustFactory {

    public static X509TrustManager trustManage(String certUrl, String pass) {

        try {
            // TrustManagerFactory
          
            KeyStore trustStore = KeyStore.getInstance("PKCS12");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("PKIX", "SunJSSE");
            InputStream inputStream1 = ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(certUrl);
            trustStore.load(inputStream1, pass.toCharArray());
            trustManagerFactory.init(trustStore);
            X509TrustManager x509TrustManager = null;
            for (TrustManager trustManager : trustManagerFactory.getTrustManagers()) {
                if (trustManager instanceof X509TrustManager) {
                    x509TrustManager = (X509TrustManager) trustManager;
                    break;
                }
            }

            if (x509TrustManager == null)
                throw new NullPointerException();

            return x509TrustManager;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
