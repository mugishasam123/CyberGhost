package com.samuel.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class GenerateKeys {

    private static KeyPair keyPair = null;

    public static KeyPair generatePair() {

        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(2048);
            keyPair = keyPairGen.generateKeyPair();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyPair;

    }
}
