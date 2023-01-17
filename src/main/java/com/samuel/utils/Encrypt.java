package com.samuel.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

public class Encrypt {

    private static String cipherText;

    public static String encryptMessage(String rawMessage) {

        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(2048);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
            cipher.update(rawMessage.getBytes());
            cipherText = new String(cipher.doFinal());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cipherText;
    }
}
