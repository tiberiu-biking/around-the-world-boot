package master.pam.server.config;

import com.master.pam.encrypt.EncryptionApiConfig;
import com.master.pam.geo.GeoApiConfig;
import com.tpo.world.api.dropbox.DropboxApiConfig;
import master.pam.foursquare.api.FoursquareApiConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Tiberiu
 * @since 28/07/16.
 */
@Configuration
@Import({GeoApiConfig.class, FoursquareApiConfig.class, DropboxApiConfig.class, EncryptionApiConfig.class})
public class ApisConfig {

}
