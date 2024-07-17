package org.example;


import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class App {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        try {
            dbManager.connect();
            StudentService studentService = new StudentService(dbManager);
            boolean exit = false;

            while (!exit) {
                System.out.println("\nStudent Management System");
                System.out.println("1. Add Student");
                System.out.println("2. Update Student");
                System.out.println("3. Delete Student");
                System.out.println("4. Display All Students");
                System.out.println("5. Search Student by ID");
                System.out.println("6. Sort Students");
                System.out.println("7. Exit");
                int choice = Utils.getIntInput("Enter your choice: ");

                switch (choice) {
                    case 1:
                        String firstName = Utils.getStringInput("Enter first name: ");
                        String lastName = Utils.getStringInput("Enter last name: ");
                        int age = Utils.getIntInput("Enter age: ");
                        float grade = Utils.getFloatInput("Enter grade: ");
                        studentService.addStudent(firstName, lastName, age, grade);
                        System.out.println("Student added successfully.");
                        break;
                    case 2:
                        int idToUpdate = Utils.getIntInput("Enter student ID to update: ");
                        firstName = Utils.getStringInput("Enter new first name: ");
                        lastName = Utils.getStringInput("Enter new last name: ");
                        age = Utils.getIntInput("Enter new age: ");
                        grade = Utils.getFloatInput("Enter new grade: ");
                        studentService.updateStudent(idToUpdate, firstName, lastName, age, grade);
                        System.out.println("Student updated successfully.");
                        break;
                    case 3:
                        int idToDelete = Utils.getIntInput("Enter student ID to delete: ");
                        studentService.deleteStudent(idToDelete);
                        System.out.println("Student deleted successfully.");
                        break;
                    case 4:
                        List<Student> students = studentService.getAllStudents();
                        System.out.println("\nAll Students:");
                        for (Student student : students) {
                            System.out.println(student);
                        }
                        break;
                    case 5:
                        int idToSearch = Utils.getIntInput("Enter student ID to search: ");
                        Student student = studentService.getStudentById(idToSearch);
                        if (student != null) {
                            System.out.println(student);
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;
                    case 6:
                        System.out.println("1. Sort by First Name");
                        System.out.println("2. Sort by Last Name");
                        System.out.println("3. Sort by Age");
                        System.out.println("4. Sort by Grade");
                        int sortChoice = Utils.getIntInput("Enter your choice: ");
                        students = studentService.getAllStudents();
                        switch (sortChoice) {
                            case 1:
                                students.sort(Comparator.comparing(Student::getFirstName));
                                break;
                            case 2:
                                students.sort(Comparator.comparing(Student::getLastName));
                                break;
                            case 3:
                                students.sort(Comparator.comparing(Student::getAge));
                                break;
                            case 4:
                                students.sort(Comparator.comparing(Student::getGrade));
                                break;
                            default:
                                System.out.println("Invalid choice.");
                                continue;
                        }
                        System.out.println("\nSorted Students:");
                        for (Student sortedStudent : students) {
                            System.out.println(sortedStudent);
                        }
                        break;
                    case 7:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                dbManager.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

