package org.sulaiman;

public class Subject {
    private int subjectId;
    private String name;
    private ClassRoom classRoom;
    private Teacher teacher;

    public Subject(int subjectId, String name, ClassRoom classRoom, Teacher teacher) {
        this.subjectId = subjectId;
        this.name = name;
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
        this.name = name;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
