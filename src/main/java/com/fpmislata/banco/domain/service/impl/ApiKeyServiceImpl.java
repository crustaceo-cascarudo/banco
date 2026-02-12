
package com.fpmislata.banco.domain.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.fpmislata.banco.domain.enums.ApiClientStatus;
import com.fpmislata.banco.domain.model.ApiClient;
import com.fpmislata.banco.domain.repository.ApiClientRepository;
import com.fpmislata.banco.domain.repository.entity.ApiClientEntity;
import com.fpmislata.banco.domain.service.ApiKeyService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiClientRepository apiClientRepository;

    public ApiKeyServiceImpl(ApiClientRepository apiClientRepository) {
        this.apiClientRepository = apiClientRepository;
    }

    @Override
    public String generateApiKey(String clientName) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] apiKeyBytes = new byte[32];
        secureRandom.nextBytes(apiKeyBytes);
        String apiKey = Base64.getUrlEncoder().withoutPadding().encodeToString(apiKeyBytes);

        String apiKeyHash = BCrypt.withDefaults().hashToString(12, apiKey.toCharArray());

        ApiClientEntity apiClientEntity = new ApiClientEntity(
                null,
                clientName,
                apiKeyHash,
                ApiClientStatus.ACTIVE,
                LocalDateTime.now()
        );

        apiClientRepository.save(apiClientEntity);

        return apiKey;
    }

    @Override
    public Optional<ApiClient> validateApiKey(String apiKey) {
        if (apiKey == null || apiKey.isBlank()) {
            return Optional.empty();
        }

        List<ApiClientEntity> allClients = apiClientRepository.findAll();

        for (ApiClientEntity entity : allClients) {
            if (entity.status() != ApiClientStatus.ACTIVE) {
                continue;
            }

            BCrypt.Result result = BCrypt.verifyer().verify(apiKey.toCharArray(), entity.apiKeyHash());
            if (result.verified) {
                return Optional.of(new ApiClient(
                        entity.id(),
                        entity.clientName(),
                        entity.apiKeyHash(),
                        entity.status(),
                        entity.createdAt()
                ));
            }
        }

        return Optional.empty();
    }
}
