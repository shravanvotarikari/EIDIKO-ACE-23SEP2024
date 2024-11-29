package encryption;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {
	static SecretKeySpec secretkey;
    private static byte[] key;
    
    public static void setkey(final String mykey) {
        try {
        	Encryption.key = mykey.getBytes("UTF-8");
            final MessageDigest sha = MessageDigest.getInstance("SHA-1");
            Encryption.key = sha.digest(Encryption.key);
            Encryption.key = Arrays.copyOf(Encryption.key, 16);
            Encryption.secretkey = new SecretKeySpec(Encryption.key, "AES");
        }
        catch (NoSuchAlgorithmException ex) {}
        catch (UnsupportedEncodingException ex2) {}
    }
    
    public static String encrypt(final String strToEnc, final String sec) {
        try {
            setkey(sec);
            final Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(1, Encryption.secretkey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEnc.getBytes("UTF-8")));
        }
        catch (Exception ex) {
            return null;
        }
    }
}