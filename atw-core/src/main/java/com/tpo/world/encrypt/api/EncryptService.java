package com.tpo.world.encrypt.api;

public interface EncryptService {

    String encrpyt(String aMessage);

    String decrpyt(String aMessage);

    String hash(String aMessage);

}
