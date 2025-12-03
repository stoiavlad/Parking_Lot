package org.parking.parkinglot.ejb;

import jakarta.ejb.Stateless;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Stateless
public class PasswordBean {

    public String convertToSha256(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());

            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();

            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
}
