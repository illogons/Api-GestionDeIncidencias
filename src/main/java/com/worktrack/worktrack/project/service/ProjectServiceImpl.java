package com.worktrack.worktrack.project.service;

import com.worktrack.worktrack.project.Infrastructure.assembler.ProjectAssembler;
import com.worktrack.worktrack.project.domain.enums.ProjectStatus;
import com.worktrack.worktrack.project.domain.respository.ProjectRepository;
import com.worktrack.worktrack.project.dto.ProjectRequestDto;
import com.worktrack.worktrack.project.dto.ProjectResponseDto;
import com.worktrack.worktrack.project.domain.model.Project;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProjectServiceImpl implements projectService {

    private final ProjectRepository projectRepository;
    private final ProjectAssembler projectAssembler;

    @Override
    public List<ProjectResponseDto> getProjects() {
        log.info("Getting all projects");
        List<ProjectResponseDto> projects = projectRepository.findAll()
                .stream()
                .map(projectAssembler::toDto)
                .toList();
        log.info("Got {} projects successfully", projects.size());
        return projects;
    }

    @Override
    public ProjectResponseDto getProjectById(Long id) {
        log.info("Getting project by id: {}", id);
        Project project = projectRepository.findById(id)
                .orElseThrow(WrongThreadException::new);
        log.info("Got project successfully");
        return projectAssembler.toDto(project);
    }

    @Override
    public ProjectResponseDto createProject(ProjectRequestDto dto) {
        log.info("Creating project: {}", dto);

        Project model = projectAssembler.toModel(dto);
        Project saved = projectRepository.save(model);

        log.info("Created project successfully");
        return projectAssembler.toDto(saved);
    }

    @Override
    public ProjectResponseDto updateProject(Long id, ProjectRequestDto dto) {
        log.info("Updating project with id: {}", id);

        Project project = projectRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Project not found with ID: " + dto.getProjectStatus()));

        projectAssembler.toUpdate(dto, project);
        Project saved = projectRepository.save(project);
        log.info("Updated project successfully");
        return projectAssembler.toDto(saved);
    }

    @Override
    public void deleteProject(Long id, ProjectRequestDto dto) {
        log.info("Deleting project with id: {}", id);
        Project project = projectRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Ticket not found"));

        project.setProjectStatus(ProjectStatus.BLOCKED);
        projectRepository.save(project);
        log.info("Deleted project successfully");
    }
}