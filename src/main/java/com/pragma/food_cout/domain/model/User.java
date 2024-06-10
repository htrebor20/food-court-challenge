package com.pragma.food_cout.domain.model;

import java.time.LocalDate;

public class User {
    private Long id;
    private String name;
    private String lastName;
    private Long document;
    private Long cellphone;
    private String email;
    private LocalDate birthdate;
    private Role role;

    public User(Long id, String name, String lastName, Long document, Long cellphone, String email, LocalDate birthdate, Role role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.cellphone = cellphone;
        this.email = email;
        this.birthdate = birthdate;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getDocument() {
        return document;
    }

    public Long getCellphone() {
        return cellphone;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Role getRole() {
        return role;
    }
}
