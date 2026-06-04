package com.worktrack.worktrack.project.domain.respository;

import com.worktrack.worktrack.project.domain.model.Project;
import com.worktrack.worktrack.project.domain.model.ProjectUser;
import com.worktrack.worktrack.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectUserRepository extends JpaRepository<ProjectUser,Long> {

    Optional<ProjectUser> findByProjectAndUser(Project project, User user);
    Optional<ProjectUser> findByUserId(Long aLong);
}
