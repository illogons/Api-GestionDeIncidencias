package com.worktrack.worktrack.project.Infrastructure.assembler;

import com.worktrack.worktrack.project.domain.model.Project;
import com.worktrack.worktrack.project.dto.ProjectRequestDto;
import com.worktrack.worktrack.project.dto.ProjectResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProjectAssembler {

    public Project toModel(ProjectRequestDto dto ){
        Project project = new Project();
        BeanUtils.copyProperties(dto, project);
        return project;
    }
    public void toUpdate(ProjectRequestDto dto, Project project){
        BeanUtils.copyProperties(dto, project);
    }
    public ProjectResponseDto toDto(Project project){

        ProjectResponseDto projectResponseDto = new ProjectResponseDto();
        BeanUtils.copyProperties(project, projectResponseDto);
        return projectResponseDto;

    }
}
