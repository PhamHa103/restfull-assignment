package repository;

import entity.Student;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

public class StudentRepository {

    public List<Student> getAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from Student", Student.class).list();
    }

    public Student getOneById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from Student where id = :p_id", Student.class)
                .setParameter("p_id",id)
                .getSingleResult();
    }

    public boolean insert(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
