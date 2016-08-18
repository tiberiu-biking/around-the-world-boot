package com.tpo.world.persistence;

import com.tpo.world.model.entity.IdEntity;
import com.tpo.world.persistence.repository.MarkerRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Tiberiu
 * @since 28/07/16.
 */
@Configuration
@EntityScan(basePackageClasses = IdEntity.class)
@EnableJpaRepositories(basePackageClasses = MarkerRepository.class)
@EnableTransactionManagement
public class PersistenceConfig {
}
