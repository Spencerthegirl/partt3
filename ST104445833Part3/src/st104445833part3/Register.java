/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package st104445833part3;

/**
 *
 * @author RC_Student_lab
 */
public class Register {
 
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    // Method to check if the username is valid
    public boolean checkUserName(String username) {
        boolean containsUnderscore = username != null && username.contains("_");
        boolean validLength = username != null && username.length() <= 5;
        return containsUnderscore && validLength;
    }

    // Method to check if the password meets complexity requirements
    public boolean checkPasswordComplexity(String password) {
        if (password.isEmpty()) {
            return false;
        }

        boolean hasUpperCase = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if ("!@#$%^&*()".indexOf(c) != -1) {
                hasSpecialCharacter = true;
            }
        }

        return password.length() >= 8 && hasUpperCase && hasNumber && hasSpecialCharacter;
    }

    // Method to register the user and return messages
    public String registerUser(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        return "Registration successful!";
    }

    // Method to verify login credentials
    public boolean loginUser(String inputUsername, String inputPassword) {
        // Debugging output
        System.out.println("Stored Username: " + this.username + ", Input Username: " + inputUsername);
        System.out.println("Stored Password: " + this.password + ", Input Password: " + inputPassword);

        return this.username != null && this.username.equals(inputUsername)
                && this.password != null && this.password.equals(inputPassword);
    }

    // Method to return login status message
    public String returnLoginStatus(boolean loggedIn) {
        if (loggedIn) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
