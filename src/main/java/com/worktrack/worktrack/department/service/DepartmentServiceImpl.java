package com.worktrack.worktrack.department.service;

import com.worktrack.worktrack.department.Infrastructure.assembler.DepartmentAssembler;
import com.worktrack.worktrack.department.domain.model.Department;
import com.worktrack.worktrack.department.domain.respository.DepartmentRepository;
import com.worktrack.worktrack.department.dto.DepartmentRequestDto;
import com.worktrack.worktrack.department.dto.DepartmentResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentAssembler departmentAssembler;

    @Override
    @Transactional
    public List<DepartmentResponseDto> getDepartments() {
        log.info("Getting all departments");

        return departmentRepository.findAll()
                .stream()
                .map(departmentAssembler::toDto)
                .toList();
    }

    @Override
    @Transactional
    public DepartmentResponseDto getDepartmentById(Long id) {
        log.info("Getting department by id: {}", id);

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + id));

        return departmentAssembler.toDto(department);
    }

    @Override
    @Transactional
    public DepartmentResponseDto createDepartment(DepartmentRequestDto dto) {
        log.info("Creating department: {}", dto);

        Department model = departmentAssembler.toModel(dto);
        Department saved = departmentRepository.save(model);

        log.info("Created department successfully");
        return departmentAssembler.toDto(saved);
    }

    @Override
    @Transactional
    public DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto dto) {
        log.info("Updating department with id: {}", id);
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + id));
        departmentAssembler.toUpdate(dto, department);
        Department saved = departmentRepository.save(department);
        log.info("Updated department successfully");
        return departmentAssembler.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteDepartment(Long id) {
        log.info("Deleting department with id: {}", id);
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + id));
        department.setActive(false);
        departmentRepository.save(department);
        log.info("Deleted department successfully");
    }
}