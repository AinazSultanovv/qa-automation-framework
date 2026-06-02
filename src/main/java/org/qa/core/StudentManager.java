
// Управление списком студентов
package org.qa.core;

import org.qa.models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private List<Student> students = new ArrayList<>();

    public void addStudent(String name, int grade) {

        Student newStudent = new Student(name, grade);
        students.add(newStudent);
    }

    public List<Student> getExcellentStudents() {
        List<Student> excellent = new ArrayList<>();


        for (Student s : students) {
            if (s.getGrade() == 5) {
                excellent.add(s);
            }
        }
        return excellent;
    }

    public Student findByName(String name) {
        for (Student s : students) {

            if (s.getName().equals(name)) {
                return s;
            }
        }

        return null;
    }
}