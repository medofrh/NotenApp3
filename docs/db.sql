CREATE TABLE teacher
(
    teacher_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    email      VARCHAR(50) NOT NULL,
    username   VARCHAR(50) NOT NULL UNIQUE,
    password   VARCHAR(50) NOT NULL
);

CREATE TABLE class
(
    class_id INT PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(50) NOT NULL
);

CREATE TABLE student
(
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    email      VARCHAR(50) NOT NULL,
    username   VARCHAR(50) NOT NULL UNIQUE,
    password   VARCHAR(50) NOT NULL,
    class_id   INT         NOT NULL,
    FOREIGN KEY (class_id) REFERENCES class (class_id)
);

CREATE TABLE subject
(
    subject_id INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(50) NOT NULL,
    class_id   INT,
    teacher_id INT,
    FOREIGN KEY (class_id) REFERENCES class (class_id),
    FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id)
);

CREATE TABLE student_subject
(
    PRIMARY KEY (student_id, subject_id),
    student_id INT,
    subject_id INT,
    FOREIGN KEY (student_id) REFERENCES student (student_id),
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id)
);

CREATE TABLE grade
(
    grade_id   INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    subject_id INT NOT NULL,
    grade      DECIMAL(2, 1),
    FOREIGN KEY (student_id, subject_id) REFERENCES student_subject (student_id, subject_id)
);