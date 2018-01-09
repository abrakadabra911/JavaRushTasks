package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    protected int age;
    protected String name;
    private List<Student> students = new ArrayList<>();
    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public  List<Student> getStudents() {
        return students;
    }

    public  void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Student getStudentWithAverageGrade(double averageGrade) {
       for (Student x: students) if(x.getAverageGrade()==averageGrade) return x;
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        double grade = Double.MIN_VALUE;
        Student max = null;
        for (Student x: students){
            if(x.getAverageGrade()>=grade){
                max = x;
                grade = x.getAverageGrade();
            }
        }
        return max;
    }

    public Student getStudentWithMinAverageGrade(){
        double grade = Double.MAX_VALUE;
        Student min = null;
        for (Student x: students) {
            if(x.getAverageGrade()<=grade){
                min = x;
                grade = x.getAverageGrade();
            }
        }
        return min;
    }

    public void expel(Student student){
        if (student != null && students.contains(student))
            students.remove(student);
    }
}