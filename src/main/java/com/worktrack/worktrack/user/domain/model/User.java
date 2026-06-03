package com.worktrack.worktrack.user.domain.model;

import com.worktrack.worktrack.activitylog.domain.model.ActivityLog;
import com.worktrack.worktrack.department.domain.model.Department;
import com.worktrack.worktrack.project.domain.model.Project;
import com.worktrack.worktrack.project.domain.model.ProjectUser;
import com.worktrack.worktrack.user.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users",uniqueConstraints = @UniqueConstraint(columnNames ={"email"} ))
@Data//sustituye a getterysetter
@Builder// sin orden exacta de los parametros
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NAME",nullable = false)
    private String name;

    @Column(name="EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name="PASSWORD", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="ROLE",nullable = false)
    protected Role role;

    @Column(name="ACTIVE",nullable = false)
    private Boolean active;

    @Column(name="CREATED_AT",updatable = false)
    private LocalDateTime createdAt;

    @PrePersist// se ejecuta antes de guardar nada, asi si se olvida el usuario se auto rellena
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.active = true;
    }

    @ManyToOne
    @JoinColumn( name = "department_id")
    private Department departmentId;

    @OneToMany(mappedBy = "userId")
    private List<ActivityLog> activityLogs;

    @OneToMany(mappedBy = "userId")
    private List<ProjectUser> projectUsers;
}
