package com.mycompany.hotelreservationsystem.models;

import java.util.Objects;

public class Guest {
    private String name;
    private String document;
    private String contact;
    private String address;

    // Constructors
    public Guest() {
    }
    public Guest(String name, String document, String contact, String address) {
        this.name = name;
        this.document = document;
        this.contact = contact;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(document, guest.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(document);
    }

    @Override
    public String toString() {
        return name + " (Documento: " + document + ")";
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    public String getDocument() {
        return document;
    }
    public String getContact() {
        return contact;
    }
    public String getAddress() {
        return address;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public void setDocument(String document) {
        this.document = document;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
