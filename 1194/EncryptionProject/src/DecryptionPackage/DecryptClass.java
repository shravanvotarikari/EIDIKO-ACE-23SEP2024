package DecryptionPackage;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DecryptClass {
	private static SecretKeySpec secretkey;
    private static byte[] key;
    
    public static void setkey(final String mykey) {
        try {
        	DecryptClass.key = mykey.getBytes("UTF-8");
            final MessageDigest sha = MessageDigest.getInstance("SHA-1");
            DecryptClass.key = sha.digest(DecryptClass.key);
            DecryptClass.key = Arrays.copyOf(DecryptClass.key, 16);
            DecryptClass.secretkey = new SecretKeySpec(DecryptClass.key, "AES");
        }
        catch (NoSuchAlgorithmException ex) {}
        catch (UnsupportedEncodingException ex2) {}
    }
    public static String decryptFunction(final String strToDec, final String keyToDec) {
        try {
            setkey(keyToDec);
            final Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2, DecryptClass.secretkey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDec)));
        }
        catch (Exception ex) {
            return null;
        }		

    }
	
}
