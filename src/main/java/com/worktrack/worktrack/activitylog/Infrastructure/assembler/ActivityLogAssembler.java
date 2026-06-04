package com.worktrack.worktrack.activitylog.Infrastructure.assembler;

import com.worktrack.worktrack.activitylog.domain.model.ActivityLog;
import com.worktrack.worktrack.activitylog.dto.ActivityLogResponseDto;
import com.worktrack.worktrack.project.domain.dtoPU.ProjectUserRequestDto;
import com.worktrack.worktrack.project.domain.dtoPU.ProjectUserResponseDto;
import com.worktrack.worktrack.project.domain.model.Project;
import com.worktrack.worktrack.project.dto.ProjectRequestDto;
import com.worktrack.worktrack.project.dto.ProjectResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ActivityLogAssembler {

    public ActivityLog assemble(ProjectUserRequestDto projectUserRequestDto) {
        ActivityLog activityLog = new ActivityLog();
        BeanUtils.copyProperties(projectUserRequestDto, activityLog);
        return activityLog;

    }
    public ActivityLogResponseDto assemble(ActivityLog model) {

        ActivityLogResponseDto activityLogResponseDto = new ActivityLogResponseDto();
        BeanUtils.copyProperties(model, activityLogResponseDto);

        if(model.getUserId() != null){
            activityLogResponseDto.setUserId(model.getUserId().getId());
        }

        return activityLogResponseDto;

    }



}
