package controller.user.roomController.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenerateCodeToJoin {

    public static String generateCode(String passwordToHash) {
        String generatedPassword = null;

        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(passwordToHash.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // Convert the first 10 bytes to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < Math.min(10, bytes.length); i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Get complete hashed password (truncated to 10 characters) in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static boolean checkInvitedCode(String code, String passwordToHash) {
        String fakeCode = generateCode(code);
        String realCode = generateCode(passwordToHash);
        if (fakeCode.equals(realCode)) {
            return true;
        }
        return false;
    }
}
