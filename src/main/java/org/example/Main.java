package org.example;

import java.sql.SQLException;
import java.util.List;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
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
                System.out.println("6. Exit");
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
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
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