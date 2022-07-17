package service;

import entity.Student;
import repository.StudentRepository;

import java.util.List;

public class StudentService {

    private StudentRepository studentRepository = new StudentRepository();
    public List<Student> getAll(){

        return studentRepository.getAll();
    }

    public Student getOneById(int id) {
        return studentRepository.getOneById(id);
    }

    public boolean insert(Student student) {
        return studentRepository.insert(student);
    }
}
