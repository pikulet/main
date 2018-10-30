package seedu.address.logic.parser;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A utility class for hashing passwords.
 * The code is taken from https://www.baeldung.com/sha-256-hashing-java.
 */
public class PasswordHashUtil {

    private static final String SHA_256_ALGORITHM = "SHA-256";

    public static String hash(String pw) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(SHA_256_ALGORITHM);
        byte[] encodedHash =
                digest.digest(pw.getBytes(StandardCharsets.UTF_8));

        return bytesToHex(encodedHash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
        String hex = Integer.toHexString(0xff & hash[i]);
        if(hex.length() == 1) hexString.append('0');
        hexString.append(hex);
        }
        return hexString.toString();
    }
}
