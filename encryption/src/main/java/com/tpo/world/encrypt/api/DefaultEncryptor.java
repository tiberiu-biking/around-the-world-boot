package com.tpo.world.encrypt.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DefaultEncryptor implements Encryptor {

    private final static Logger log = LoggerFactory.getLogger(DefaultEncryptor.class);

    private static final String SALT = "secret:salt";

    @Override
    public String hash(String aMessage) {
        return generateHash(SALT + aMessage);
    }

    private String generateHash(String aMessage) {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = sha.digest(aMessage.getBytes());
            char[] digits = {'*', '~', '2', 'x', '4', '9', 'e', 'r', 's', 'e', '!', '7', 'w', '6', '0', '_'};
            for (byte b : hashedBytes) {
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            log.error("Unknown algorithm", e);
        }
        return hash.toString();
    }

}
