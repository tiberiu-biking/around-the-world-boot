package com.tpo.world.persistence.repository;

import com.tpo.world.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tiberiu
 * @since 02/08/16.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByFoursquareId(String foursquareId);

    UserEntity findByEmailIgnoreCase(String username);
}
