package org.sulaiman;

import java.util.ArrayList;

public class ClassRoom implements FromDataBase{
    private final int classRoomId;
    private String name;
    private ArrayList<Subject> subjects;

    public ClassRoom(int classRoomId, String name) {
        this.classRoomId = classRoomId;
        this.name = name;
        this.subjects = new ArrayList<>();
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

    @Override
    public int getUid() {
        return this.classRoomId;
    }

    public String toString() {
        return "ClassRoom{\n" +
                "classRoomId=" + this.classRoomId +
                ", name='" + name + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}
