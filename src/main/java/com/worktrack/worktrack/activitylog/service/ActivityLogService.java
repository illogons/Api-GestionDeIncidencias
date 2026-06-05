package com.worktrack.worktrack.activitylog.service;

import com.worktrack.worktrack.activitylog.dto.ActivityLogRequestDto;
import com.worktrack.worktrack.activitylog.dto.ActivityLogResponseDto;

import java.util.List;

public interface ActivityLogService {

     List<ActivityLogResponseDto> getLogsByUser(Long userId);
     ActivityLogResponseDto registerLog(ActivityLogRequestDto dto);
     List<ActivityLogResponseDto> getAllLogs();



    }
