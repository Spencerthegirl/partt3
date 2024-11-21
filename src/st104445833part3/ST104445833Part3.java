package st104445833part3;

import javax.swing.*;
import java.util.*;

public class ST104445833Part3 {

    public static void main(String[] args) {
        // Initializing objects and UI elements
        Register registerObj = new Register(); // Register object to handle user registration and login
        TaskManager taskObj = new TaskManager(100);  // TaskManager object to handle task-related operations, max 100 tasks
        
        Scanner input = new Scanner(System.in); // Scanner to read user input from console
        JDialog window = new JDialog(); // JDialog window for GUI, set as top-most
        window.setAlwaysOnTop(true);

        System.out.println("\t\tRegistration with Easy Kanban\n");

        // Declare variables to hold user input
        String firstName, lastName, username, password, enteredUsername, enteredPassword;

        // Collect user's first and last name
        System.out.print("Enter your first name: ");
        firstName = input.nextLine();

        System.out.print("Enter your last name: ");
        lastName = input.nextLine();

        // Collect and validate the username (must contain underscore and max 5 characters)
        System.out.print("Enter your username (Username must contain an underscore and be no more than 5 characters): ");
        username = input.nextLine();

        // Collect and validate password (must be at least 8 characters, contain capital, number, and special character)
        System.out.print("Enter your password (Password must be at least 8 characters long, contain a capital letter, a number, and a special character): ");
        password = input.nextLine();

        // Validate the username entered by the user
        while (!registerObj.checkUserName(username)) {
            System.out.print("Invalid username! Enter a username (5 or fewer characters, containing an underscore): ");
            username = input.nextLine(); // Prompt again if invalid
        }

        // Validate the password entered by the user
        while (!registerObj.checkPasswordComplexity(password)) {
            System.out.print("Invalid password! Enter a password (8 or more characters, with a digit, capital letter, and special character): ");
            password = input.nextLine(); // Prompt again if invalid
        }

        // Attempt to register the user with provided details
        String registrationStatus = registerObj.registerUser(firstName, lastName, username, password);
        System.out.println(registrationStatus); // Show the result of registration

        // If registration is successful, proceed to login
        if (registrationStatus.equals("Registration successful!")) {
            System.out.println("Please log in:");

            boolean loggedIn = false;
            // Continue prompting the user until they successfully log in
            while (!loggedIn) {
                System.out.print("Enter your username: ");
                enteredUsername = input.nextLine();

                System.out.print("Enter your password: ");
                enteredPassword = input.nextLine();

                // Validate login credentials
                loggedIn = registerObj.loginUser(enteredUsername, enteredPassword);
                if (loggedIn) {
                    System.out.println("Login successful!");
                    JOptionPane.showMessageDialog(null, "Welcome to Easy Kanban", "Easy Kanban", JOptionPane.INFORMATION_MESSAGE);
                    taskMenu(taskObj); // Open the task menu upon successful login
                } else {
                    System.out.println("Invalid username or password. Please try again.");
                }
            }
        }
    }

    // Task menu that allows users to perform different task-related operations
    public static void taskMenu(TaskManager taskObj) {
        int statusInput, report; // Variables for user menu input and report selection
        boolean keepRunning = true; // Flag to keep the menu running
        boolean quit = false; // Flag to quit the menu

        // Main loop for task menu until user decides to quit
        while (!quit) {
            // Display the task menu and prompt for user choice
            String input = JOptionPane.showInputDialog(null, 
                "1. Add Tasks\n2. Show Report\n3. Exit", "Easy Kanban", JOptionPane.QUESTION_MESSAGE);

            // Validate user input to ensure it's a valid number between 1 and 3
            try {
                statusInput = Integer.parseInt(input);
                if (statusInput < 1 || statusInput > 3) {
                    JOptionPane.showMessageDialog(null, "Invalid option. Please enter a number between 1 and 3.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    continue;  // Ask for input again if the option is out of range
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                continue;  // Ask for input again if the input is not a number
            }

            // Switch based on the user choice
            switch (statusInput) {
                case 1:
                    // Option to add tasks
                    taskObj.addTask(); // Call addTask() method from TaskManager class
                    break;
                case 2:
                    // Option to show reports
                    boolean reportMenu = true;

                    // Nested loop for report menu until user chooses to go back to main menu
                    while (reportMenu) {
                        // Display report options and prompt for selection
                        String reportInput = JOptionPane.showInputDialog(null,
                            "Show Report Menu (Please Select an Option to View):\n"
                            + "1. Display the Developer, Task Names and Task Duration for all tasks with the status of done.\n"
                            + "2. Display the Developer and Duration of the class with the longest duration.\n"
                            + "3. Search for a task with a Task Name.\n"
                            + "4. Search for all tasks assigned to a developer.\n"
                            + "5. Delete a task using the Task Name.\n"
                            + "6. Display a report that lists the full details of all captured tasks.\n"
                            + "7. Back to Main Menu.");

                        try {
                            report = Integer.parseInt(reportInput);
                            if (report < 1 || report > 7) {
                                JOptionPane.showMessageDialog(null, "Invalid option. Please select a number between 1 and 7.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                                continue; // Re-prompt for valid report menu selection
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            continue; // Re-prompt for valid input
                        }

                        // Switch case for handling each report option
                        switch (report) {
                            case 1:
                                taskObj.displayDoneTasks(); // Display tasks with "done" status
                                break;
                            case 2:
                                taskObj.displayLongestTask(); // Display the longest task
                                break;
                            case 3:
                                // Search tasks by task name
                                String taskName = JOptionPane.showInputDialog("Enter task name to search:");
                                taskObj.searchTaskByName(taskName);
                                break;
                            case 4:
                                // Search tasks by developer's name
                                String developer = JOptionPane.showInputDialog("Enter developer name to search tasks (Firstname and LastName):");
                                taskObj.searchTasksByDeveloper(developer);
                                break;
                            case 5:
                                // Delete task by task name
                                String deleteTaskName = JOptionPane.showInputDialog("Enter task name to delete:");
                                taskObj.deleteTaskByName(deleteTaskName);
                                break;
                            case 6:
                                taskObj.displayAllTasks(); // Display full list of tasks
                                break;
                            case 7:
                                JOptionPane.showMessageDialog(null, "Returning to the main menu.");
                                reportMenu = false; // Exit report menu
                                break;
                            default:
                                // Handle unexpected values (shouldn't happen due to validation)
                                JOptionPane.showMessageDialog(null, "Invalid Value. Try Again.");
                        }
                    }
                    break;
                case 3:
                    quit = true; // Exit task menu
                    JOptionPane.showMessageDialog(null, "Leaving so soon.", "Easy Kanban", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    // Handle invalid menu option (shouldn't happen due to validation)
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.", "Easy Kanban", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
