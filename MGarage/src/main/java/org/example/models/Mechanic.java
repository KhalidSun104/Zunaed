package org.example.models;

import java.util.Collection;
import java.util.PriorityQueue;

public class Mechanic extends User {
    private PriorityQueue<Task> taskQueue;

    public Mechanic(String name, String id) {
        super(name, id);
        this.taskQueue = new PriorityQueue<>((a, b) -> a.getPriority().compareTo(b.getPriority()));
    }

    public void assignTask(Task task) {
        taskQueue.offer(task);
        System.out.println("Task assigned to " + getName() + ": " + task.getDescription());
    }

    public void completeTask() {
        Task task = taskQueue.poll();
        if (task != null) {
            System.out.println("Task completed by " + getName() + ": " + task.getDescription());
        } else {
            System.out.println("No tasks to complete.");
        }
    }

    public void viewTasks() {
        if (taskQueue.isEmpty()) {
            System.out.println(getName() + " has no tasks assigned.");
        } else {
            System.out.println(getName() + "'s Task Queue:");
            for (Task task : taskQueue) {
                System.out.println("- " + task.getDescription() + " | Priority: " + task.getPriority());
            }
        }
    }

    @Override
    public void performRole() {
        System.out.println("Mechanic role: View and complete tasks.");
    }

    public int getTaskCount() {
        return taskQueue.size();
    }

    public Collection<Task> getTasks() {
        return taskQueue;
    }
}
