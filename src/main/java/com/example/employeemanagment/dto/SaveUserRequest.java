package com.example.employeemanagment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserRequest {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Surname must not be blank")
    private String surname;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

}
