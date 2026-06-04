package com.worktrack.worktrack.department.Infrastructure.assembler;

import com.worktrack.worktrack.department.domain.model.Department;
import com.worktrack.worktrack.department.dto.DepartmentRequestDto;
import com.worktrack.worktrack.department.dto.DepartmentResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DepartmentAssembler {

    public Department toModel(DepartmentRequestDto dto){

        Department department = new Department();
        BeanUtils.copyProperties(dto, department);
        return department;

    }

    public void toUpdate(DepartmentRequestDto dto, Department department){
        BeanUtils.copyProperties(dto, department);
    }

    public DepartmentResponseDto toDto(Department model){
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
        BeanUtils.copyProperties(model, departmentResponseDto);
        return departmentResponseDto;
    }
}
