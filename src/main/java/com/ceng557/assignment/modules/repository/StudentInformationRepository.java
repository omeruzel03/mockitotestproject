package com.ceng557.assignment.modules.repository;

import com.ceng557.assignment.modules.entity.StudentInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentInformationRepository extends CrudRepository<StudentInformation, Long> {


}
