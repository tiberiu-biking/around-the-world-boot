package com.master.pam.encrypt;

import com.master.pam.encrypt.api.EncryptApi;
import com.master.pam.encrypt.api.IEncryptApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tiberiu
 * @since 28/07/16.
 */
@Configuration
public class EncryptionApiConfig {

    @Bean
    public IEncryptApi encryptApi() {
        return new EncryptApi();
    }
}
