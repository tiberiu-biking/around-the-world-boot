package com.tpo.world;

import master.pam.server.ServerConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Tiberiu
 * @since 28/07/16.
 */
@Configuration
@EnableAutoConfiguration
@Import(ServerConfig.class)
public class AroundTheWorldAppConfig {
}
