
package com.fpmislata.banco.domain.model;

import java.time.LocalDateTime;

import com.fpmislata.banco.domain.enums.ApiClientStatus;

public class ApiClient {
    private Long id;
    private String clientName;
    private String apiKeyHash;
    private ApiClientStatus status;
    private LocalDateTime createdAt;

    public ApiClient(String clientName, String apiKeyHash) {
        this.clientName = clientName;
        this.apiKeyHash = apiKeyHash;
        this.status = ApiClientStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    public ApiClient(Long id, String clientName, String apiKeyHash, ApiClientStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.clientName = clientName;
        this.apiKeyHash = apiKeyHash;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getApiKeyHash() {
        return apiKeyHash;
    }

    public void setApiKeyHash(String apiKeyHash) {
        this.apiKeyHash = apiKeyHash;
    }

    public ApiClientStatus getStatus() {
        return status;
    }

    public void setStatus(ApiClientStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
