package com.tpo.world.web.config;

import com.tpo.world.api.dropbox.DropboxApiConfig;
import com.tpo.world.api.geo.GeoApiConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Tiberiu
 * @since 28/07/16.
 */
@Configuration
@Import({GeoApiConfig.class, DropboxApiConfig.class})
public class ApisConfig {

}
