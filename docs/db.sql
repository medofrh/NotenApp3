CREATE TABLE teacher
(
    teacher_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50)  NOT NULL,
    last_name  VARCHAR(50)  NOT NULL,
    username   VARCHAR(50)  NOT NULL UNIQUE,
    email      VARCHAR(100) NOT NULL UNIQUE,
    password   VARCHAR(100) NOT NULL
);

CREATE TABLE classroom
(
    classroom_id INT PRIMARY KEY AUTO_INCREMENT
        name VARCHAR (50) NOT NULL
);

CREATE TABLE student
(
    student_id   INT PRIMARY KEY AUTO_INCREMENT,
    first_name   VARCHAR(50)  NOT NULL,
    last_name    VARCHAR(50)  NOT NULL,
    username     VARCHAR(50)  NOT NULL UNIQUE,
    email        VARCHAR(100) NOT NULL UNIQUE,
    password     VARCHAR(100) NOT NULL,
    classroom_id INT          NOT NULL,
    FOREIGN KEY (classroom_id) REFERENCES classroom (classroom_id)
);

CREATE TABLE subject
(
    subject_id   INT PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(50) NOT NULL,
    teacher_id   INT         NOT NULL,
    classroom_id INT         NOT NULL,
    FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id),
    FOREIGN KEY (classroom_id) REFERENCES classroom (classroom_id)
);

CREATE TABLE student_subject
(
    student_id INT NOT NULL,
    subject_id INT NOT NULL,
    PRIMARY KEY (student_id, subject_id),
    FOREIGN KEY (student_id) REFERENCES student (student_id),
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id)
);

CREATE TABLE grade
(
    grade_id   INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    subject_id INT NOT NULL,
    grade_number DOUBLE NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student (student_id),
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id)
);

-- Insert data into teacher
INSERT INTO teacher (teacher_id, first_name, last_name, username, email, password)
VALUES (1, 'John', 'Smith', 'jsmith', 'john.smith@example.com', 'password123'),
       (2, 'Jane', 'Doe', 'jdoe', 'jane.doe@example.com', 'password456'),
       (3, 'Alice', 'Johnson', 'ajohnson', 'alice.johnson@example.com', 'password789');

-- Insert data into classroom
INSERT INTO classroom (classroom_id, name)
VALUES (1, 'Math 101'),
       (2, 'Physics 101'),
       (3, 'Chemistry 101');

-- Insert data into student
INSERT INTO student (student_id, first_name, last_name, username, email, password, classroom_id)
VALUES (1, 'Michael', 'Brown', 'mbrown', 'michael.brown@example.com', 'password111', 1),
       (2, 'Sarah', 'Williams', 'swilliams', 'sarah.williams@example.com', 'password222', 1),
       (3, 'David', 'Miller', 'dmiller', 'david.miller@example.com', 'password333', 2),
       (4, 'Emily', 'Davis', 'edavis', 'emily.davis@example.com', 'password444', 2),
       (5, 'James', 'Garcia', 'jgarcia', 'james.garcia@example.com', 'password555', 3);

-- Insert data into subject
INSERT INTO subject (subject_id, name, teacher_id, classroom_id)
VALUES (1, 'Algebra', 1, 1),
       (2, 'Trigonometry', 1, 1),
       (3, 'Mechanics', 2, 2),
       (4, 'Thermodynamics', 2, 2),
       (5, 'Organic Chemistry', 3, 3);

-- Insert data into student_subject
INSERT INTO student_subject (student_id, subject_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 2),
       (3, 3),
       (3, 4),
       (4, 3),
       (4, 4),
       (5, 5);

-- Insert data into grade
INSERT INTO grade (grade_id, student_id, subject_id, grade_number)
VALUES (1, 1, 1, 85.5),
       (2, 1, 2, 90.0),
       (3, 2, 1, 88.0),
       (4, 2, 2, 92.5),
       (5, 3, 3, 78.0),
       (6, 3, 4, 82.0),
       (7, 4, 3, 85.0),
       (8, 4, 4, 87.5),
       (9, 5, 5, 89.0);