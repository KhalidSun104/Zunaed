package org.example.services;

import org.example.models.Mechanic;
import org.example.models.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskAllocationManager {
    private List<Mechanic> mechanics;
    private Map<Mechanic, Integer> taskCountMap;

    public TaskAllocationManager(List<Mechanic> mechanics) {
        this.mechanics = mechanics;
        this.taskCountMap = new HashMap<>();

        for (Mechanic mechanic : mechanics) {
            taskCountMap.put(mechanic, 0);
        }
    }

    public void allocateTask(Task task) {
        if (mechanics.isEmpty()) {
            System.out.println("No mechanics available to allocate the task.");
            return;
        }

        for (Mechanic mechanic : mechanics) {
            taskCountMap.putIfAbsent(mechanic, 0);
        }

        Mechanic leastBusyMechanic = mechanics.get(0);
        for (Mechanic mechanic : mechanics) {
            if (taskCountMap.get(mechanic) < taskCountMap.get(leastBusyMechanic)) {
                leastBusyMechanic = mechanic;
            }
        }

        leastBusyMechanic.assignTask(task);
        taskCountMap.put(leastBusyMechanic, taskCountMap.get(leastBusyMechanic) + 1);

        System.out.println("Task allocated to mechanic: " + leastBusyMechanic.getName());
    }


    public void completeTask(Mechanic mechanic) {
        if (!mechanics.contains(mechanic)) {
            System.out.println("Mechanic not found in the system.");
            return;
        }

        mechanic.completeTask();

        taskCountMap.put(mechanic, Math.max(0, taskCountMap.get(mechanic) - 1));
    }

    public void printTaskAssignments() {
        System.out.println("Task Assignments:");
        for (Mechanic mechanic : mechanics) {
            //System.out.println("Mechanic: " + mechanic.getName() + " | Tasks: " + taskCountMap.get(mechanic));
            System.out.println("Mechanic: " + mechanic.getName() + " | Tasks: " + mechanic.getTaskCount());
        }
    }
}
