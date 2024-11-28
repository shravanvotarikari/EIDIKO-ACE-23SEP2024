package EncryptionPackage;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptClass {
	static SecretKeySpec secretkey;
    private static byte[] key;
    
    public static void setkey(final String mykey) {
        try {
        	EncryptClass.key = mykey.getBytes("UTF-8");
            final MessageDigest sha = MessageDigest.getInstance("SHA-1");
            EncryptClass.key = sha.digest(EncryptClass.key);
            EncryptClass.key = Arrays.copyOf(EncryptClass.key, 16);
            EncryptClass.secretkey = new SecretKeySpec(EncryptClass.key, "AES");
        }
        catch (NoSuchAlgorithmException ex) {}
        catch (UnsupportedEncodingException ex2) {}
    }
    
    public static String encryptFunction(final String strToEnc, final String keyToEnc) {
        try {
            setkey(keyToEnc);
            final Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(1, EncryptClass.secretkey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEnc.getBytes("UTF-8")));
        }
        catch (Exception ex) {
            return null;
        }
    }

}
