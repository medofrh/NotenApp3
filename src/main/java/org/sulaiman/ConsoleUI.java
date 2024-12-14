package org.sulaiman;

import java.util.Scanner;

public class ConsoleUI {
    // Start the console UI
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            displayMainMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Login
                    System.out.println("Login");
                    break;
                case "2":
                    // Register
                    System.out.println("Register");
                    break;
                case "3":
                    // Exit
                    System.out.println("Exiting...");
                    isRunning = false;
                    break;
                default:
                    displayError("Invalid choice, please try again.");
            }
        }
    }
    // Display the main menu
    public static void displayMainMenu() {
        System.out.println("==============Main Menu==============");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.println("=====================================");
        System.out.println("Enter your choice: ");
    }

    // Display error message
    public static void displayError(String message) {
        System.out.println("Error: " + message);
    }

    // Display success message
    public static void displaySuccess(String message) {
        System.out.println("Success: " + message);
    }

    // Display custom message
    public static void displayMessage(String message) {
        System.out.println(message);
    }
}
