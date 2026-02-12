
package com.fpmislata.banco.persistence.dao.impl.entity;

import com.fpmislata.banco.domain.enums.ApiClientStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "api_client")
public class ApiClientJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;

    @Column(name = "api_key_hash")
    private String apiKeyHash;

    @Enumerated(EnumType.STRING)
    private ApiClientStatus status;

    private LocalDateTime createdAt;

    public ApiClientJpaEntity() {
    }

    public ApiClientJpaEntity(Long id, String clientName, String apiKeyHash, ApiClientStatus status, LocalDateTime createdAt) {
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
