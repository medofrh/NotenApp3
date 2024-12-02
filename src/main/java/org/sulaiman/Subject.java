package org.sulaiman;

public class Subject implements FromDataBase{
    private int subjectId ;
    private static int counter= 0;
    private String name;

    private Grade grade;

    public Subject(String name) {
        this.name = name;
        ++counter;
        this.subjectId = counter;

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

    public String toString() {
        return "{\n" +
                "subjectId=" + subjectId +
                ", name='" + name +
                ", grade=" + grade +
                "}"+ "\n";
    }
}
