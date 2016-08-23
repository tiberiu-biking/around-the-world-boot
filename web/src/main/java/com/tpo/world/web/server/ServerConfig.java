package com.tpo.world.web.server;

import com.tpo.world.encrypt.api.Encryptor;
import com.tpo.world.persistence.PersistenceConfig;
import com.tpo.world.persistence.repository.MarkerRepository;
import com.tpo.world.persistence.repository.PasswordRepository;
import com.tpo.world.persistence.repository.UserRepository;
import com.tpo.world.web.server.api.server.IServer;
import com.tpo.world.web.server.config.ApisConfig;
import com.tpo.world.web.server.impl.request.RequestFactory;
import com.tpo.world.web.server.impl.server.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Tiberiu
 * @since 28/07/16.
 */
@Configuration
@Import({PersistenceConfig.class, ApisConfig.class})
public class ServerConfig {

    @Bean
    public IServer server(RequestFactory requestFactory) {
        return new Server(requestFactory);
    }

    @Bean
    public RequestFactory requestFactory(MarkerRepository markerRepository, UserRepository userRepository, PasswordRepository passwordRepository, Encryptor encryptorApi) {
        return new RequestFactory(userRepository, passwordRepository, markerRepository, encryptorApi);
    }
}
