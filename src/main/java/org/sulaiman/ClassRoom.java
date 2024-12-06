package org.sulaiman;

import java.util.ArrayList;

public class ClassRoom implements FromDataBase{
    private final int classRoomId;
    private String name;

    private ArrayList<Student> students;
    private ArrayList<Subject> subjects;

    public ClassRoom(int classRoomId, String name, ArrayList<Subject> subjects) {
        this.classRoomId = classRoomId;
        this.name = name;
        this.subjects = (subjects != null) ? subjects : new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student){
        if(student == null){
            throw new IllegalArgumentException("Student can't be null");
        }
        this.students.add(student);
    }

    public void removeStudent(Student student){
        if(student == null){
            throw new IllegalArgumentException("Student can't be null");
        }
        this.students.remove(student);
    }
    public ArrayList<Student> getStudents() {
        return students;
    }
    public void addSubject(Subject subject){
        this.subjects.add(subject);
    }

    public void removeSubject(Subject subject){
        this.subjects.remove(subject);
    }
    public ArrayList<Subject> getSubjects() {
        return new ArrayList<>(subjects);
    }
    public int getClassRoomId() {
        return classRoomId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("ClassRoom name can't be empty");
        }
        this.name = name;
    }

    public String toString() {
        return "ClassRoom{\n" +
                "classRoomId=" + this.classRoomId +
                ", name='" + name + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}
