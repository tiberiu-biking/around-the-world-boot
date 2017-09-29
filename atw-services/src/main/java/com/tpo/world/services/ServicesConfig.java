package com.tpo.world.services;

import com.tpo.world.services.encrypt.EncryptService;
import com.tpo.world.services.encrypt.aes.AESEncryptService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tiberiu
 * @since 28/07/16.
 */
@Configuration
public class ServicesConfig {

    @Bean
    public EncryptService encryptService() {
        return new AESEncryptService();
    }

}
