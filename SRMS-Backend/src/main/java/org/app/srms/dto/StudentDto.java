package org.app.srms.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentDto {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Username is required")
    private String userName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be a valid email address")
    private String email;

    @Min(value = 1, message = "Age must be greater than 0")
    @Max(value = 150, message = "Age must be realistic")
    private int age;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}

