
package org.example.controller;

import org.example.model.Grade;
import org.example.service.GradeService;

import java.util.List;

public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    // Register a grade
    public void addGrade(Grade grade) {
        gradeService.saveGrade(grade);
        System.out.println("Grade saved successfully!");
    }

    // Display all grades
    public List<Grade> getAllGrades() {
        return gradeService.getAllGrades();
    }

    // Find grade by ID
    public Grade getGradeById(Long id) {
        return gradeService.getGradeById(id);
    }

    // Update grade
    public void updateGrade(Grade grade) {
        gradeService.updateGrade(grade);
        System.out.println("Grade updated successfully!");
    }

    // Delete grade
    public void deleteGrade(Long id) {
        gradeService.deleteGrade(id);
        System.out.println("Grade deleted successfully!");
    }

    // Calculate letter grade
    public String calculateGrade(double marks) {
        return gradeService.calculateGrade(marks);
    }
}
