package com.worktrack.worktrack.project.Infrastructure.assembler;

import com.worktrack.worktrack.project.domain.dtoPU.ProjectUserRequestDto;
import com.worktrack.worktrack.project.domain.dtoPU.ProjectUserResponseDto;
import com.worktrack.worktrack.project.domain.model.ProjectUser;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProjectUserAssembler {

    public ProjectUser toModel(ProjectUserRequestDto dto ){
        ProjectUser project = new ProjectUser();
        BeanUtils.copyProperties(dto, project);
        return project;
    }
    public void toUpdate(ProjectUserRequestDto dto, ProjectUser project){
        BeanUtils.copyProperties(dto, project);
    }
    public ProjectUserResponseDto toDto(ProjectUser model){

        ProjectUserResponseDto projectuserResponseDto = new ProjectUserResponseDto();
        BeanUtils.copyProperties(model, projectuserResponseDto);

        if(model.getProjectId() != null){
            projectuserResponseDto.setProjectId(model.getProjectId().getId());
        }
        if(model.getUserId() != null){
            projectuserResponseDto.setUserId(model.getUserId().getId());
        }

        return projectuserResponseDto;
    }
}
