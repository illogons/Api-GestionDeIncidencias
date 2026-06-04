package com.worktrack.worktrack.project.domain.dtoPU;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUserResponseDto {

    private Long Id;

    private Long projectId;
    private Long userId;

    private String description;


}
