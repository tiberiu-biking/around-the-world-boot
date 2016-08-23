package com.tpo.world.persistence.repository;

import com.tpo.world.model.entity.PasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tiberiu
 * @since 02/08/16.
 */
@Repository
public interface PasswordRepository extends JpaRepository<PasswordEntity, Long> {

    PasswordEntity findByUserId(long id);

}
