package com.pragma.food_cout.domain.model;

public class Restaurant {
    private final Long id;
    private final String name;
    private final Long taxId;
    private final String address;
    private final Long phone;
    private final String logoUrl;
    private final Long ownerId;

    public Restaurant(Long id, String name, Long taxId, String address, Long phone, String logoUrl, Long ownerId) {
        this.id = id;
        this.name = name;
        this.taxId = taxId;
        this.address = address;
        this.phone = phone;
        this.logoUrl = logoUrl;
        this.ownerId = ownerId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getTaxId() {
        return taxId;
    }

    public String getAddress() {
        return address;
    }

    public Long getPhone() {
        return phone;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public Long getOwnerId() {
        return ownerId;
    }
}