package com.worktrack.worktrack.user.dto;

import com.worktrack.worktrack.user.domain.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {


    @NotBlank(message = "must to have a name")
    private String name;

    @NotBlank( message = "must to have a pass")
    private String password;

    @NotBlank( message = "must to have a email")
    private String email;

    @NotNull(message = "role date cannot be null")
    private Role role;

    @NotNull(message = "departament id is required")
    private Long departmentId;











}
