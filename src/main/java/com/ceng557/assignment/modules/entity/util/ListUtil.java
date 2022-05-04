package com.ceng557.assignment.modules.entity.util;

import com.ceng557.assignment.modules.entity.Student;

import java.util.List;
import java.util.stream.Collectors;

public class ListUtil {
    public static List<Student> filterGraduated(Iterable<Student> students) {
        return ((List<Student>) students).stream().filter(Student::getGraduated).collect(Collectors.toList());
    }
}
