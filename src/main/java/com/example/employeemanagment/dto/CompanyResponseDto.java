package com.example.employeemanagment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyResponseDto {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
}
