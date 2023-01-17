package com.samuel.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

public class Decrypt {

    private static String finalMessage;

    public static String decryptMessage(String cipherText) {

        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(2048);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
            byte[] output = cipher.doFinal(cipherText.getBytes());
            finalMessage = new String(output);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return finalMessage;
    }
}
