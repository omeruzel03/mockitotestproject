package com.ceng557.assignment.modules.service;

import com.ceng557.assignment.modules.entity.Student;

import java.util.List;

public interface StudentService {
    Student getByNumber(String number);

    List<Student> getStudentList();

    Student save(Student student) throws IllegalArgumentException;

    Student save(String studentNumber, String firstName, String lastName);
}
