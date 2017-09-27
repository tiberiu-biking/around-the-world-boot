package com.tpo.world.web;

import com.tpo.world.core.CoreConfig;
import com.tpo.world.core.encrypt.api.EncryptService;
import com.tpo.world.persistence.PersistenceConfig;
import com.tpo.world.persistence.repository.MarkerRepository;
import com.tpo.world.persistence.repository.PasswordRepository;
import com.tpo.world.persistence.repository.UserRepository;
import com.tpo.world.web.api.server.IServer;
import com.tpo.world.web.config.ApisConfig;
import com.tpo.world.web.impl.request.RequestFactory;
import com.tpo.world.web.impl.server.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Tiberiu
 * @since 28/07/16.
 */
@Configuration
@Import({PersistenceConfig.class, CoreConfig.class, ApisConfig.class})
public class ServerConfig {

    @Bean
    public IServer server(RequestFactory requestFactory) {
        return new Server(requestFactory);
    }

    @Bean
    public RequestFactory requestFactory(MarkerRepository markerRepository, UserRepository userRepository, PasswordRepository passwordRepository, EncryptService encryptApi) {
        return new RequestFactory(userRepository, passwordRepository, markerRepository, encryptApi);
    }
}
