package com.worktrack.worktrack.activitylog.Infrastructure.assembler;

import com.worktrack.worktrack.activitylog.domain.model.ActivityLog;
import com.worktrack.worktrack.activitylog.dto.ActivityLogRequestDto;
import com.worktrack.worktrack.activitylog.dto.ActivityLogResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ActivityLogAssembler {

    public ActivityLog toModel(ActivityLogRequestDto projectUserRequestDto) {
        ActivityLog activityLog = new ActivityLog();
        BeanUtils.copyProperties(projectUserRequestDto, activityLog);
        return activityLog;

    }

    public ActivityLogResponseDto toDto(ActivityLog model) {

        ActivityLogResponseDto activityLogResponseDto = new ActivityLogResponseDto();
        BeanUtils.copyProperties(model, activityLogResponseDto);

        if(model.getUserId() != null){
            activityLogResponseDto.setUserId(model.getUserId().getId());
        }

        return activityLogResponseDto;

    }



}
