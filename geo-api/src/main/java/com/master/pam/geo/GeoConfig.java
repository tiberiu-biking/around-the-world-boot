package com.master.pam.geo;

import com.master.pam.geo.coding.api.IGeoCodingAPI;
import com.master.pam.geo.coding.api.impl.GeoCodingAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tiberiu
 * @since 28/07/16.
 */
@Configuration
public class GeoConfig {

    @Bean
    public IGeoCodingAPI geoCodingApi(){
        return new GeoCodingAPI();
    }
}
