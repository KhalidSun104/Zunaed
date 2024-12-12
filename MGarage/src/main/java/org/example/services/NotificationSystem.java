package org.example.services;


import org.example.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class NotificationSystem {
    private List<Customer> registeredCustomers = new ArrayList<>();

    public void registerCustomer(Customer customer) {
        registeredCustomers.add(customer);
    }

    public void notifyAllRegistered(String message) {
        for (Customer customer : registeredCustomers) {
            System.out.println("Notification to " + customer.getName() + ": " + message);
        }
    }
}
