package com.worktrack.worktrack.project.service;

import com.worktrack.worktrack.project.dto.ProjectRequestDto;
import com.worktrack.worktrack.project.dto.ProjectResponseDto;


import java.util.List;

public interface projectService {

    List<ProjectResponseDto> getProjects();
    ProjectResponseDto getProjectById(Long Id);
    ProjectResponseDto createProject(ProjectRequestDto user);
    ProjectResponseDto updateProject(Long Id, ProjectRequestDto user);
    void deleteProject(Long Id, ProjectRequestDto user);
}
