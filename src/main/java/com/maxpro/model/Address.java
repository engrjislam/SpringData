package com.maxpro.model;

import javax.persistence.*;


@Entity
public class Address {

    private Long id;
    private String street;
    private String city;
    private String country;
    private String postalCode;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString () {
        return String.format("address[id=%d, street=%s, city=%s, country=%s, postalCode=%s]",
                id, street, city, country, postalCode);
    }

}
