package com.example.employeemanagment.service.impl;

import com.example.employeemanagment.dto.EmployeeDto;
import com.example.employeemanagment.dto.SaveEmployeeRequest;
import com.example.employeemanagment.entity.Employee;
import com.example.employeemanagment.mapper.EmployeeMapper;
import com.example.employeemanagment.repository.EmployeeRepository;
import com.example.employeemanagment.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeMapper.toDtoList(employeeRepository.findAll());
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public EmployeeDto createEmployee(SaveEmployeeRequest employeeRequest) {
        return employeeMapper.toDto(employeeRepository.save(employeeMapper.toEntity(employeeRequest)));
    }

    @Override
    public void deleteEmployeeById(int id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee does not exist");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        Employee serviceEmployee = getEmployeeById(id);
        serviceEmployee.setName(employee.getName());
        serviceEmployee.setSurname(employee.getSurname());
        serviceEmployee.setPhone(employee.getPhone());
        serviceEmployee.setSalary(employee.getSalary());
        serviceEmployee.setCompany(employee.getCompany());
        employeeRepository.save(serviceEmployee);
        return serviceEmployee;
    }

    @Override
    public EmployeeDto getEmployeeDtoById(int id) {
        return employeeMapper.toDto(employeeRepository.findById(id).orElse(null));
    }

    @Override
    public List<EmployeeDto> getEmployeesByCompanyId(int companyId) {
        return employeeMapper.toDtoList(employeeRepository.findByCompanyId(companyId));
    }

    @Override
    public boolean existsByPhone(String phone) {
        return employeeRepository.existsByPhone(phone);
    }
}
