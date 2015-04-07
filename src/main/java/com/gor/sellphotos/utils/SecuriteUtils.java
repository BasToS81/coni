package com.gor.sellphotos.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecuriteUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecuriteUtils.class);

    private static byte[] cleDeCryptage = new byte[] {
                    0x05, 0x35, 0x20, 0x65, 0x07, 0x35, 0x06, 0x07, 0x70, 0x22, 0x63,
                    0x41, 0x02, 0x00, 0x32, 0x01
    };

    public static String crypterTexte(String texte) {
        try
        {

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            final SecretKeySpec secretKey = new SecretKeySpec(cleDeCryptage, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            String encryptedString = Base64.encodeBase64URLSafeString(cipher.doFinal(texte.getBytes()));
            // String encodedString = URLEncoder.encode(encryptedString, "UTF-8");
            return encryptedString;
        }
        catch (Exception e)
        {
            LOGGER.error("Erreur durant l'encription du texte", e);
        }
        return null;
    }

}
