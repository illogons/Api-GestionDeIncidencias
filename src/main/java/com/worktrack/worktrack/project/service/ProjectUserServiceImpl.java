package com.worktrack.worktrack.project.service;

import com.worktrack.worktrack.project.Infrastructure.assembler.ProjectUserAssembler;
import com.worktrack.worktrack.project.domain.dtoPU.ProjectUserRequestDto;
import com.worktrack.worktrack.project.domain.dtoPU.ProjectUserResponseDto;
import com.worktrack.worktrack.project.domain.model.Project;
import com.worktrack.worktrack.project.domain.model.ProjectUser;
import com.worktrack.worktrack.project.domain.respository.ProjectRepository;
import com.worktrack.worktrack.project.domain.respository.ProjectUserRepository;
import com.worktrack.worktrack.user.domain.model.User;
import com.worktrack.worktrack.user.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectUserServiceImpl implements ProjectUserService {

    private final ProjectUserRepository projectUserRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectUserAssembler projectUserAssembler;


    @Override
    @Transactional
    public ProjectUserResponseDto assignUserToProject(Long projectId, Long userId, ProjectUserRequestDto dto) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(()-> new RuntimeException("project not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("user not found"));

        ProjectUser projectuser = ProjectUser.builder()
                .userId(user)
                .projectId(project)
                .description(dto.getDescription())
                .build();

        return projectUserAssembler.toDto(projectUserRepository.save(projectuser));
    }

    @Override
    @Transactional
    public List<ProjectUserResponseDto> getUsersByProject(Long projectId) {

            Project project = projectRepository.findById(projectId)
                    .orElseThrow(()-> new RuntimeException("user not found"));

            return projectUserRepository.findUsersByProjectId(project).stream().map(projectUserAssembler::toDto)
                    .toList();

    }

    @Override
    public void removeUserFromProject(Long projectId, Long userId) {
        log.debug("Removing user from project");

        Project project = projectRepository.findById(projectId)
                .orElseThrow(()-> new RuntimeException("user not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("user not found"));

        ProjectUser c = projectUserRepository.findByProjectIdAndUserId(project,user)
                .orElseThrow(()-> new RuntimeException("user not found"));

        c.setActive(false);
        projectUserRepository.save(c);
        log.info("user has been removed");

    }

    @Override
    public List<ProjectUserResponseDto> getProjectsByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("user not found"));

        return projectUserRepository.findUsersByProject(user).stream().map(projectUserAssembler::toDto)
                .toList();
    }
}
