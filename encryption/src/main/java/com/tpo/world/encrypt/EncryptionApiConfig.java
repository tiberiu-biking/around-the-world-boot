package com.tpo.world.encrypt;

import com.tpo.world.encrypt.api.EncryptApi;
import com.tpo.world.encrypt.api.IEncryptApi;
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
