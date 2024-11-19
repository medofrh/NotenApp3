# School Management and Grading System - Java Console Application

## Aenda
1. [Overview](#overview)
2. [Objectives](#objectives)
3. [Features](#features)
4. [Database Structure](#database-structure)
5. [Technology Stack](#technology-stack)
6. [Setup Instructions](#setup-instructions)
7. [UML Diagrams](#uml-diagrams)
8. [Learning Goals](#learning-goals)
9. [Contributing](#contributing)
10. [License](#license)
11. [Data Path](#data-path)

## Overview
This project is a **Java Console Application** developed as part of a school project to practice and improve skills in **Entity-Relationship Diagrams (ERD)**, **UML diagrams**, and **database programming**. The application connects to a **MySQL** database to manage student and teacher data, track class information, and record various grades throughout the academic year. The database schema and application are designed to support a comprehensive school management system, with a focus on applying programming concepts and database structure knowledge.

## Objectives
- **Learn and Practice ERD and UML Diagrams**: Gain experience in database design and object-oriented modeling through ERD and UML diagrams.
- **Improve Java Programming Skills**: Develop a full-fledged console application to manage school data and grades, reinforcing programming skills.
- **Explore Database Integration**: Work with **MySQL** to implement and interact with complex database schemas, learning SQL and JDBC integration in Java.

## Features
- **Student and Teacher Profiles**: Manage essential information such as name, birthdate, contact details, and academic status.
- **Class and Subject Management**: Define classes, assign teachers, and link subjects to educational tracks.
- **Customizable Grading Scales**: Support multiple grading systems (e.g., points, percentages, letter grades) with adaptable scales for different educational programs.
- **Grade Entry and Calculation**: Teachers can enter grades for various categories (e.g., participation, exams, projects). Semi-annual and annual grades are calculated, while teachers retain control over final report card grades.
- **Historical Data Retention**: Grades are never deleted, enabling future analysis and historical records.
- **Role-based Access**: Only designated teachers can enter grades for their assigned classes, minimizing data entry errors.

## Database Structure
The application is backed by a MySQL database structured around the following tables:

1. **Schueler (Students)**: Stores student profiles and academic data.
2. **Lehrer (Teachers)**: Holds teacher information and subjects taught.
3. **Notenskala (Grade Scale)**: Defines different grading scales used in each educational track.
4. **Notenart (Grade Type)**: Defines types of grades (e.g., exams, assignments).
5. **Bildungsgang (Education Track)**: Links to grading scale and specifies academic track details.
6. **Fach (Subjects)**: Manages subjects within specific educational tracks.
7. **Klasse (Classes)**: Tracks class details, teachers, and associated educational tracks.
8. **Noten (Grades)**: Records individual grades for students with grade type, value, and teacher details.
9. **Zeugnisnote (Final Report Card Grade)**: Contains final grades approved by teachers for each student and subject.

## Technology Stack
- **Programming Language**: Java
- **Database**: MySQL
- **Java Database Connectivity (JDBC)**: Used for database interaction.

## Setup Instructions
1. **Install MySQL**: Ensure MySQL is installed and running.
2. **Clone the Repository**.
3. **Import SQL Scripts**: Run the SQL scripts to create and initialize the database schema.
4. **Configure Database Connection**: Update database connection details in the Java code to connect to your MySQL instance.
5. **Run the Application**: Compile and run the Java application from the console.

## UML Diagrams
This project includes UML diagrams to illustrate class structure and relationships, providing a visual understanding of how the Java code is organized.

## Learning Goals
This project aims to help students:
- Develop a deeper understanding of ERD and UML diagrams.
- Enhance Java programming and object-oriented design skills.
- Gain experience with relational databases and SQL integration.

## Contributing
Contributions are welcome! This project is educational, so any improvements or suggestions that enhance its functionality or structure are appreciated. Feel free to fork the repository and submit a pull request.

## License
This project is licensed under the MIT License.

## Data Path
- ERD Diagrams: `docs/erd-diagrams`
- UML Diagrams: `docs/uml-diagrams`
- SQL Scripts: `database-scripts`