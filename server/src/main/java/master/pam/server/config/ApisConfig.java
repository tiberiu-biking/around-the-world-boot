package master.pam.server.config;

import com.master.pam.encrypt.EncryptConfig;
import com.tpo.world.api.dropbox.DropboxApiConfig;
import com.tpo.world.geo.GeoApiConfig;
import master.pam.foursquare.api.FoursquareApiConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Tiberiu
 * @since 28/07/16.
 */
@Configuration
@Import({GeoApiConfig.class, FoursquareApiConfig.class, DropboxApiConfig.class, EncryptConfig.class})
public class ApisConfig {

}
