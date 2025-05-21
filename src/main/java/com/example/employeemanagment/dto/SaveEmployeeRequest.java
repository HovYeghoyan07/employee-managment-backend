package com.example.employeemanagment.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveEmployeeRequest {
    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Surname must not be blank")
    private String surname;

    @NotBlank(message = "Phone number must not be blank")
    private String phone;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be a positive number")
    private Double salary;

    private String picture;

    @Min(value = 1, message = "Company ID must be greater than 0")
    private int companyId;


}
