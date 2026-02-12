
package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.domain.model.ApiClient;

import java.util.Optional;

public interface ApiKeyService {
    String generateApiKey(String clientName);
    Optional<ApiClient> validateApiKey(String apiKey);
}
