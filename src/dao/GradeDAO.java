
package org.example.dao;

import org.example.model.Grade;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GradeDAO {


    public void save(Grade grade) {

        Transaction transaction = null;

        try (Session session = HibernateUtil
                .getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(grade);

            transaction.commit();

            System.out.println("Grade saved successfully!");

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw new RuntimeException("Error saving grade", e);
        }
    }


    public List<Grade> findAll() {

        try (Session session = HibernateUtil
                .getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM Grade", Grade.class
            ).list();
        }
    }


    public Grade findById(Long id) {

        try (Session session = HibernateUtil
                .getSessionFactory().openSession()) {

            return session.find(Grade.class, id);
        }
    }


    public List<Grade> findByStudentId(Long studentId) {

        try (Session session = HibernateUtil
                .getSessionFactory().openSession()) {

            return session.createQuery(
                            "FROM Grade g WHERE g.student.id = :studentId",
                            Grade.class
                    )
                    .setParameter("studentId", studentId)
                    .list();
        }
    }


    public void update(Grade grade) {

        Transaction transaction = null;

        try (Session session = HibernateUtil
                .getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.merge(grade);

            transaction.commit();

            System.out.println("Grade updated successfully!");

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw new RuntimeException("Error updating grade", e);
        }
    }


    public void delete(Long id) {

        Transaction transaction = null;

        try (Session session = HibernateUtil
                .getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Grade grade = session.find(Grade.class, id);

            if (grade != null) {
                session.remove(grade);
            }

            transaction.commit();

            System.out.println("Grade deleted successfully!");

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw new RuntimeException("Error deleting grade", e);
        }
    }
}
