package org.sulaiman;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleUI {
    private static boolean isRunning = true;

    // Start the console UI
    public static void start() {
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
                        clearScreen();
                        displaySuccess("Welcome " + user.getFirstName() + " " + user.getLastName());

                        if (user instanceof Student) {
                            boolean isStudentRunning = true;
                            ArrayList<Subject> subjects = getSubjects(user);
                            while (isStudentRunning) {
                                Subject subject = displayStudentMenu(subjects, scanner);

                                if (subject != null) {
                                    clearScreen();
                                    // get grade for the student
                                    Grade grade = getGrade((Student) user, subject);
                                    if (grade != null) {
                                        System.out.println("Your grade for " + subject.getName() + " is: " + grade.getGradeNumber());
                                    } else {
                                        System.out.println("You don't have a grade for " + subject.getName());
                                    }
                                } else {
                                    clearScreen();
                                    isStudentRunning = false;
                                }
                            }
                        } else if (user instanceof Teacher) {
                            boolean isTeacherRunning = true;
                            ArrayList<Subject> subjects = getSubjects(user);
                            while (isTeacherRunning) {
                                ClassRoom classRoom = displayTeacherMenu(subjects, scanner, (Teacher) user);
                                if (classRoom != null) {
                                    clearScreen();
                                } else {
                                    clearScreen();
                                    isTeacherRunning = false;
                                }
                            }
                        }
                    }
                    break;
                case "2":
                    // Exit
                    System.out.println("Exiting...");
                    isRunning = false;
            }
        }
    }

    // Display the main menu
    public static void displayMainMenu() {
        System.out.println("==============Main Menu==============");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.println("=====================================");
        System.out.print("Enter your choice: ");
    }

    // Display the student menu and return the subject
    public static Subject displayStudentMenu(ArrayList<Subject> subjects, Scanner scanner) {
        Subject selectedSubject = null;

        while (selectedSubject == null) {
            System.out.println("==============Student Menu==============");
            System.out.println("Subjects:");
            for (int i = 0; i < subjects.size(); i++) {
                System.out.println((i + 1) + ". " + subjects.get(i).getName());
            }
            System.out.println("=======================================");
            System.out.println("0. Logout");
            System.out.println("=======================================");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) {
                    return null;
                } else if (choice > 0 && choice <= subjects.size()) {
                    selectedSubject = subjects.get(choice - 1);
                } else {
                    displayError("Invalid choice, please try again.");
                }
            } catch (Exception e) {
                displayError("Invalid choice, please try again.");
            }
        }
        return selectedSubject;
    }

    // Display the teacher menu
    public static ClassRoom displayTeacherMenu(ArrayList<Subject> subjects, Scanner scanner, Teacher teacher) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("==============Teacher Menu==============");
            // TODO: press c to cancel
            System.out.println("-1. Add Classroom");
            // Get all classrooms
            ArrayList<ClassRoom> classRooms = getClassRooms(subjects);
            ClassRoom selectedClassRoom = null;
            for (int i = 0; i < classRooms.size(); i++) {
                System.out.println((i + 1) + ". " + classRooms.get(i).getName());
            }
            System.out.println("0. Logout");
            System.out.println("=======================================");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) {
                    isRunning = false;
                } else if (choice == -1) {
                    // Add classroom
                    System.out.print("Enter the name of the classroom: ");
                    String className = scanner.nextLine();
                    ClassRoom newClassRoom = setClassRoom(new ClassRoom(0, className));
                    boolean isAddingSubject = true;
                    System.out.println("Enter 0 to stop adding subjects.");
                    while (isAddingSubject){
                        System.out.println("Enter the name of the subject: ");
                        String subjectName = scanner.nextLine();
                        if (subjectName.equals("0")){
                            isAddingSubject = false;
                        }else {
                            setSubject(new Subject(0, subjectName, newClassRoom, teacher));
                        }
                    }
                    // update the classRooms
                    classRooms = getClassRooms(getSubjects(teacher));
                    subjects = getSubjects(teacher);
                } else if (choice > 0 && choice <= classRooms.size() + 1) {
                    selectedClassRoom = classRooms.get(choice - 1);

                    // Get all subjects in the class
                    ArrayList<Subject> classSubjects = getSubjects(selectedClassRoom);
                    boolean isClassRunning = true;
                    clearScreen();
                    while (isClassRunning) {
                        System.out.println("==============" + selectedClassRoom.getName() + "==============");
                        for (int i = 0; i < classSubjects.size(); i++) {
                            System.out.println((i + 1) + ". " + classSubjects.get(i).getName());
                        }
                        System.out.println("0. Back");
                        System.out.println("=======================================");
                        System.out.print("Enter your choice: ");

                        try {
                            int classChoice = Integer.parseInt(scanner.nextLine());
                            if (classChoice == 0) {
                                isClassRunning = false;
                            } else if (classChoice > 0 && classChoice <= classSubjects.size()) {
                                // Get all students in the class
                                ArrayList<Student> students = getStudents(classSubjects.get(classChoice - 1));
                                boolean isSubjectRunning = true;
                                clearScreen();
                                while (isSubjectRunning) {
                                    System.out.println("==============" + classSubjects.get(classChoice - 1).getName() + "==============");
                                    System.out.println("-1. Add Student");
                                    for (int i = 0; i < students.size(); i++) {
                                        System.out.println((i + 1) + ". " + students.get(i).getFirstName() + " " + students.get(i).getLastName());
                                    }
                                    System.out.println("0. Back");
                                    System.out.println("=======================================");
                                    System.out.print("Enter your choice: ");
                                    try {
                                        int studentChoice = Integer.parseInt(scanner.nextLine());
                                        if (studentChoice == 0) {
                                            isSubjectRunning = false;
                                        } else if (studentChoice == -1){
                                            System.out.print("Is the student already in the system? (y/n):");
                                            String isStudentInSystem = scanner.nextLine();
                                            if (isStudentInSystem.equals("y")) {
                                                System.out.print("Enter the student's username: ");
                                                String username = scanner.nextLine();
                                                Student student = getStudent(username);
                                                if (student != null) {
                                                    updateStudent(student, classSubjects, selectedClassRoom);
                                                    displaySuccess("Student added successfully.");

                                                    // update the students
                                                    students = getStudents(classSubjects.get(classChoice - 1));
                                                } else {
                                                    displayError("Student not found.");
                                                }
                                            }else {
                                                System.out.print("Enter the student's first name: ");
                                                String firstName = scanner.nextLine();
                                                System.out.print("Enter the student's last name: ");
                                                String lastName = scanner.nextLine();
                                                System.out.print("Enter the student's username: ");
                                                String username = scanner.nextLine();
                                                System.out.print("Enter the student's password: ");
                                                String password = scanner.nextLine();
                                                System.out.print("Enter the student's email: ");
                                                String email = scanner.nextLine();
                                                Student student = new Student(0, firstName, lastName, username, password, email);
                                                if (setStudent(student, classSubjects, selectedClassRoom)) {
                                                    displaySuccess("Student added successfully.");
                                                } else {
                                                    displayError("Failed to add student.");
                                                }
                                                students = getStudents(classSubjects.get(classChoice - 1));
                                            }
                                        }else if (studentChoice > 0 && studentChoice <= students.size()) {
                                            // check choice for view or edit
                                            boolean isStudentRunning = true;
                                            clearScreen();
                                            while (isStudentRunning) {
                                                System.out.println("==============" + students.get(studentChoice - 1).getFirstName() + " " + students.get(studentChoice - 1).getLastName() + "==============");
                                                System.out.println("1. View Grade");
                                                if (getGrade(students.get(studentChoice - 1), classSubjects.get(classChoice - 1)) == null) {
                                                    System.out.println("2. Set Grade");
                                                } else {
                                                    System.out.println("2. Edit Grade");
                                                }
                                                System.out.println("0. Back");
                                                System.out.println("=======================================");
                                                System.out.print("Enter your choice: ");
                                                int studentChoice2 = Integer.parseInt(scanner.nextLine());
                                                try {
                                                    if (studentChoice2 == 0) {
                                                        isStudentRunning = false;
                                                    } else if (studentChoice2 == 1) {
                                                        // Get the grade for the student
                                                        Grade grade = getGrade(students.get(studentChoice - 1), classSubjects.get(classChoice - 1));
                                                        if (grade != null) {
                                                            System.out.println("Grade for " + students.get(studentChoice - 1).getFirstName() + " " + students.get(studentChoice - 1).getLastName() + " is: " + grade.getGradeNumber());
                                                        } else {
                                                            System.out.println("No grade for " + students.get(studentChoice - 1).getFirstName() + " " + students.get(studentChoice - 1).getLastName());
                                                        }
                                                    } else if (studentChoice2 == 2) {
                                                        // Set the grade for the student
                                                        System.out.print("Enter the grade for " + students.get(studentChoice - 1).getFirstName() + " " + students.get(studentChoice - 1).getLastName() + ": ");
                                                        double gradeNumber = Double.parseDouble(scanner.nextLine());
                                                        if (getGrade(students.get(studentChoice - 1), classSubjects.get(classChoice - 1)) == null) {
                                                            if (setGrade(students.get(studentChoice - 1), classSubjects.get(classChoice - 1), gradeNumber)) {
                                                                displaySuccess("Grade set successfully.");
                                                                isStudentRunning = false;
                                                            } else {
                                                                displayError("Failed to set grade.");
                                                            }
                                                        } else {
                                                            if (updateGrade(students.get(studentChoice - 1), classSubjects.get(classChoice - 1), gradeNumber)) {
                                                                displaySuccess("Grade updated successfully.");
                                                            } else {
                                                                displayError("Failed to update grade.");
                                                            }
                                                        }
                                                    } else {
                                                        displayError("Invalid choice, please try again.");
                                                    }
                                                } catch (Exception e) {
                                                    displayError("Invalid choice, please try again.");
                                                }
                                            }
                                        } else {
                                            displayError("Invalid choice, please try again.");
                                        }
                                    } catch (Exception e) {
                                        displayError("Invalid choice, please try again.");
                                    }
                                }
                            } else {
                                displayError("Invalid choice, please try again.");
                            }
                        } catch (Exception e) {
                            displayError("Invalid choice, please try again.");
                        }
                    }

                    for (int i = 0; i < classSubjects.size(); i++) {
                        System.out.println((i + 1) + ". " + classSubjects.get(i).getName());
                    }
                    System.out.println("0. Back");

                    // Get all students in the class


                } else {
                    displayError("Invalid choice, please try again.");
                }
            } catch (Exception e) {
                displayError("Invalid choice, please try again.");
            }
        }
        return null;
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

    // Login method
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
                clearScreen();
                // Student login
                System.out.println("==============Student Login==============");
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
                clearScreen();
                System.out.println("==============Teacher Login==============");
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
            while (resultSet.next()) {
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
            while (resultSet.next()) {
                subjects.add(ConsoleUI.getSubject(resultSet.getInt("subject_id")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subjects;
    }

    public static ArrayList<Subject> getSubjects(ClassRoom classRoom) {
        ArrayList<Subject> subjects = new ArrayList<>();
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // read mm relations
        String query = "SELECT * FROM subject WHERE classroom_id = ?";
        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setInt(1, classRoom.getClassRoomId());
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                subjects.add(new Subject(
                        resultSet.getInt("subject_id"),
                        resultSet.getString("name"),
                        classRoom,
                        ConsoleUI.getTeacher(resultSet.getInt("teacher_id"))
                ));
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
            while (resultSet.next()) {
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

    public static boolean setSubject(Subject subject) {
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // read mm relations
        String query = "insert into subject (name, teacher_id, classroom_id) values (?, ?, ?)";

        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setString(1, subject.getName());
            stmt.setInt(2, subject.getTeacher().getUid());
            stmt.setInt(3, subject.getClassRoom().getClassRoomId());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Teacher getTeacher(int teacherId) {
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // read mm relations
        String query = "SELECT * FROM teacher WHERE teacher_id = ?";
        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setInt(1, teacherId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
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
            while (resultSet.next()) {
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

    public static ClassRoom setClassRoom(ClassRoom classRoom) {
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // read mm relations
        String query = "insert into classroom (name) values (?)";

        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, classRoom.getName());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows > 0){
                ResultSet resultSet = stmt.getGeneratedKeys();
                if(resultSet.next()){
                    classRoom = new ClassRoom(
                            resultSet.getInt(1),
                            classRoom.getName()
                    );
                    return classRoom;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<ClassRoom> getClassRooms(ArrayList<Subject> subjects) {
        ArrayList<ClassRoom> classRooms = new ArrayList<>();
        DatabaseManager dbManager = DatabaseManager.getInstance();

        if (subjects.isEmpty()) {
            return classRooms;
        }

        // read mm relations
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM classroom WHERE classroom_id IN (");
        for (int i = 0; i < subjects.size(); i++) {
            queryBuilder.append("?");
            if (i != subjects.size() - 1) {
                queryBuilder.append(",");
            }
        }
        queryBuilder.append(")");
        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(queryBuilder.toString());

            for (int i = 0; i < subjects.size(); i++) {
                stmt.setInt(i + 1, subjects.get(i).getClassRoom().getClassRoomId());
            }
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                classRooms.add(new ClassRoom(
                        resultSet.getInt("classroom_id"),
                        resultSet.getString("name")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classRooms;
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
            while (resultSet.next()) {
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

    public static Student getStudent(String username) {
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // read mm relations
        String query = "SELECT * FROM student WHERE username = ?";
        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
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

    public static boolean updateStudent(Student student, ArrayList<Subject> subjects, ClassRoom classRoom) {
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // read mm relations
        String queryStudent = "update student set classroom_id = ? where student_id = ?";
        String querySubject = "update student_subject set subject_id = ? where student_id = ?";

        // set student to class
        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(queryStudent);
            stmt.setInt(1, classRoom.getClassRoomId());
            stmt.setInt(2, student.getUid());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // set student to subjects
        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(querySubject);
            for (Subject subject : subjects) {
                stmt.setInt(1, student.getUid());
                stmt.setInt(2, subject.getSubjectId());
                stmt.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean setStudent(Student student, ArrayList<Subject> subjects, ClassRoom classRoom) {
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // read mm relations
        String queryStudent = "insert into student (classroom_id, first_name, last_name, username, password, email) values (?, ?, ?, ?, ?, ?)";
        String querySubject = "insert into student_subject (student_id, subject_id) values (?, ?)";

        // add student to class
        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(queryStudent, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, classRoom.getClassRoomId());
            stmt.setString(2, student.getFirstName());
            stmt.setString(3, student.getLastName());
            stmt.setString(4, student.getUserName());
            stmt.setString(5, student.getPassword());
            stmt.setString(6, student.getEmail());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows > 0){
                ResultSet resultSet = stmt.getGeneratedKeys();
                if(resultSet.next()){
                    student = new Student(
                            resultSet.getInt(1),
                            student.getFirstName(),
                            student.getLastName(),
                            student.getUserName(),
                            student.getPassword(),
                            student.getEmail()
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // add student to subjects
        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(querySubject);
            for (Subject subject : subjects) {
                stmt.setInt(1, student.getUid());
                stmt.setInt(2, subject.getSubjectId());
                stmt.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Grade getGrade(Student student, Subject subject) {
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // read mm relations
        String query = "SELECT * FROM grade WHERE student_id = ? AND subject_id = ?";
        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setInt(1, student.getUid());
            stmt.setInt(2, subject.getSubjectId());
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                return new Grade(
                        resultSet.getInt("grade_id"),
                        ConsoleUI.getStudent(resultSet.getInt("student_id")),
                        ConsoleUI.getSubject(resultSet.getInt("subject_id")),
                        resultSet.getDouble("grade_number")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean setGrade(Student student, Subject subject, double gradeNumber) {
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // read mm relations
        String query = "insert into grade (student_id, subject_id, grade_number) values (?, ?, ?)";

        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setInt(1, student.getUid());
            stmt.setInt(2, subject.getSubjectId());
            stmt.setDouble(3, gradeNumber);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateGrade(Student student, Subject subject, double gradeNumber) {
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // read mm relations
        String query = "update grade set grade_number = ? where student_id = ? and subject_id = ?";

        try {
            PreparedStatement stmt = dbManager.getConnection().prepareStatement(query);
            stmt.setDouble(1, gradeNumber);
            stmt.setInt(2, student.getUid());
            stmt.setInt(3, subject.getSubjectId());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}