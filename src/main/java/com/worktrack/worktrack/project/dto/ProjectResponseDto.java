package com.worktrack.worktrack.project.dto;

import com.worktrack.worktrack.project.domain.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseDto {

    private Long id;

    private String name;

    private String description;

    private ProjectStatus projectStatus;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
