package com.example.employeemanagment.mapper;

import com.example.employeemanagment.dto.CompanyResponseDto;
import com.example.employeemanagment.dto.SaveCompanyRequest;
import com.example.employeemanagment.entity.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyResponseDto toDto(Company company);

    List<CompanyResponseDto> toDtoList(List<Company> companies);

    Company toEntity(SaveCompanyRequest saveCompanyRequest);
}
