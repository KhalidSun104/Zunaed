package org.example.models;

public class Customer extends User {
    private String contactNumber;
    private String email;
    private String address;

    public Customer(String name, String id, String contactNumber, String email, String address) {
        super(name, id);
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void performRole() {
        System.out.println("Customer role: Interact with the service system.");
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + getName() + '\'' +
                ", id='" + getId() + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
