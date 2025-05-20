package com.example.employeemanagment.service;

import com.example.employeemanagment.dto.CompanyResponseDto;
import com.example.employeemanagment.dto.SaveCompanyRequest;
import com.example.employeemanagment.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<CompanyResponseDto> findAll();

    CompanyResponseDto save(SaveCompanyRequest authorRequest);

    void deleteById(int id);

    CompanyResponseDto findById(int id);

    Optional<Company> findByName(String name);

    Company getCompanyById(int id);

    Company updateCompany(int id, Company company);

}
