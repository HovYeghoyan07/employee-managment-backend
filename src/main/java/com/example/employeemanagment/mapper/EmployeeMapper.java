package com.example.employeemanagment.mapper;

import com.example.employeemanagment.dto.EmployeeDto;
import com.example.employeemanagment.dto.SaveEmployeeRequest;
import com.example.employeemanagment.entity.Company;
import com.example.employeemanagment.entity.Employee;
import com.example.employeemanagment.repository.CompanyRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    @Autowired
    private CompanyRepository companyRepository;

    public abstract EmployeeDto toDto(Employee employee);

    public abstract List<EmployeeDto> toDtoList(List<Employee> employees);

    @Mapping(target = "company", expression = "java(findCompanyById(employeeRequest.getCompanyId()))")
    public abstract Employee toEntity(SaveEmployeeRequest employeeRequest);

    protected Company findCompanyById(int companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId));
    }
}
