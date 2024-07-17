package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class StudentService {
    private DatabaseManager dbManager;

    public StudentService(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void addStudent(String firstName, String lastName, int age, float grade) throws SQLException {
        String query = "INSERT INTO student (first_name, last_name, age, grade) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = dbManager.getConnection().prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setInt(3, age);
            stmt.setFloat(4, grade);
            stmt.executeUpdate();
        }
    }

    public void updateStudent(int id, String firstName, String lastName, int age, float grade) throws SQLException {
        String query = "UPDATE student SET first_name = ?, last_name = ?, age = ?, grade = ? WHERE id = ?";
        try (PreparedStatement stmt = dbManager.getConnection().prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setInt(3, age);
            stmt.setFloat(4, grade);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        }
    }

    public void deleteStudent(int id) throws SQLException {
        String query = "DELETE FROM student WHERE id = ?";
        try (PreparedStatement stmt = dbManager.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        String query = "SELECT * FROM student";
        List<Student> students = new ArrayList<>();
        try (Statement stmt = dbManager.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("age"),
                        rs.getFloat("grade")
                ));
            }
        }
        return students;
    }

    public Student getStudentById(int id) throws SQLException {
        String query = "SELECT * FROM student WHERE id = ?";
        try (PreparedStatement stmt = dbManager.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getInt("age"),
                            rs.getFloat("grade")
                    );
                }
            }
        }
        return null;
    }
}
