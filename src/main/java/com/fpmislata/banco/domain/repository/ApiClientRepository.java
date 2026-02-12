
package com.fpmislata.banco.domain.repository;

import com.fpmislata.banco.domain.repository.entity.ApiClientEntity;

import java.util.List;
import java.util.Optional;

public interface ApiClientRepository {
    Optional<ApiClientEntity> findByApiKey(String apiKey);
    ApiClientEntity save(ApiClientEntity apiClientEntity);
    List<ApiClientEntity> findAll();
}
