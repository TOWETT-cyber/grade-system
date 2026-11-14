
package org.example.dao;

import org.example.model.Student;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAO {

    // CREATE: Save a student
    public void save(Student student) {
        Transaction transaction = null;

        try (Session session = HibernateUtil
                .getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(student);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Could not save student", e);
        }
    }

    // READ: Get all students
    public List<Student> findAll() {
        try (Session session = HibernateUtil
                .getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM Student", Student.class
            ).list();
        }
    }

    // READ: Find student by ID
    public Student findById(Long id) {
        try (Session session = HibernateUtil
                .getSessionFactory().openSession()) {

            return session.find(Student.class, id);
        }
    }

    // UPDATE: Update student details
    public void update(Student student) {
        Transaction transaction = null;

        try (Session session = HibernateUtil
                .getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.merge(student);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Could not update student", e);
        }
    }

    // DELETE: Delete student by ID
    public void delete(Long id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil
                .getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Student student = session.find(Student.class, id);

            if (student != null) {
                session.remove(student);
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Could not delete student", e);
        }
    }
}

