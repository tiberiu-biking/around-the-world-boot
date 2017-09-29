package com.tpo.world.services.encrypt;

public interface EncryptService {

    String encrpyt(String aMessage);

    String decrpyt(String aMessage);

    String hash(String aMessage);

}
