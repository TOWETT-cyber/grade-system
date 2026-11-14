package org.example;
import org.example.model.Student;
import org.example.model.Subject;
import org.example.model.Grade;
import org.example.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

    public static void main(String[] args) {

        Transaction transaction = null;

        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {


            transaction = session.beginTransaction();


            Student student = new Student(
                    "Benson",
                    "Cheruiyot",
                    "benson@gmail.com"
            );


            Subject subject = new Subject(
                    "Mathematics",
                    "Mathematics examination"
            );

            session.persist(student);
            session.persist(subject);


            Grade grade = new Grade(
                    85.0,
                    "Final Examination",
                    student,
                    subject
            );


            session.persist(grade);


            transaction.commit();


            System.out.println("STUDENT GRADE MANAGEMENT SYSTEM");


            System.out.println("Student: "
                    + student.getFirstName() + " "
                    + student.getLastName());

            System.out.println("Subject: "
                    + subject.getName());

            System.out.println("Marks: "
                    + grade.getMarks());

            System.out.println("Records saved successfully!");

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            e.printStackTrace();

        } finally {

            HibernateUtil.shutdown();

        }
    }
}
