package com.ceng557.assignment.modules.service.impl;

import com.ceng557.assignment.modules.entity.Student;
import com.ceng557.assignment.modules.repository.StudentRepository;
import com.ceng557.assignment.modules.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public Student getByNumber(String number) {
        return studentRepository.getStudentByNumber(number);
    }

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudentList() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student save(String studentNumber, String firstName, String lastName) {
        return null;
    }
}
