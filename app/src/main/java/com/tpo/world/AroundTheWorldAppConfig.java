package com.tpo.world;

import com.tpo.world.web.ServerConfig;
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
@ComponentScan(basePackages = "master.pam.world.servlet.impl")
public class AroundTheWorldAppConfig {
}
