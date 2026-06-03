package com.worktrack.worktrack.user.domain.model;

import com.worktrack.worktrack.user.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data//sustituye a getterysetter
@Builder// sin orden exacta de los parametros
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column( nullable = false, unique = true)
    private String email;

    @Column( nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Role role;

    @Column(nullable = false)
    private Boolean active;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist// se ejecuta antes de guardar nada, asi si se olvida el usuario se auto rellena
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.active = true;
    }

}
