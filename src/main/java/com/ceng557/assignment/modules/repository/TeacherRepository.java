package com.ceng557.assignment.modules.repository;

import com.ceng557.assignment.modules.entity.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {



}
