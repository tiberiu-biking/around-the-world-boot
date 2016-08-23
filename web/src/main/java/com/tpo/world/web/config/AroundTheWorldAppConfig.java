package com.tpo.world.web.config;

import com.tpo.world.web.server.ServerConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Tiberiu
 * @since 28/07/16.
 */
@Configuration
@EnableAutoConfiguration
@Import(ServerConfig.class)
@ComponentScan(basePackages = "com.tpo.world.web.web.impl")
public class AroundTheWorldAppConfig {
}
