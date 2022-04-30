package com.ceng557.assignment.modules.service.impl;

import com.ceng557.assignment.modules.entity.Teacher;
import com.ceng557.assignment.modules.repository.TeacherRepository;
import com.ceng557.assignment.modules.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public Teacher getTeacherByNumber(String number) {
        return teacherRepository.findByNumber(number);
    }

}
