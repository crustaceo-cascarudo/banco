
package com.fpmislata.banco.persistence.repository.impl;

import com.fpmislata.banco.domain.repository.ApiClientRepository;
import com.fpmislata.banco.domain.repository.entity.ApiClientEntity;
import com.fpmislata.banco.persistence.dao.ApiClientDao;
import com.fpmislata.banco.persistence.dao.impl.entity.ApiClientJpaEntity;
import com.fpmislata.banco.persistence.repository.mapper.ApiClientMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ApiClientRepositoryImpl implements ApiClientRepository {

    private final ApiClientDao apiClientDao;
    private final ApiClientMapper apiClientMapper;

    public ApiClientRepositoryImpl(ApiClientDao apiClientDao, ApiClientMapper apiClientMapper) {
        this.apiClientDao = apiClientDao;
        this.apiClientMapper = apiClientMapper;
    }

    @Override
    public Optional<ApiClientEntity> findByApiKey(String apiKeyHash) {
        Optional<ApiClientJpaEntity> jpaEntity = apiClientDao.findByApiKeyHash(apiKeyHash);
        return jpaEntity.map(apiClientMapper::fromApiClientJpaEntityToApiClientEntity);
    }

    @Override
    public ApiClientEntity save(ApiClientEntity apiClientEntity) {
        ApiClientJpaEntity jpaEntity = apiClientMapper.fromApiClientEntityToJpaEntity(apiClientEntity);
        if (apiClientDao.findById(jpaEntity.getId()).isPresent()) {
            return apiClientMapper.fromApiClientJpaEntityToApiClientEntity(apiClientDao.update(jpaEntity));
        }
        return apiClientMapper.fromApiClientJpaEntityToApiClientEntity(apiClientDao.insert(jpaEntity));
    }

    @Override
    public List<ApiClientEntity> findAll() {
        return apiClientDao.findAll().stream()
                .map(apiClientMapper::fromApiClientJpaEntityToApiClientEntity)
                .collect(Collectors.toList());
    }
}
