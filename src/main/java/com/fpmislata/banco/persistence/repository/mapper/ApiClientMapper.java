
package com.fpmislata.banco.persistence.repository.mapper;

import com.fpmislata.banco.domain.repository.entity.ApiClientEntity;
import com.fpmislata.banco.persistence.dao.impl.entity.ApiClientJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ApiClientMapper {

    private static ApiClientMapper instance;

    private ApiClientMapper() {
    }

    public static ApiClientMapper getInstance() {
        if (instance == null) {
            instance = new ApiClientMapper();
        }
        return instance;
    }

    public ApiClientJpaEntity fromApiClientEntityToJpaEntity(ApiClientEntity apiClientEntity) {
        if (apiClientEntity == null) {
            return null;
        }
        return new ApiClientJpaEntity(
                apiClientEntity.id(),
                apiClientEntity.clientName(),
                apiClientEntity.apiKeyHash(),
                apiClientEntity.status(),
                apiClientEntity.createdAt()
        );
    }

    public ApiClientEntity fromApiClientJpaEntityToApiClientEntity(ApiClientJpaEntity apiClientJpaEntity) {
        if (apiClientJpaEntity == null) {
            return null;
        }
        return new ApiClientEntity(
                apiClientJpaEntity.getId(),
                apiClientJpaEntity.getClientName(),
                apiClientJpaEntity.getApiKeyHash(),
                apiClientJpaEntity.getStatus(),
                apiClientJpaEntity.getCreatedAt()
        );
    }
}
