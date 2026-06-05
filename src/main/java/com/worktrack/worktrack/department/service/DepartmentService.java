package com.worktrack.worktrack.department.service;

import com.worktrack.worktrack.department.dto.DepartmentRequestDto;
import com.worktrack.worktrack.department.dto.DepartmentResponseDto;

import java.util.List;

public interface DepartmentService {
     List<DepartmentResponseDto> getDepartments();
     DepartmentResponseDto getDepartmentById(Long id);
     DepartmentResponseDto createDepartment(DepartmentRequestDto dto);
     DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto dto);
     void deleteDepartment(Long id);




    }
