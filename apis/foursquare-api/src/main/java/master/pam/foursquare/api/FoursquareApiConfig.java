package master.pam.foursquare.api;

import master.pam.foursquare.api.impl.FoursquareSource;
import master.pam.foursquare.api.impl.IFoursquareSource;
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
