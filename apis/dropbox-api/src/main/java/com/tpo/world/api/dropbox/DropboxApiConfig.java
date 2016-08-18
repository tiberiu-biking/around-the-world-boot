package com.tpo.world.api.dropbox;

import com.tpo.world.api.dropbox.api.IDropboxSource;
import com.tpo.world.api.dropbox.api.impl.DropboxSource;
import com.tpo.world.geo.coding.api.IGeoCodingAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tiberiu
 * @since 28/07/16.
 */
@Configuration
public class DropboxApiConfig {

    @Bean
    public IDropboxSource dropboxSource(IGeoCodingAPI geoCodingApi){
        return new DropboxSource(geoCodingApi);
    }
}
