
package org.example.service;

import org.example.dao.GradeDAO;
import org.example.model.Grade;

import java.util.List;

public class GradeService {

    private final GradeDAO gradeDAO;

    public GradeService(GradeDAO gradeDAO) {
        this.gradeDAO = gradeDAO;
    }


    public void saveGrade(Grade grade) {

        if (grade == null) {
            throw new IllegalArgumentException("Grade cannot be empty");
        }

        if (grade.getMarks() < 0 || grade.getMarks() > 100) {
            throw new IllegalArgumentException(
                    "Marks must be between 0 and 100"
            );
        }

        if (grade.getStudent() == null) {
            throw new IllegalArgumentException("Select a student");
        }

        if (grade.getSubject() == null) {
            throw new IllegalArgumentException("Select a subject");
        }

        gradeDAO.save(grade);
    }


    public List<Grade> getAllGrades() {
        return gradeDAO.findAll();
    }


    public Grade getGradeById(Long id) {

        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid grade ID");
        }

        Grade grade = gradeDAO.findById(id);

        if (grade == null) {
            throw new RuntimeException("Grade not found");
        }

        return grade;
    }


    public void updateGrade(Grade grade) {

        if (grade == null || grade.getId() == null) {
            throw new IllegalArgumentException("Grade ID is required");
        }

        if (grade.getMarks() < 0 || grade.getMarks() > 100) {
            throw new IllegalArgumentException(
                    "Marks must be between 0 and 100"
            );
        }

        getGradeById(grade.getId());

        gradeDAO.update(grade);
    }


    public void deleteGrade(Long id) {
        getGradeById(id);
        gradeDAO.delete(id);
    }


    public String calculateGrade(double marks) {

        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException(
                    "Marks must be between 0 and 100"
            );
        }

        if (marks >= 80) return "A";
        if (marks >= 70) return "B";
        if (marks >= 60) return "C";
        if (marks >= 50) return "D";

        return "E";
    }
}
