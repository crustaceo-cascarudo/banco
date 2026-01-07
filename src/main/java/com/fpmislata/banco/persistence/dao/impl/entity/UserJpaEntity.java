package com.fpmislata.banco.persistence.dao.impl.entity;

import com.fpmislata.banco.domain.enumerado.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class UserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "password")
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public UserJpaEntity() {
    }

    public UserJpaEntity(Long id, String name, String passwordHash, Role role) {
        this.id = id;
        this.name = name;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Role getRole() {
        return role;
    }
}
