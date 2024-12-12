package org.example.models;


import org.example.services.NotificationSystem;

public class Manager extends User {
    private NotificationSystem notificationSystem;

    public Manager(String name, String id, NotificationSystem notificationSystem) {
        super(name, id);
        this.notificationSystem = notificationSystem;
    }

    public void allocateTask(Task task, Mechanic mechanic) {
        mechanic.assignTask(task);
        System.out.println("Task allocated to mechanic: " + mechanic.getName());
    }

    public void notifyCustomers(String message) {
        notificationSystem.notifyAllRegistered(message);
    }

    @Override
    public void performRole() {
        System.out.println("Manager role: Allocate tasks, manage notifications, and more.");
    }
}
