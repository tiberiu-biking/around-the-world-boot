package com.tpo.world.encrypt.api;

public interface IEncryptApi {

    String encrpyt(String aMessage);

    String decrpyt(String aMessage);

    String hash(String aMessage);

}
