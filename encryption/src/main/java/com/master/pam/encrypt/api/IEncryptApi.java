package com.master.pam.encrypt.api;

public interface IEncryptApi {

    public String encrpyt(String aMessage);

    public String decrpyt(String aMessage);

    public String hash(String aMessage);

}
