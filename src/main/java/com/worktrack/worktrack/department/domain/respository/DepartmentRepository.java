package com.worktrack.worktrack.department.domain.respository;

import com.worktrack.worktrack.department.domain.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

    Optional<Department> findByName(String name);
}
