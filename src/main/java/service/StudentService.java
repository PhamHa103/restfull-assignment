package service;

import entity.Student;
import repository.StudentRepository;

import java.util.Date;
import java.util.List;

public class StudentService {

    private StudentRepository studentRepository = new StudentRepository();
    public List<Student> getAll(String fullName, Date startDate, Date endDate, String gender, String hometown, String className, String major, Float averageMarkMin, Float averageMarkMax){

        return studentRepository.getAll(fullName, startDate, endDate, gender, hometown,className, major, averageMarkMin, averageMarkMax);
    }

    public Student getOneById(int id) {
        return studentRepository.getOneById(id);
    }

    public boolean insert(Student student) {
        return studentRepository.insert(student);
    }

    public boolean update(Student student) {
        return studentRepository.update(student);
    }

    public boolean removeStudent(int id) {
        return studentRepository.removeStudent(id);
    }
}
