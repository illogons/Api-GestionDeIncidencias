package com.worktrack.worktrack.project.domain.respository;

import com.worktrack.worktrack.project.domain.dtoPU.ProjectUserResponseDto;
import com.worktrack.worktrack.project.domain.model.Project;
import com.worktrack.worktrack.project.domain.model.ProjectUser;
import com.worktrack.worktrack.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectUserRepository extends JpaRepository<ProjectUser,Long> {


    List<ProjectUser> findUsersByProject(User user);
    List<ProjectUser> findUsersByProjectId(Project project);
    Optional<ProjectUser> findByProjectIdAndUserId(Project project, User user);}
