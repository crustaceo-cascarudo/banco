package com.fpmislata.banco.domain.model;

public class User {
    private Long id;
    private String name;
    private String surname1;
    private String surname2;
    private String dni;
    private String passwordHash;

    public User() {
    }

    public User(Long id, String name, String surname1, String surname2, String dni, String passwordHash) {
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

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

}

