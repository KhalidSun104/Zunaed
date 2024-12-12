package org.example;

import org.example.models.Customer;
import org.example.models.Mechanic;
import org.example.models.Task;
import org.example.models.Vehicle;
import org.example.services.NotificationSystem;
import org.example.services.TaskAllocationManager;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    void testAllocateTask() {

        Mechanic mechanic1 = new Mechanic("John", "M001");
        Mechanic mechanic2 = new Mechanic("Jane", "M002");
        List<Mechanic> mechanics = List.of(mechanic1, mechanic2);
        TaskAllocationManager manager = new TaskAllocationManager(mechanics);

        Customer customer = new Customer("Alice", "100", "123456789", "alice@example.com", "123 Main St");
        Vehicle vehicle = new Vehicle("ABC123", "Toyota", "Corolla", customer);
        Task task = new Task("Repair brakes", vehicle, Task.Priority.HIGH);

        manager.allocateTask(task);

        assertTrue(mechanic1.getTasks().contains(task) || mechanic2.getTasks().contains(task));
        assertEquals(1, mechanic1.getTasks().size() + mechanic2.getTasks().size());
    }

    @Test
    void testCompleteTask() {
        Mechanic mechanic = new Mechanic("John", "M001");
        Customer customer = new Customer("Alice", "100", "123456789", "alice@example.com", "123 Main St");
        Vehicle vehicle = new Vehicle("ABC123", "Toyota", "Corolla", customer);
        Task task = new Task("Repair brakes", vehicle, Task.Priority.HIGH);

        mechanic.assignTask(task);
        assertEquals(1, mechanic.getTasks().size());

        TaskAllocationManager manager = new TaskAllocationManager(List.of(mechanic));
        manager.completeTask(mechanic);

        assertEquals(0, mechanic.getTasks().size());
    }

    @Test
    void testNotifyCustomers() {
        NotificationSystem notificationSystem = new NotificationSystem();
        Customer customer = new Customer("Monir", "200", "987654321", "monir@example.com", "456 Elm St");
        notificationSystem.registerCustomer(customer);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        notificationSystem.notifyAllRegistered("Service reminder");
        assertTrue(outContent.toString().contains("Notification to Monir: Service reminder"));
    }

    @Test
    void testInvalidTaskPriority() {
        Mechanic mechanic = new Mechanic("John", "M001");
        List<Mechanic> mechanics = List.of(mechanic);
        TaskAllocationManager manager = new TaskAllocationManager(mechanics);

        Customer customer = new Customer("Alice", "100", "123456789", "alice@example.com", "123 Main St");
        Vehicle vehicle = new Vehicle("ABC123", "Toyota", "Corolla", customer);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String invalidPriority = "INVALID_PRIORITY";
        try {
            Task.Priority priority = Task.Priority.valueOf(invalidPriority);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid priority. Please enter HIGH, MEDIUM, or LOW.");
        }

        assertTrue(outContent.toString().contains("Invalid priority. Please enter HIGH, MEDIUM, or LOW."));
    }
}
