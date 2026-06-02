
// Модель студента с именем и оценкой
package org.qa.models;

public class Student {
    private String name;
    private int grade;

    public Student(String name, int grade){
        this.name = name;
        if (grade < 1 || grade > 5){
            throw new IllegalArgumentException("Ошибка, оценка должна быть от 1 до 5");
        }
        else {
            this.grade = grade;
        }
    }
    public String getName(){return name;};
    public int getGrade(){return grade;};

    @Override
    public String toString() {
        return "Student{name='" + name + "', grade=" + grade + "}";
    }

}
