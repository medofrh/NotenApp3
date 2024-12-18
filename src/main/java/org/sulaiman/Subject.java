package org.sulaiman;

import java.util.ArrayList;
public class Subject implements FromDataBase{
    private int subjectId ;
    private String name;
    private ClassRoom classRoom;
    private Teacher teacher;
    private ArrayList<Student> students;

    private ArrayList<Grade> grades;

    public Subject(int uid, String name, ClassRoom classRoom, Teacher teacher) {
        this.name = name;
        this.subjectId = uid;
        this.grades = new ArrayList<>();
        this.students = new ArrayList<>();
        this.classRoom = classRoom;
        this.teacher = teacher;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Subject name can't be empty");
        }
        this.name = name;
    }

    public void addGrade(Grade grade){
        // TODO: It could be a list of grades for the same subject. So we need to check if the grade is already added and add grades to the list. grades.addALL(grades)
        grades.add(grade);
    }

    public ArrayList<Grade> getGrades(){
        return grades;
    }


    @Override
    public int getUid() {
        return this.subjectId;
    }


    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void addStudent(Student student){
        if(this.students.contains(student)) return;

        this.students.add(student);
        student.addSubject(this);
    }
    public void removeStudent(Student student){
        if(!this.students.contains(student)) return;

        this.students.remove(student);
        student.removeSubject(this);

        // delete grades
        DatabaseList<Grade> remainingGrade = new DatabaseList<>();
        for(Grade grade : this.getGrades()) {
            if(grade.getStudent() != student) {
                remainingGrade.add(grade);
            }
        }
        this.grades = remainingGrade;
    }

    public ArrayList<Student> getStudents() {
        return ConsoleUI.getStudents(this);
    }
}
