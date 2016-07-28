package master.pam.server;

import com.master.pam.encrypt.api.IEncryptApi;
import master.pam.crud.PersistenceConfig;
import master.pam.crud.api.dao.IMarkerDao;
import master.pam.crud.api.dao.IPasswordDao;
import master.pam.crud.api.dao.IUserDao;
import master.pam.server.api.server.IServer;
import master.pam.server.impl.request.RequestFactory;
import master.pam.server.impl.server.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Tiberiu
 * @since 28/07/16.
 */
@Configuration
@Import(PersistenceConfig.class)
public class ServerConfig {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IEncryptApi encryptApi;
    @Autowired
    private IMarkerDao markerDao;
    @Autowired
    private IPasswordDao passwordDao;

    @Bean
    public IServer server(RequestFactory requestFactory) {
        return new Server(requestFactory);
    }

    @Bean
    public RequestFactory requestFactory() {
        return new RequestFactory(userDao, encryptApi, markerDao, passwordDao);
    }
}
