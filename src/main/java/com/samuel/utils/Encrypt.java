package com.samuel.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Encrypt {

    private static String encryptedMessage;

    public static String encryptMessage(String rawMessage) {

        try {

            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(128); // The AES key size in number of bits
            SecretKey secKey = generator.generateKey();
            Cipher aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
            byte[] cipherText = aesCipher.doFinal(rawMessage.getBytes());
            StringBuilder cipher_key = new StringBuilder(cipherText.toString());
            cipher_key.append('-');
            Cipher cipher = GenerateCipher.generate();
            cipher_key.append(cipher);
            cipher_key.append('-');

            System.out.println(cipher);
            
            cipher.init(Cipher.PUBLIC_KEY, GenerateKeys.generatePair().getPublic());
            byte[] encryptedKey = cipher.doFinal(secKey.getEncoded()/* Seceret Key From Step 1 */);
            cipher_key.append(encryptedKey.toString());
            encryptedMessage = cipher_key.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptedMessage;
    }
}