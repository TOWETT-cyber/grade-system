
package org.example.controller;

import org.example.model.Student;
import org.example.service.StudentService;

import java.util.List;

public class StudentController {

    private final StudentService studentService;

    // Constructor injection
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Register a new student
    public void addStudent(Student student) {
        studentService.saveStudent(student);
        System.out.println("Student registered successfully!");
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Find student by ID
    public Student getStudentById(Long id) {
        return studentService.getStudentById(id);
    }

    // Update student
    public void updateStudent(Student student) {
        studentService.updateStudent(student);
        System.out.println("Student updated successfully!");
    }

    // Delete student
    public void deleteStudent(Long id) {
        studentService.deleteStudent(id);
        System.out.println("Student deleted successfully!");
    }
}
