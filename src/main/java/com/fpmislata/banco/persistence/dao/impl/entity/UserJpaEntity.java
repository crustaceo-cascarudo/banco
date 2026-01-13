package com.fpmislata.banco.persistence.dao.impl.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class UserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname1;
    private String surname2;
    private String dni;
    @Column(name = "password")
    private String passwordHash;

    public UserJpaEntity() {
    }

    public UserJpaEntity(Long id, String name, String surname1, String surname2, String dni, String passwordHash) {
        this.id = id;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.dni = dni;
        this.passwordHash = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname1() {
        return surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public String getDni() {
        return dni;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
