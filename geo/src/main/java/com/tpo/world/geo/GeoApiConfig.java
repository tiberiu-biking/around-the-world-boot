package com.tpo.world.geo;

import com.tpo.world.geo.coding.api.IGeoCodingAPI;
import com.tpo.world.geo.coding.api.impl.GeoCodingAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tiberiu
 * @since 28/07/16.
 */
@Configuration
public class GeoApiConfig {

    @Bean
    public IGeoCodingAPI geoCodingApi(){
        return new GeoCodingAPI();
    }
}
