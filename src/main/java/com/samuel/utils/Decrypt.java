package com.samuel.utils;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Decrypt {

    private static String finalMessage;

    public static String decryptMessage(String encryptedMessage) {

        try {
           
            String[] messageObject = encryptedMessage.split("-");
            Cipher cipher = GenerateCipher.generate();
            System.out.println(cipher);
            cipher.init(Cipher.PRIVATE_KEY, GenerateKeys.generatePair().getPrivate());
            byte[] decryptedKey = cipher.doFinal(messageObject[1].getBytes());
            System.out.println(decryptedKey);

            // Convert bytes to AES SecertKey
            SecretKey originalKey = new SecretKeySpec(decryptedKey, 0, decryptedKey.length, "AES");
            Cipher aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.DECRYPT_MODE, originalKey);
            byte[] bytePlainText = aesCipher.doFinal(messageObject[0].getBytes());
            finalMessage = new String(bytePlainText);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return finalMessage;
    }

    public static void main(String[] args) {
        String ciphertext = Encrypt.encryptMessage("hello");
      
        System.out.println(decryptMessage(ciphertext));
    }
}
