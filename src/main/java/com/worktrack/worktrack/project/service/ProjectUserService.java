package com.worktrack.worktrack.project.service;

import com.worktrack.worktrack.project.domain.dtoPU.ProjectUserRequestDto;
import com.worktrack.worktrack.project.domain.dtoPU.ProjectUserResponseDto;
import com.worktrack.worktrack.user.dto.UserResponseDto;

import java.util.List;

public interface ProjectUserService {

    ProjectUserResponseDto assignUserToProject(Long projectId, Long userId, ProjectUserRequestDto dto);
    List<ProjectUserResponseDto> getUsersByProject(Long projectId);
    void removeUserFromProject(Long projectId, Long userId);
    List<ProjectUserResponseDto> getProjectsByUser(Long userId);




}
