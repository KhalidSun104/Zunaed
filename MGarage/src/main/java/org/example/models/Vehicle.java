package org.example.models;

public class Vehicle {
    private String registrationNumber;
    private String make;
    private String model;
    private Customer owner;

    public Vehicle(String registrationNumber, String make, String model, Customer owner) {
        this.registrationNumber = registrationNumber;
        this.make = make;
        this.model = model;
        this.owner = owner;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Customer getOwner() {
        return owner;
    }
}
