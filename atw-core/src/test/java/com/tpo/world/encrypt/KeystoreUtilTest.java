package com.tpo.world.encrypt;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class KeystoreUtilTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void getKeystoreResource() {

//        Key key = KeystoreUtil.getKeyFromKeyStore("src/test/resources/aes-keystore.jck", "mystorepass", "jceksaes", "mykeypass");

        //      assertThat(key, is(notNullValue()));
    }

    @Test
    public void getKeystoreResourceMissingKeystore() {

        //    exception.expect(RuntimeException.class);

        //  KeystoreUtil.getKeyFromKeyStore("notFound.jck", "mystorepass", "jceksaes", "mykeypass");
    }

}
