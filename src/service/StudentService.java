
package org.example.service;

import org.example.dao.StudentDAO;
import org.example.model.Student;

import java.util.List;

public class StudentService {

    private final StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    // Register a student
    public void saveStudent(Student student) {

        if (student == null) {
            throw new IllegalArgumentException("Student cannot be empty");
        }

        if (student.getFirstName() == null ||
                student.getFirstName().isBlank()) {
            throw new IllegalArgumentException("First name is required");
        }

        if (student.getLastName() == null ||
                student.getLastName().isBlank()) {
            throw new IllegalArgumentException("Last name is required");
        }

        if (student.getEmail() == null ||
                !student.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Enter a valid email");
        }

        studentDAO.save(student);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    // Find student by ID
    public Student getStudentById(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid student ID");
        }

        Student student = studentDAO.findById(id);

        if (student == null) {
            throw new RuntimeException("Student not found");
        }

        return student;
    }

    // Update student
    public void updateStudent(Student student) {

        if (student == null || student.getId() == null) {
            throw new IllegalArgumentException("Student ID is required");
        }

        getStudentById(student.getId());

        studentDAO.update(student);
    }

    // Delete student
    public void deleteStudent(Long id) {

        getStudentById(id);

        studentDAO.delete(id);
    }
}
