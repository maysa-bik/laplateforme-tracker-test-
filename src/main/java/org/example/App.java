package org.example;


import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

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
                System.out.println("7. Advanced Search");
                System.out.println("8. Display Statistics");
                System.out.println("9. Display Students with Pagination");
                System.out.println("10. Exit");
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
                        System.out.println("1. Search by Age");
                        System.out.println("2. Search by Grade");
                        int searchChoice = Utils.getIntInput("Enter your choice: ");
                        switch (searchChoice) {
                            case 1:
                                int âge = Utils.getIntInput("Enter age: ");
                                List<Student> studentsByAge = studentService.getStudentsByAge(âge);
                                for (Student Student : studentsByAge) {
                                    System.out.println(Student);
                                }
                                break;
                            case 2:
                                float Grade = Utils.getFloatInput("Enter grade: ");
                                List<Student> studentsByGrade = studentService.getStudentsByGrade(Grade);
                                for (Student Student : studentsByGrade) {
                                    System.out.println(Student);
                                }
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                        break;
                    case 8:
                        float avgGrade = studentService.getAverageGrade();
                        System.out.println("Average grade: " + avgGrade);
                        // Ajouter d'autres statistiques si nécessaire
                        break;
                    case 9:
                        int pageSize = Utils.getIntInput("Enter page size: ");
                        int pageNumber = 1;
                        List<Student> paginatedStudents;
                        do {
                            paginatedStudents = studentService.getStudentsWithPagination(pageNumber, pageSize);
                            for (Student Student : paginatedStudents) {
                                System.out.println(Student);
                            }
                            if (!paginatedStudents.isEmpty()) {
                                pageNumber++;
                                System.out.println("\nPress Enter to view next page or type 'exit' to go back.");
                                String input = new Scanner(System.in).nextLine();
                                if (input.equalsIgnoreCase("exit")) {
                                    break;
                                }
                            }
                        } while (!paginatedStudents.isEmpty());
                        break;
                    case 10:
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
