package com.ceng557.assignment.modules.repository;

import com.ceng557.assignment.modules.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

    Department findFirstByNameContains(String name);

}
