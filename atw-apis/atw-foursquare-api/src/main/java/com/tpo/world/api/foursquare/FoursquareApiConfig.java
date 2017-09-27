package com.tpo.world.api.foursquare;

import com.tpo.world.api.foursquare.impl.FoursquareSource;
import com.tpo.world.api.foursquare.impl.IFoursquareSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tiberiu
 * @since 28/07/16.
 */
@Configuration
public class FoursquareApiConfig {

    @Bean
    public IFoursquareSource foursquareApi(){
        return new FoursquareSource();
    }
}
