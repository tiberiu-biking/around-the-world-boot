package master.pam.crud;

import master.pam.crud.api.dao.IMarkerDao;
import master.pam.crud.api.dao.IPasswordDao;
import master.pam.crud.api.dao.IUserDao;
import master.pam.crud.impl.dao.MarkerDao;
import master.pam.crud.impl.dao.PasswordDao;
import master.pam.crud.impl.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tiberiu
 * @since 28/07/16.
 */
@Configuration
public class PersistenceConfig {

    @Bean
    public IMarkerDao markerDao() {
        return new MarkerDao();
    }

    @Bean
    public IUserDao userDao(IPasswordDao passwordDao) {
        return new UserDao(passwordDao);
    }

    @Bean
    public IPasswordDao passwordDao() {
        return new PasswordDao();
    }
}
