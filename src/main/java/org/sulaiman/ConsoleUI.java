package org.sulaiman;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleUI {
    private static boolean isRunning = true;

    // Start the console UI
    public static AbstractUser start() {
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            displayMainMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Login
                    clearScreen();
                    AbstractUser user = login(scanner);
                    if (user != null) {
                        return user;
                    }
                    break;
                case "2":
                    // Register
                    System.out.println("Register");
                    clearScreen();
                    isRunning = false;
                    return null;
                case "3":
                    // Exit
                    System.out.println("Exiting...");
                    isRunning = false;
                    return null;
                default:
                    displayError("Invalid choice, please try again.");
                    return null;
            }
        }
        return null;
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

    public static AbstractUser login(Scanner scanner) {
        // Login
        clearScreen();
        try {
            DatabaseManager dbManager = DatabaseManager.getInstance();
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
                        if (resultSet.isLast()) {
                            return new Student(
                                    resultSet.getInt("student_id"),
                                    resultSet.getString("first_name"),
                                    resultSet.getString("last_name"),
                                    resultSet.getString("username"),
                                    resultSet.getString("password"),
                                    resultSet.getString("email")
                            );
                        } else {
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
                        if (resultSet.isLast()) {
                            return new Teacher(
                                    resultSet.getInt("teacher_id"),
                                    resultSet.getString("first_name"),
                                    resultSet.getString("last_name"),
                                    resultSet.getString("username"),
                                    resultSet.getString("password"),
                                    resultSet.getString("email")
                            );
                        } else {
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
        } catch (Exception e) {
            return null;
        }
    }

    public static ArrayList<Subject> getSubjects(AbstractUser user) {
        if (user instanceof Student) {
            return getSubjects((Student) user);
        } else if (user instanceof Teacher) {
            return getSubjects((Teacher) user);
        }
        return new ArrayList<>();
    }

    public static ArrayList<Subject> getSubjects(Student student) {
        ArrayList<Subject> subjects = new ArrayList<>();
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // read mm relations
        String query = "SELECT * FROM student_subject WHERE student_id = ?";
        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setInt(1, student.getUid());
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                subjects.add(ConsoleUI.getSubject(resultSet.getInt("subject_id")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subjects;
    }

    public static ArrayList<Subject> getSubjects(Teacher teacher) {
        ArrayList<Subject> subjects = new ArrayList<>();
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // read mm relations
        String query = "SELECT subject_id FROM subject WHERE teacher_id = ?";
        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setInt(1, teacher.getUid());
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                subjects.add(ConsoleUI.getSubject(resultSet.getInt("subject_id")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subjects;
    }

    public static Subject getSubject(int subjectId) {
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // read mm relations
        String query = "SELECT * FROM subject WHERE subject_id = ?";
        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setInt(1, subjectId);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                return new Subject(
                        resultSet.getInt("subject_id"),
                        resultSet.getString("name"),
                        ConsoleUI.getClassRoom(resultSet.getInt("classroom_id")),
                        ConsoleUI.getTeacher(resultSet.getInt("teacher_id"))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Teacher getTeacher(int teacherId) {
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // read mm relations
        String query = "SELECT * FROM teacher WHERE teacher_id = ?";
        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setInt(1, teacherId);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                return new Teacher(
                        resultSet.getInt("teacher_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ClassRoom getClassRoom(int classRoomId) {
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // read mm relations
        String query = "SELECT * FROM classroom WHERE classroom_id = ?";
        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setInt(1, classRoomId);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                return new ClassRoom(
                        resultSet.getInt("classroom_id"),
                        resultSet.getString("name")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Student> getStudents(Subject subject) {
        ArrayList<Student> students = new ArrayList<>();
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // read mm relations
        String query = "SELECT student_id FROM student_subject WHERE subject_id = ?";
        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setInt(1, subject.getSubjectId());
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                students.add(ConsoleUI.getStudent(resultSet.getInt("student_id")));
            }
            return students;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Student getStudent(int studentId) {
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // read mm relations
        String query = "SELECT * FROM student WHERE student_id = ?";
        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setInt(1, studentId);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                return new Student(
                        resultSet.getInt("student_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}