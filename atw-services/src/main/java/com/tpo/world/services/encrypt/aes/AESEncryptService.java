package com.tpo.world.services.encrypt.aes;

import com.tpo.world.services.encrypt.EncryptService;
import com.tpo.world.services.encrypt.util.encrypt.AESCipher;
import com.tpo.world.services.encrypt.util.encrypt.KeystoreUtil;
import com.tpo.world.services.encrypt.util.hash.HashUtil;

import java.security.Key;

public class AESEncryptService implements EncryptService {

    private static final String KEYSTORE_LOCATION = "src/main/resources/keystore/aes-keystore.jck";
    private static final String STORE_PASS = "mystorepass";
    private static final String ALIAS = "jceksaes";
    private static final String KEY_PASS = "mykeypass";
    private static final String INITIAL_V = "4000297891200071";
    private Key keyFromKeyStore;

    @Override
    public String encrpyt(String aMessage) {
        AESCipher cipher = new AESCipher(getKeyFromKeyStore(), INITIAL_V.getBytes());
        return cipher.getEncryptedMessage(aMessage);
    }

    @Override
    public String decrpyt(String aMessage) {
        AESCipher cipher = new AESCipher(getKeyFromKeyStore(), INITIAL_V.getBytes());
        return cipher.getDecryptedMessage(aMessage);
    }

    @Override
    public String hash(String aMessage) {
        return HashUtil.getHash(aMessage);
    }

    private Key getKeyFromKeyStore() {
        if (keyFromKeyStore == null)
            keyFromKeyStore = KeystoreUtil.getKeyFromKeyStore(KEYSTORE_LOCATION, STORE_PASS, ALIAS, KEY_PASS);
        return keyFromKeyStore;
    }

}
