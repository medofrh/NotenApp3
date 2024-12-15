package org.sulaiman;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                    clearScreen();
                    login(scanner);
                    break;
                case "2":
                    // Register
                    System.out.println("Register");
                    clearScreen();
                    isRunning = false;
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
        System.out.print("Enter your choice: ");
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

    // Clear screen
    public static void clearScreen() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            try {
                Process process = Runtime.getRuntime().exec("cls");
                process.waitFor();
                System.out.println("\033[H\033[2J");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                Process process = Runtime.getRuntime().exec("clear");
                process.waitFor();
                System.out.print("\033[H\033[2J");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Object login(Scanner scanner) {
        // Login
        clearScreen();
        DatabaseManager dbManager = new DatabaseManager();
        System.out.println("==============Login==============");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.println("=================================");
        System.out.print("Enter your choice: ");
        String loginChoice = scanner.nextLine();
        if (loginChoice.equals("1")) {
            // Student login
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            // Check if the username and password are correct
            String query = "SELECT * FROM student WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = dbManager.getConnection().prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet resultSet = stmt.executeQuery();

                if (resultSet.next()) {
                    if (resultSet.isLast()){
                        return new Student(
                                resultSet.getInt("id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                resultSet.getString("username"),
                                resultSet.getString("password"),
                                resultSet.getString("email")
                        );
                    }else {
                        displayError("Invalid username or password, please try again.");
                    }
                } else {
                    displayError("Invalid username or password, please try again.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (loginChoice.equals("2")) {
            // Teacher login
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            // Check if the username and password are correct
            String query = "SELECT * FROM teacher WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = dbManager.getConnection().prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet resultSet = stmt.executeQuery();

                if (resultSet.next()) {
                    if (resultSet.isLast()){
                        return new Teacher(
                                resultSet.getInt("id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                resultSet.getString("username"),
                                resultSet.getString("password"),
                                resultSet.getString("email")
                        );
                    }else {
                        displayError("Invalid username or password, please try again.");
                    }
                } else {
                    displayError("Invalid username or password, please try again.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            displayError("Invalid choice, please try again.");
        }
        return null;
    }
}