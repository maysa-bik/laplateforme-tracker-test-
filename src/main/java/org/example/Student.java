package org.example;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private float grade;

    public Student(int id, String firstName, String lastName, int age, float grade) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.grade = grade;
    }

    // Getters and Setters

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                '}';
    }
}
