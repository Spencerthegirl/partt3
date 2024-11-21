package st104445833part3;

import javax.swing.*;

public class TaskManager {

   
    private static final int MAX_TASKS = 100;
    private String[] taskName = new String[MAX_TASKS];
    private String[] taskDescription = new String[MAX_TASKS];
    private String[] developerDetails = new String[MAX_TASKS];
    private String[] taskID = new String[MAX_TASKS];
    private int[] taskDuration = new int[MAX_TASKS];
    private String[] taskStatus = new String[MAX_TASKS];
    private int taskCount = 0; // Initialize taskCount to 0

    // Constructor initializes arrays to a specified size
    public TaskManager(int sizeOfArray) {
        this.taskCount = sizeOfArray;
        taskName = new String[sizeOfArray];
        taskDescription = new String[sizeOfArray];
        developerDetails = new String[sizeOfArray];
        taskID = new String[sizeOfArray];
        taskDuration = new int[sizeOfArray];
        taskStatus = new String[sizeOfArray];
    }

    // Method to check if the task description is valid (max 50 characters)
    public boolean checkDescription(String description) {
        if (description.length() <= 50) {
            JOptionPane.showMessageDialog(null, "Task description is successfully captured");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Task description should not exceed 50 characters", "Easy Kanban", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Method to create and return the Task ID
    public String createTaskID(String taskName, int taskNumber, String developerDetails) {
        String Name = taskName.length() >= 2 ? taskName.substring(0, 2).toUpperCase() : taskName.toUpperCase();
        String developer = developerDetails.length() >= 3 ? developerDetails.substring(developerDetails.length() - 3).toUpperCase() : developerDetails.toUpperCase();
        return Name + ":" + taskNumber + ":" + developer;
    }

    // Method to print task details
    public void printTaskDetails(String taskStatus, String developerDetails, int taskNumber, String taskName, String taskDescription, String taskID, int taskDuration) {
        String details = "\t\t Task Details \n"
                + "Task Status: " + taskStatus + "\n"
                + "Developer Details: " + developerDetails + "\n"
                + "Task Number: " + taskNumber + "\n"
                + "Task Name: " + taskName + "\n"
                + "Task Description: " + taskDescription + "\n"
                + "Task ID: " + taskID + "\n"
                + "Task Duration: " + taskDuration + " hours\n"
                + "*************************\n";
        JOptionPane.showMessageDialog(null, details, "Easy Kanban - Task Details", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to return total hours
    public float returnTotalHours(float hours) {
        return hours;
    }

    // Method to add tasks
    public void addTask() {
        float totalHours = 0;
        int size = Integer.parseInt(JOptionPane.showInputDialog("How many tasks do you wish to capture?"));

        // Ensure taskCount is updated as tasks are added
        taskCount = size;  // Adjust taskCount based on the size of the input

        for (int k = 0; k < size; k++) {
            taskName[k] = JOptionPane.showInputDialog("Enter Task name for task " + (k + 1) + ":");

            do {
                taskDescription[k] = JOptionPane.showInputDialog("Enter Task Description for task " + (k + 1) + " (max 50 characters):");
            } while (!checkDescription(taskDescription[k]));

            developerDetails[k] = JOptionPane.showInputDialog("Enter Developer Details (First and Last name) for task " + (k + 1) + ":");
            taskDuration[k] = Integer.parseInt(JOptionPane.showInputDialog("Enter Task Duration (in hours) for task " + (k + 1) + ":"));

            taskID[k] = createTaskID(taskName[k], k, developerDetails[k]);
            JOptionPane.showMessageDialog(null, "Task ID for task " + (k + 1) + ": " + taskID[k]);

            int statusOption = Integer.parseInt(JOptionPane.showInputDialog("Choose a status for task " + (k + 1) + ":\n1: To Do\n2: Doing\n3: Done"));
            switch (statusOption) {
                case 1:
                    taskStatus[k] = "To Do";
                    break;
                case 2:
                    taskStatus[k] = "Doing";
                    break;
                case 3:
                    taskStatus[k] = "Done";
                    break;
            }

            totalHours += returnTotalHours(taskDuration[k]);
        }

        JOptionPane.showMessageDialog(null, "The total hours of all tasks performed: " + totalHours + " hrs");
    }

    public void displayDoneTasks() {
        StringBuilder result = new StringBuilder("Tasks with status 'Done':\n");
        for (int i = 0; i < taskCount; i++) {
            if (taskName[i] != null && "Done".equals(taskStatus[i])) {  // Null check added here
                result.append("Developer: ").append(developerDetails[i]).append("\nTask Name: ").append(taskName[i]).append("\nTask Duration: ").append(taskDuration[i]).append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, result.toString());
    }

    // Method to display the task with the longest duration
    public void displayLongestTask() {
        if (taskCount == 0) {
            JOptionPane.showMessageDialog(null, "No tasks available.");
            return;
        }

        int longestTaskIndex = 0;
        for (int i = 1; i < taskCount; i++) {
            if (taskDuration[i] > taskDuration[longestTaskIndex]) {
                longestTaskIndex = i;
            }
        }

        JOptionPane.showMessageDialog(null, "Task with the longest duration:\nDeveloper: " + developerDetails[longestTaskIndex] + "\nDuration: " + taskDuration[longestTaskIndex]);
    }

    // Method to search for a task by name
    public void searchTaskByName(String name) {
        for (int i = 0; i < taskCount; i++) {
            if (taskName[i] != null && taskName[i].equalsIgnoreCase(name)) {  // Null check added here
                JOptionPane.showMessageDialog(null, "Task found:\nTask Name: " + taskName[i] + "\nDeveloper: " + developerDetails[i] + "\nTask Status: " + taskStatus[i]);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.");
    }

    // Method to search for tasks by developer
    public void searchTasksByDeveloper(String developer) {
        StringBuilder result = new StringBuilder("Tasks assigned to " + developer + ":\n");
        for (int i = 0; i < taskCount; i++) {
            if (developerDetails[i].equalsIgnoreCase(developer)) {
                result.append("Task Name: ").append(taskName[i]).append("\nTask Status: ").append(taskStatus[i]).append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, result.toString());
    }

    // Method to delete a task by name
    public void deleteTaskByName(String name) {
        for (int i = 0; i < taskCount; i++) {
            if (taskName[i].equalsIgnoreCase(name)) {
                // Shift tasks to the left to remove the task
                for (int j = i; j < taskCount - 1; j++) {
                    taskName[j] = taskName[j + 1];
                    developerDetails[j] = developerDetails[j + 1];
                    taskID[j] = taskID[j + 1];
                    taskDuration[j] = taskDuration[j + 1];
                    taskStatus[j] = taskStatus[j + 1];
                }
                // Reset the last task
                taskName[taskCount - 1] = null;
                developerDetails[taskCount - 1] = null;
                taskID[taskCount - 1] = null;
                taskDuration[taskCount - 1] = 0;
                taskStatus[taskCount - 1] = null;
                taskCount--;
                JOptionPane.showMessageDialog(null, "Task '" + name + "' deleted successfully.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.");
    }

    // Method to display a report of all tasks
    public void displayAllTasks() {
        if (taskCount == 0) {
            JOptionPane.showMessageDialog(null, "No tasks available.");
            return;
        }

        StringBuilder result = new StringBuilder("All Tasks:\n");
        for (int i = 0; i < taskCount; i++) {
            if (taskName[i] != null) {  // Null check added here
                result.append("\nTask Status: ").append(taskStatus[i])
                        .append("\nDeveloper Details: ").append(developerDetails[i])
                        .append("\nTask Name: ").append(taskName[i])
                        .append("\nTask Number: ").append(i + 1)
                        .append("\nTask Description: ").append(taskDescription[i])
                        .append("\nTask ID: ").append(taskID[i])
                        .append("\nTask Duration: ").append(taskDuration[i])
                        .append("\n-----------------------------\n")  // Adding separator between task details
                        .append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, result.toString());
    }

} 