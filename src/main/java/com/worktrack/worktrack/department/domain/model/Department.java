package com.worktrack.worktrack.department.domain.model;

import com.worktrack.worktrack.user.domain.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name= "department")
public class Department {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="ACTIVE",nullable = false)
    private Boolean active;

    @Column(name="DESCRIPTION",  columnDefinition = "TEXT")
    private String description;

    @PrePersist
    public void prePersist() {
        this.active = true;
    }

    @OneToMany(mappedBy = "departmentId")
    private List<User> users;
}
