package com.worktrack.worktrack.department.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequestDto {

    private Long id;

    @NotBlank(message = "name required")
    private String name;

    @Size(max = 500, message = "comment description must not exceed 5000 characters")
    private String description;


}
