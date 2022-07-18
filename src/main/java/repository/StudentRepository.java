package repository;

import entity.Student;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.Date;
import java.util.List;

public class StudentRepository {

    Logger logger = Logger.getLogger(StudentRepository.class);

    public List<Student> getAll(String fullName, Date startDate, Date endDate, String gender, String hometown, String className, String major, Float averageMarkMin, Float averageMarkMax) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String queryString ="select s from Student s";
            if (fullName != null&& !fullName.equals("")) {
                queryString += " where lower( s.fullName) like :fullName";
            }
            if (startDate != null){
                if (queryString =="select s from Student s")
                queryString += " where s.birthday >= :startDate";
                else
                queryString += " and s.birthday >= :startDate";
            }
            if (endDate != null){
                if (queryString =="select s from Student s")
                    queryString += " where s.birthday <= :endDate";
                else
                    queryString += " and s.birthday <= :endDate";
            }
            if (gender != null && !gender.equals("")){
                if (queryString =="select s from Student s")
                    queryString += " where s.gender = :gender";
                else
                    queryString += " and s.gender = :gender";
            }
            if (hometown != null && !hometown.equals("")){
                if (queryString =="select s from Student s")
                    queryString += " where s.hometown = :hometown";
                else
                    queryString += " and s.hometown = :hometown";
            }
            if (className != null && !className.equals("")){
                if (queryString =="select s from Student s")
                    queryString += " where s.className = :className";
                else
                    queryString += " and s.className = :className";
            }
            if (major != null && !major.equals("")){
                if (queryString =="select s from Student s")
                    queryString += " where lower s.major = :major";
                else
                    queryString += " and lower s.major = :major";
            }
            if (averageMarkMin != null){
                if (queryString =="select s from Student s")
                    queryString += " where  s.averageMark >= :averageMarkMin";
                else
                    queryString += " and s.averageMark >= :averageMarkMin";
            }
            if (averageMarkMax != null){
                if (queryString =="select s from Student s")
                    queryString += " where  s.averageMark <= :averageMarkMax";
                else
                    queryString += " and s.averageMark <= :averageMarkMax";
            }

            Query query = session.createQuery(queryString, Student.class);

            if (fullName != null&& !fullName.equals("")){
                query.setParameter("fullName", "%" + fullName.toLowerCase() + "%");
            }
            if (startDate != null)
                query.setParameter("startDate", startDate );
            if (endDate != null)
                query.setParameter("endDate", endDate );
            if (gender != null && !gender.equals("")){
                query.setParameter("gender",  gender);
            }
            if (hometown != null && !hometown.equals("")){
                query.setParameter("hometown",  hometown);
            }
            if (className != null && !className.equals("")){
                query.setParameter("className", className);
            }
            if (major != null && !major.equals("")){
                query.setParameter("major", major );
            }
            if (averageMarkMin != null){
                query.setParameter("averageMarkMin", averageMarkMin );
            }
            if (averageMarkMax != null){
                query.setParameter("averageMarkMax", averageMarkMax );
            }

            return query.list();

        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return null;
    }

    public Student getOneById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Student> query = session.createQuery("select s from Student s where s.id = :p_student_id");
            query.setParameter("p_student_id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return null;
    }

    public boolean insert(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public boolean update(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public boolean removeStudent(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Student student = session.load(Student.class, id);
            session.delete(student);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            logger.error(e);
        } finally {
            session.close();
        }
        return false;
    }


}
