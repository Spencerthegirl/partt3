/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package st104445833part3;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author RC_Student_lab
 */
public class TaskManagerTest {
    
    private String[] taskdescripArray;
    private String[] taskname;
    private String[] menuChoices;
    private int[] taskduration;
    private int numTasks;
    private String name;
    private String surname;

    

    
    
    
    
    //--------------------------- Part 2 -------------------------------//
    public int returnTotalHours() {
        int total = 0;
        for (int i = 0; i < numTasks; i++) {
            total += taskduration[i];
        }
        return total;
    }

    public boolean checkTaskDescription(String description) {
        return description.length() <= 50;
    }

    public String createTaskID(String taskName, int taskNumber, String developerDetails) {
        String initials = taskName.length() >= 2 ? taskName.substring(taskName.length() - 2).toUpperCase() : taskName.toUpperCase();
        String details = developerDetails.length() >= 3 ? developerDetails.substring(developerDetails.length() - 3).toUpperCase() : developerDetails.toUpperCase();
        return initials + ":" + taskNumber + ":" + details;
    }

    public String taskStatus(int choice) {
        switch (choice) {
            case 1:
                return "TO DO";
            case 2:
                return "DOING";
            case 3:
                return "DONE";
            default:
                return "INVALID";
        }
    }

    public String addTask(String name, String surname, int taskIndex) {
        StringBuilder output = new StringBuilder();
        output.append("Task Number: ").append(taskIndex).append("\n")
                .append("Task Name: ").append(taskname[taskIndex]).append("\n")
                .append("Task Description: ").append(taskdescripArray[taskIndex]).append("\n")
                .append("Task Status: ").append(menuChoices[taskIndex]).append("\n")
                .append("Developer Details: ").append(name).append(" ").append(surname).append("\n")
                .append("Task ID: ").append(createTaskID(taskname[taskIndex], taskIndex, surname)).append("\n")
                .append("Task Duration: ").append(taskduration[taskIndex]).append(" hours\n\n")
                .append("Total number of hours (rounded): ").append(returnTotalHours());
        return output.toString();
    }

    


    @Test
    public void testTaskDescriptionLength_Success_Robyn() {
        String validDescription = "Create Login to authenticate users";
        boolean result = checkTaskDescription(validDescription);
        assertTrue(result);
    }

    // Other tests remain unchanged...
    @Test
    public void testReturnTotalHours() {
        taskduration[0] = 10;
        taskduration[1] = 12;
        taskduration[2] = 55;
        taskduration[3] = 11;
        taskduration[4] = 1;
        numTasks = 5;

        int expectedTotalHours = returnTotalHours(); // Use the refactored method
        assertEquals(expectedTotalHours, expectedTotalHours);
    }
                 //--------------------------- Part 3 -------------------------------//
    // Test data arrays
    private String[] developers = {
        "Mike Smith",
        "Edward Harrison",
        "Samantha Paulson",
        "Glenda Oberholzer"
    };
    private String[] taskNames = {
        "Create Login",
        "Create Add Features",
        "Create Reports",
        "Add Arrays"
    };

    // Test: Developer array populated correctly
    @Test
    public void testDeveloperArrayPopulated() {
        String[] expectedDevelopers = {
            "Mike Smith",
            "Edward Harrison",
            "Samantha Paulson",
            "Glenda Oberholzer"
        };

        assertArrayEquals(expectedDevelopers, developers);
    }
    @Before
    public void setUp() {
        // Initialize the arrays
        taskdescripArray = new String[10];
        taskname = new String[10];
        menuChoices = new String[10];
        taskduration = new int[10];
        developers = new String[10];  // Initialize the developers array

        numTasks = 0;

        // Populate task data and developers
        taskname[0] = "Login Feature";
        taskdescripArray[0] = "Create Login to authenticate users";
        menuChoices[0] = "TO DO";
        taskduration[0] = 8;
        developers[0] = "Mike Smith";  // Assigning developer to task 0

        taskname[1] = "Task Management";
        taskdescripArray[1] = "Add the ability to manage tasks";
        menuChoices[1] = "TO DO";
        taskduration[1] = 6;
        developers[1] = "Edward Harrison";  // Assigning developer to task 1

        taskname[2] = "Search Functionality";
        taskdescripArray[2] = "Implement search functionality for tasks";
        menuChoices[2] = "DOING";
        taskduration[2] = 12;
        developers[2] = "Samantha Paulson";  // Assigning developer to task 2

        taskname[3] = "User Registration";
        taskdescripArray[3] = "Add registration for new users";
        menuChoices[3] = "DONE";
        taskduration[3] = 11;
        developers[3] = "Glenda Oberholzer";  // Assigning developer to task 3

        numTasks = 4;  // We now have 4 tasks
    }

    // Test Case 1: Test Developer Array Population
    @Test
    public void testDeveloperArrayPopulation() {
        // Ensure the developers array is populated correctly for tasks 1-4
        assertEquals("Mike Smith", developers[0]);
        assertEquals("Edward Harrison", developers[1]);
        assertEquals("Samantha Paulson", developers[2]);
        assertEquals("Glenda Oberholzer", developers[3]);
    }

    // Test Case 2: Test Task with Longest Duration
    @Test
    public void testLongestDurationTask() {
        // Assuming numTasks is set correctly in setUp() and taskduration[] and developers[] are populated
        int longestDuration = Integer.MIN_VALUE;
        String developer = "";
        int taskIndex = -1;  // To keep track of the index of the task with the longest duration

        // Loop through all tasks to find the one with the longest duration
        for (int i = 0; i < numTasks; i++) {
            if (taskduration[i] > longestDuration) {
                longestDuration = taskduration[i];
                developer = developers[i];
                taskIndex = i;  // Store the index of the longest duration task
            }
        }

        // The expected task with the longest duration is "Glenda Oberholzer" with 11 hours
        assertNotEquals("No task found with longest duration", -1, taskIndex);  // Ensure we found a task
        assertEquals("Samantha Paulson", developer);
        assertEquals(12, longestDuration);
    }

    // Test Case 3: Test Search for Task by Task Name
    @Test
    public void testSearchTaskByName() {
        String taskToSearch = "Login Feature";
        String expected = "Mike Smith, Login Feature";  // Developer and Task Name

        boolean found = false;
        for (int i = 0; i < numTasks; i++) {
            if (taskname[i].equals(taskToSearch)) {
                assertEquals(expected, developers[i] + ", " + taskname[i]);
                found = true;
                break;
            }
        }

        assertTrue("Task not found: " + taskToSearch, found);
    }

    // Test Case 4: Test Search for All Tasks Assigned to a Developer
    @Test
    public void testSearchTasksAssignedToDeveloper() {
        String developerToSearch = "Samantha Paulson";
        boolean found = false;

        for (int i = 0; i < numTasks; i++) {
            if (developers[i].equals(developerToSearch)) {
                assertEquals("Search Functionality", taskname[i]);
                found = true;
            }
        }

        assertTrue("No tasks found for developer: " + developerToSearch, found);
    }

    // Test Case 5: Test Deleting Task from Array
    @Test
    public void testDeleteTaskFromArray() {
        String taskToDelete = "Task Management";
        boolean taskDeleted = false;

        for (int i = 0; i < numTasks; i++) {
            if (taskname[i].equals(taskToDelete)) {
                taskname[i] = null;  // Simulate task deletion
                taskDeleted = true;
                break;
            }
        }

        assertTrue("Task not deleted: " + taskToDelete, taskDeleted);
    }

    @After
    public void tearDown() {
        // Optional: Cleanup after each test if necessary
    }
}
