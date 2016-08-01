package master.pam.crud;

import master.pam.crud.api.dao.IMarkerDao;
import master.pam.crud.api.dao.IPasswordDao;
import master.pam.crud.api.dao.IUserDao;
import master.pam.crud.impl.CrudImpl;
import master.pam.crud.impl.dao.MarkerDao;
import master.pam.crud.impl.dao.PasswordDao;
import master.pam.crud.impl.dao.UserDao;
import master.pam.crud.impl.iface.ICrud;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;

/**
 * @author Tiberiu
 * @since 28/07/16.
 */
@Configuration
@EntityScan(basePackages = "master.pam.crud.impl.entity")
@EnableTransactionManagement
public class PersistenceConfig {

    @Bean
    public ICrud crud(EntityManager entityManager) {
        return new CrudImpl(entityManager);
    }

    @Bean
    public IMarkerDao markerDao(ICrud crud) {
        return new MarkerDao(crud);
    }

    @Bean
    public IUserDao userDao(ICrud crud, IPasswordDao passwordDao) {
        return new UserDao(crud, passwordDao);
    }

    @Bean
    public IPasswordDao passwordDao(ICrud crud) {
        return new PasswordDao(crud);
    }
}
