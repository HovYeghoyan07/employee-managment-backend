package com.example.employeemanagment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveCompanyRequest {

    @NotNull(message = "Company name must not be null")
    private String name;

    @NotNull(message = "Address must not be null")
    private String address;

    @NotNull(message = "Phone number must not be null")
    private String phone;

    @NotNull(message = "Email must not be null")
    @Email(message = "Invalid email format")
    private String email;

}
