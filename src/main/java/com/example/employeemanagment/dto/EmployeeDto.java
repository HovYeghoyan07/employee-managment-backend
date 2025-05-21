package com.example.employeemanagment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {

    private int id;
    private String name;
    private String surname;
    private String phone;
    private double salary;
    private String picture;
    private CompanyResponseDto company;

}
