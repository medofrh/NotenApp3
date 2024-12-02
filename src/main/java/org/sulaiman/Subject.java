package org.sulaiman;

import java.util.ArrayList;
public class Subject implements FromDataBase{
    private int subjectId ;
    private static int counter= 0;
    private String name;

    private ArrayList<Grade> grades;

    public Subject(String name) {
        this.name = name;
        ++counter;
        this.subjectId = counter;
        this.grades = new ArrayList<>();
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

    public void addGrade(Grade grade){
        grades.add(grade);
    }

    public ArrayList<Grade> getGrade(){
        return grades;
    }

    public String toString() {
        return "{\n" +
                "subjectId=" + subjectId +
                ", name='" + name +
                "}"+ "\n";
    }
}
