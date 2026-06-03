package com.worktrack.worktrack.project.domain.model;

import com.worktrack.worktrack.project.domain.enums.ProjectStatus;
import com.worktrack.worktrack.ticket.domain.model.Ticket;
import com.worktrack.worktrack.user.domain.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name= "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "NAME", nullable = false, unique = true)
    private String name;

    @Column(name= "DESCRIPTION",  columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROJECT_STATUS",nullable = false)
    private ProjectStatus projectStatus;

    @Column(name= "START_DATE", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @Column(name= "END_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    @PrePersist
    public void prePersist() {
        this.startDate = LocalDateTime.now();
    }

   @OneToMany(mappedBy = "projectId")
   private List<ProjectUser> projectUsers;

    @OneToMany(mappedBy = "projectId")
    private List<Ticket> tickets;











}

