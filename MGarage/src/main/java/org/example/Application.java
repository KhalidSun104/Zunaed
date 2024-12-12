package org.example;

import org.example.models.*;
import org.example.services.NotificationSystem;
import org.example.services.TaskAllocationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    private static final List<Mechanic> mechanics = new ArrayList<>();
    private static final List<Vehicle> vehicles = new ArrayList<>();
    private static final NotificationSystem notificationSystem = new NotificationSystem();
    private static final TaskAllocationManager taskAllocationManager = new TaskAllocationManager(mechanics);

    public static void main(String[] args) {
        initializeSampleData();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Bay Motors System ===");
            System.out.println("1. Manager Login");
            System.out.println("2. Mechanic Login");
            System.out.println("3. Exit");
            System.out.print("Select user role: ");

            int userRoleChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userRoleChoice) {
                case 1 -> managerMenu(scanner);
                case 2 -> mechanicMenu(scanner);
                case 3 -> {
                    running = false;
                    System.out.println("Exiting system...");
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }

    private static void managerMenu(Scanner scanner) {
        boolean managerRunning = true;

        while (managerRunning) {
            System.out.println("\n=== Manager Menu ===");
            System.out.println("1. Log Vehicle");
            System.out.println("2. Allocate Task");
            System.out.println("3. Notify Customers");
            System.out.println("4. Print Task Assignments");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> logVehicle(scanner);
                case 2 -> allocateTask(scanner);
                case 3 -> notifyCustomers(scanner);
                case 4 -> taskAllocationManager.printTaskAssignments();
                case 5 -> managerRunning = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void mechanicMenu(Scanner scanner) {
        System.out.print("Enter mechanic name: ");
        String mechanicName = scanner.nextLine();

        Mechanic mechanic = mechanics.stream()
                .filter(m -> m.getName().equalsIgnoreCase(mechanicName))
                .findFirst()
                .orElse(null);

        if (mechanic == null) {
            System.out.println("Mechanic not found.");
            return;
        }

        boolean mechanicRunning = true;

        while (mechanicRunning) {
            System.out.println("\n=== Mechanic Menu ===");
            System.out.println("1. View Tasks");
            System.out.println("2. Complete Task");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> mechanic.viewTasks();
                case 2 -> taskAllocationManager.completeTask(mechanic);
                case 3 -> mechanicRunning = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void initializeSampleData() {
        Mechanic mechanic1 = new Mechanic("John", "M001");
        Mechanic mechanic2 = new Mechanic("Jane", "M002");
        mechanics.add(mechanic1);
        mechanics.add(mechanic2);

        Customer customer1 = new Customer("Alice", "100", "123456789", "alice@example.com", "123 Main St");
        Vehicle vehicle1 = new Vehicle("ABC123", "Toyota", "Corolla", customer1);
        vehicles.add(vehicle1);

        notificationSystem.registerCustomer(customer1);
    }

    private static void logVehicle(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter vehicle registration number: ");
        String registration = scanner.nextLine();
        System.out.print("Enter vehicle make: ");
        String make = scanner.nextLine();
        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine();

        Customer owner = new Customer(name, "C" + (vehicles.size() + 1), "N/A", "N/A", "N/A");
        Vehicle vehicle = new Vehicle(registration, make, model, owner);
        vehicles.add(vehicle);

        System.out.println("Vehicle logged successfully: " + vehicle.getRegistrationNumber());
    }

    private static void allocateTask(Scanner scanner) {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vehicle registration number: ");
        String registration = scanner.nextLine();

        Vehicle vehicle = vehicles.stream()
                .filter(v -> v.getRegistrationNumber().equalsIgnoreCase(registration))
                .findFirst()
                .orElse(null);

        if (vehicle == null) {
            System.out.println("Vehicle not found. Please log the vehicle first.");
            return;
        }

        System.out.print("Enter task priority (HIGH, MEDIUM, LOW): ");
        String priorityInput = scanner.nextLine().toUpperCase();

        Task.Priority priority;
        try {
            priority = Task.Priority.valueOf(priorityInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid priority. Defaulting to MEDIUM.");
            priority = Task.Priority.MEDIUM;
        }

        Task task = new Task(description, vehicle, priority);
        taskAllocationManager.allocateTask(task);
    }

    private static void notifyCustomers(Scanner scanner) {
        System.out.print("Enter notification message: ");
        String message = scanner.nextLine();
        notificationSystem.notifyAllRegistered(message);
        System.out.println("Notification sent to all registered customers.");
    }
}
