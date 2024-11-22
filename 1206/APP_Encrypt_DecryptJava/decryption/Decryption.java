package decryption;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Decryption {

	private static SecretKeySpec secretkey;
    private static byte[] key;
    
    public static void setkey(final String mykey) {
        try {
        	Decryption.key = mykey.getBytes("UTF-8");
            final MessageDigest sha = MessageDigest.getInstance("SHA-1");
            Decryption.key = sha.digest(Decryption.key);
            Decryption.key = Arrays.copyOf(Decryption.key, 16);
            Decryption.secretkey = new SecretKeySpec(Decryption.key, "AES");
        }
        catch (NoSuchAlgorithmException ex) {}
        catch (UnsupportedEncodingException ex2) {}
    }
    public static String decrypt(final String strToDec, final String sec) {
        try {
            setkey(sec);
            final Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2, Decryption.secretkey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDec)));
        }
        catch (Exception ex) {
            return null;
        }		

    }
	
}
