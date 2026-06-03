package com.worktrack.worktrack.project.domain.model;

import com.worktrack.worktrack.user.domain.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name= "ProjectUser")
public class ProjectUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="LAUNCH_DATE",nullable = false)
    private LocalDateTime launchDate;

    @Column(name="ACTIVE",nullable = false)
    private Boolean active;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn( name= "project_id")
    private Project projectId;

    @ManyToOne
    @JoinColumn( name= "user_id")
    private User userId;


}
