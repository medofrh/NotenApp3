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
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Subject name can't be empty");
        }
        this.name = name;
    }

    public void addGrade(Grade grade){
        // TODO: It could be a list of grades for the same subject. So we need to check if the grade is already added and add grades to the list. grades.addALL(grades)
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
