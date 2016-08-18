package com.tpo.world.persistence.repository;

import com.tpo.world.model.entity.MarkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tiberiu
 * @since 02/08/16.
 */
@Repository
public interface MarkerRepository extends JpaRepository<MarkerEntity, Long> {

    List<MarkerEntity> findByUserId(Long userId);

    List<MarkerEntity> findByUserIdOrderByDateDesc(Long userId);

    List<MarkerEntity> findByIdAndUserId(Long id, Long userId);

    List<MarkerEntity> findByUserIdAndExternalId(Long userId, String externalId);
}
