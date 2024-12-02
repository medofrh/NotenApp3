package org.sulaiman;

import java.util.ArrayList;

public class ClassRoom implements FromDataBase{
    private int classRoomId;
    private String name;

    private ArrayList<Student> students;
    private ArrayList<Subject> subjects;

    public ClassRoom(int classRoomId, String name, ArrayList<Subject> subjects) {
        this.classRoomId = classRoomId;
        this.name = name;
        this.subjects = subjects;
    }
    public void addSubject(Subject subject){
        this.subjects.add(subject);
    }
    public ArrayList<Subject> getSubjects() {
        return subjects;
    }
    public int getClassRoomId() {
        return classRoomId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    //TODO removeSubject method

    public String toString() {
        return "ClassRoom{\n" +
                "classRoomId=" + this.classRoomId +
                ", name='" + name + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}
