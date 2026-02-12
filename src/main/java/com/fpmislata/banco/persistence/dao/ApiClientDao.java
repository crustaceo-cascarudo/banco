
package com.fpmislata.banco.persistence.dao;

import com.fpmislata.banco.persistence.dao.impl.entity.ApiClientJpaEntity;

import java.util.List;
import java.util.Optional;

public interface ApiClientDao extends GenericDao<ApiClientJpaEntity> {
    Optional<ApiClientJpaEntity> findByApiKeyHash(String apiKeyHash);
    Optional<ApiClientJpaEntity> findById(Long id);
    List<ApiClientJpaEntity> findAll();
}
