package com.example.employeemanagment.repository;

import com.example.employeemanagment.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByCompanyId(int companyId);

    void deleteEmployeeByCompanyId(int companyId);

    boolean existsByPhone(String phone);
}
