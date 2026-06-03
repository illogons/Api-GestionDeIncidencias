package com.worktrack.worktrack.activitylog.dto;

import com.worktrack.worktrack.user.domain.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLogRequestDto {

    private Long id;

    private String action;

    private String entityType;

    private Long entityId;

    private LocalDateTime date;

    private Long userId;

}
