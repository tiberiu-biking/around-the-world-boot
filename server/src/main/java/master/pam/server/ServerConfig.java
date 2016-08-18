package master.pam.server;

import com.master.pam.encrypt.api.IEncryptApi;
import com.tpo.world.persistence.PersistenceConfig;
import com.tpo.world.persistence.repository.MarkerRepository;
import com.tpo.world.persistence.repository.PasswordRepository;
import com.tpo.world.persistence.repository.UserRepository;
import master.pam.server.api.server.IServer;
import master.pam.server.config.ApisConfig;
import master.pam.server.impl.request.RequestFactory;
import master.pam.server.impl.server.Server;
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
    public RequestFactory requestFactory(MarkerRepository markerRepository, UserRepository userRepository, PasswordRepository passwordRepository, IEncryptApi encryptApi) {
        return new RequestFactory(userRepository, passwordRepository, markerRepository, encryptApi);
    }
}
