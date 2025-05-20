package com.example.employeemanagment.service;

import com.example.employeemanagment.dto.EmployeeDto;
import com.example.employeemanagment.dto.SaveEmployeeRequest;
import com.example.employeemanagment.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAllEmployees();

    Employee getEmployeeById(int id);

    EmployeeDto createEmployee(SaveEmployeeRequest employeeRequest);

    void deleteEmployeeById(int id);

    Employee updateEmployee(int id, Employee employee);

    EmployeeDto getEmployeeDtoById(int id);

    List<EmployeeDto> getEmployeesByCompanyId(int companyId);

    boolean existsByPhone(String phone);


}
