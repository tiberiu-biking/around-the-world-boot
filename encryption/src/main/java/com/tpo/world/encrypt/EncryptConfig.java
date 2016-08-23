package com.tpo.world.encrypt;

import com.tpo.world.encrypt.api.DefaultEncryptor;
import com.tpo.world.encrypt.api.Encryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tiberiu
 * @since 28/07/16.
 */
@Configuration
public class EncryptConfig {

    @Bean
    public Encryptor encryptApi() {
        return new DefaultEncryptor();
    }
}
