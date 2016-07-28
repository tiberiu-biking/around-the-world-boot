package com.master.pam.dropbox;

import com.master.pam.dropbox.api.IDropboxSource;
import com.master.pam.dropbox.api.impl.DropboxSource;
import com.master.pam.geo.coding.api.IGeoCodingAPI;
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
