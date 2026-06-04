package com.worktrack.worktrack.activitylog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLogResponseDto {

    private String action;

    private String entityType;

    private LocalDateTime date;

    private Long entityId;

    private Long userId;
}
