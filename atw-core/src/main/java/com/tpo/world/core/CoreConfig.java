package com.tpo.world.core;

import com.tpo.world.core.encrypt.api.AESEncryptService;
import com.tpo.world.core.encrypt.api.EncryptService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tiberiu
 * @since 28/07/16.
 */
@Configuration
public class CoreConfig {

    @Bean
    public EncryptService encryptService() {
        return new AESEncryptService();
    }

}
