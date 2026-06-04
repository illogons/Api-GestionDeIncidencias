package com.worktrack.worktrack.project.domain.respository;

import com.worktrack.worktrack.project.domain.model.Project;
import com.worktrack.worktrack.ticket.domain.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    Optional<Project> findByName(String name);
}
