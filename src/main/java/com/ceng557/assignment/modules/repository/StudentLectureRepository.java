package com.ceng557.assignment.modules.repository;

import com.ceng557.assignment.modules.entity.StudentLecture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentLectureRepository extends CrudRepository<StudentLecture, Long> {



}
