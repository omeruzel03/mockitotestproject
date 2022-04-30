package com.ceng557.assignment.modules.repository;

import com.ceng557.assignment.modules.entity.TeacherLecture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherLectureRepository extends CrudRepository<TeacherLecture, Long> {



}
