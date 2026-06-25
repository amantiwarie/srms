package org.app.srms.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeacherDto {


    private Long id;

    @NotBlank(message = "username is required")
    private String userName;

    @Size(min =6,message = "Pasword must be greater than 6 characters")
    private String password;



    @NotBlank(message = "please fill the Password")
    @Email(message = "eMAIL should be valid email address")
    private String email;
}
