package com.samuel.utils;

import javax.crypto.Cipher;

public class GenerateCipher {

    private static Cipher cipher;

    public static Cipher generate() {
        try {
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipher;
    }
}
