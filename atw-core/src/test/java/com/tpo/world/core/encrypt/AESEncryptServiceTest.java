package com.tpo.world.core.encrypt;

import com.tpo.world.core.encrypt.util.hash.HashUtil;
import org.junit.Test;

public class AESEncryptServiceTest {

    @Test
    public void encryptMessageTest() {
        // System.out.println( "Password decypted = " + AESEncryptService.API.encrpyt(
        // "password" ) );
    }

    @Test
    public void decryptMessageTest() {
        // System.out.println( "Password decrypted = " + AESEncryptService.API.decrpyt(
        // "H0lxmJwCXVmSRxFgW71CWw==" ) );
    }

    @Test
    public void hashTibisPassword() {
        System.out.println("Tibi's password hashed = " + HashUtil.getHash("x"));
    }

    @Test
    public void hashAlesPassword() {
        System.out.println("Ale's password hashed = " + HashUtil.getHash("a"));
    }

}
