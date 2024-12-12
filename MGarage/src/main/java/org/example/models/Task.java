package org.example.models;

public class Task {
    private String description;
    private Vehicle vehicle;
    private Priority priority;

    public enum Priority {
        HIGH, MEDIUM, LOW
    }

    public Task(String description, Vehicle vehicle, Priority priority) {
        this.description = description;
        this.vehicle = vehicle;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Priority getPriority() {
        return priority;
    }
}
